package dev.g30v4.spring.data.cfg;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableConfigurationProperties
@EnableMongoRepositories(basePackages = "dev.g30v4.spring.data.dao.db1", mongoTemplateRef = "db1MongoTemplate")
public class Db1Config {

    @Bean(name = "primaryProperties")
    @ConfigurationProperties(prefix = "db.mongo.db1")
    @Primary
    public MongoProperties primaryProperties() {
        return new MongoProperties();
    }

    @Bean(name = "primaryMongoClient")
    public MongoClient mongoClient(@Qualifier("primaryProperties") MongoProperties mongoProperties) {
        return MongoClients.create(mongoProperties.getUri());
    }

    @Primary
    @Bean(name = "primaryMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(@Qualifier("primaryMongoClient") MongoClient mongoClient, @Qualifier("primaryProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

    @Primary
    @Bean(name = "db1MongoTemplate")
    public MongoTemplate mongoTemplate(
        @Qualifier("primaryMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory,
        final MappingMongoConverter converter
        ) {
        return new MongoTemplate(mongoDatabaseFactory,converter);
    }
    
}
