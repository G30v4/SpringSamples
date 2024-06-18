package dev.g30v4.spring.data.cfg;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

public abstract class AbstractMongoConfig {

    /*
     * Method that creates MongoDbFactory
     * Common to both of the MongoDb connections
     */
    public MongoDatabaseFactory mongoDbFactory(String uri) throws Exception {
        return new SimpleMongoClientDatabaseFactory(uri);
    }

    /*
     * Factory method to create the MongoTemplate
     */
    public abstract MongoTemplate getMongoTemplate(
        MappingMongoConverter converter
        ) throws Exception;
}
