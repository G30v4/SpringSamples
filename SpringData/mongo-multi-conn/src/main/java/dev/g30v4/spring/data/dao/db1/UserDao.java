package dev.g30v4.spring.data.dao.db1;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.g30v4.spring.data.dto.User;

public interface UserDao extends MongoRepository<User, String> {
    
}
