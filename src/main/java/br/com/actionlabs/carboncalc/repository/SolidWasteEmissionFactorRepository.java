package br.com.actionlabs.carboncalc.repository;

import br.com.actionlabs.carboncalc.model.SolidWasteEmissionFactor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolidWasteEmissionFactorRepository
    extends MongoRepository<SolidWasteEmissionFactor, String> {

        @Query("{uf:'?0'}")
        SolidWasteEmissionFactor findByUf(String uf);
    
}
