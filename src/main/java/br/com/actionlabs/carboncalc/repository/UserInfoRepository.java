package br.com.actionlabs.carboncalc.repository;


import br.com.actionlabs.carboncalc.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {

}
