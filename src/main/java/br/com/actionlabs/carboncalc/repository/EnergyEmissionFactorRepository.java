package br.com.actionlabs.carboncalc.repository;

import br.com.actionlabs.carboncalc.model.EnergyEmissionFactor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyEmissionFactorRepository extends MongoRepository<EnergyEmissionFactor, String> {

	@Query("{uf:'?0'}")
	EnergyEmissionFactor findByUf(String uf);

}
