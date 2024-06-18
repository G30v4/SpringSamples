package dev.g30v4.spring.data.dao.db2;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.g30v4.spring.data.dto.Product;

public interface ProductDao extends MongoRepository<Product, String> {
    
}
