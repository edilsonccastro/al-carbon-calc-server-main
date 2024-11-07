package br.com.actionlabs.carboncalc.repository;

import br.com.actionlabs.carboncalc.model.CalcInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalcInfoRepository extends MongoRepository<CalcInfo, String> {

}
