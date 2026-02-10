package com.intra.team.repository;


import com.intra.team.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByUsername(String username);
}
