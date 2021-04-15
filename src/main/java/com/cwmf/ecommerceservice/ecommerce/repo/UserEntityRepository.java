package com.cwmf.ecommerceservice.ecommerce.repo;

import com.cwmf.ecommerceservice.ecommerce.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findUserEntityByEmail(String email);
}
