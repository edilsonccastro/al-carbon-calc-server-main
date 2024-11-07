package br.com.actionlabs.carboncalc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.actionlabs.carboncalc.dto.CarbonCalculationResultDTO;
import br.com.actionlabs.carboncalc.dto.StartCalcRequestDTO;
import br.com.actionlabs.carboncalc.dto.TransportationDTO;
import br.com.actionlabs.carboncalc.dto.UpdateCalcInfoRequestDTO;
import br.com.actionlabs.carboncalc.model.CalcInfo;
import br.com.actionlabs.carboncalc.model.Transportation;
import br.com.actionlabs.carboncalc.model.UserInfo;
import br.com.actionlabs.carboncalc.model.TransportationEmissionFactor;
import br.com.actionlabs.carboncalc.model.EnergyEmissionFactor;
import br.com.actionlabs.carboncalc.model.SolidWasteEmissionFactor;
import br.com.actionlabs.carboncalc.repository.CalcInfoRepository;
import br.com.actionlabs.carboncalc.repository.UserInfoRepository;
import br.com.actionlabs.carboncalc.repository.EnergyEmissionFactorRepository;
import br.com.actionlabs.carboncalc.repository.SolidWasteEmissionFactorRepository;
import br.com.actionlabs.carboncalc.repository.TransportationEmissionFactorRepository;

@Service
public class OpenService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    CalcInfoRepository calcInfoRepository;

    @Autowired
    EnergyEmissionFactorRepository energyEmissionFactorRepository;

    @Autowired
    SolidWasteEmissionFactorRepository solidWasteEmissionFactorRepository;

    @Autowired
    TransportationEmissionFactorRepository transportationEmissionFactorRepository;

    public String saveUserInfo(StartCalcRequestDTO dto) {
        var userInfo = new UserInfo();
        userInfo.setName(dto.getName());
        userInfo.setEmail(dto.getEmail());
        userInfo.setUf(dto.getUf());
        userInfo.setPhoneNumber(dto.getPhoneNumber());
        userInfo = userInfoRepository.save(userInfo);

        return userInfo.getId();
    }

    public boolean updateCalcInfo(UpdateCalcInfoRequestDTO dto) {
        boolean success = false;
        List<Transportation> transportationList = new ArrayList<>();
        
        try {
            var calcInfo = new CalcInfo();
            calcInfo.setId(dto.getId());
            calcInfo.setEnergyConsumption(dto.getEnergyConsumption());
            for (TransportationDTO dtoItem : dto.getTransportation()) {
                transportationList.add(
                    new Transportation(
                        dtoItem.getType(),
                        dtoItem.getMonthlyDistance())
                );
            }
            calcInfo.setTransportation(transportationList);
            calcInfo.setSolidWasteTotal(dto.getSolidWasteTotal());
            calcInfo.setRecyclePercentage(dto.getRecyclePercentage());
            calcInfoRepository.save(calcInfo);
            success = true;
        } finally {
            return success;
        }
    }

    public CarbonCalculationResultDTO calculateCarbon(String id) {
        var result = new CarbonCalculationResultDTO();
        var userInfo = userInfoRepository.findById(id).get();
        var calcInfo = calcInfoRepository.findById(id).get();
        var energyEmissionFactor = energyEmissionFactorRepository.findByUf(userInfo.getUf());
        var transportationEmissionFactorList = transportationEmissionFactorRepository.findAll();
        var solidWasteEmissionFactor = solidWasteEmissionFactorRepository.findByUf(userInfo.getUf());

        result.setEnergy(calcInfo.getEnergyConsumption() * energyEmissionFactor.getFactor());

        var transportation = 0.0;
        for (Transportation item : calcInfo.getTransportation()) {
            List<TransportationEmissionFactor> factors = transportationEmissionFactorList.stream()
                .filter(f -> f.getType().equals(item.getType()))
                .collect(Collectors.toList());    
            transportation += factors.get(0).getFactor() * item.getMonthlyDistance();
        }
        result.setTransportation(transportation);

        var recycled = calcInfo.getSolidWasteTotal() * calcInfo.getRecyclePercentage();
        result.setSolidWaste(
            recycled * solidWasteEmissionFactor.getRecyclableFactor() +
            (calcInfo.getSolidWasteTotal() - recycled) * solidWasteEmissionFactor.getNonRecyclableFactor()
        );

        result.setTotal(
            result.getEnergy() +
            result.getTransportation() +
            result.getSolidWaste()
        );

        return result;        
    }

}
