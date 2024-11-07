package br.com.actionlabs.carboncalc.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateCalcInfoRequestDTO {
  @NonNull
  private String id;
  private int energyConsumption;
  private List<TransportationDTO> transportation;
  private int solidWasteTotal;
  private double recyclePercentage;
}
