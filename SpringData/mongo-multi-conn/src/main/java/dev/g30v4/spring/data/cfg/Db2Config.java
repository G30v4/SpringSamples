package dev.g30v4.spring.data.cfg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Configuration
@RequiredArgsConstructor
@ConfigurationPropertiesScan 
@ConfigurationProperties(prefix = "db.mongo.db2")
@EnableMongoRepositories(basePackages = "dev.g30v4.spring.data.dao.db2", mongoTemplateRef = "db2MongoTemplate")
public class Db2Config extends AbstractMongoConfig {
    
    private String uri;

    @Override
    @Bean(name = "db2MongoTemplate")
    public MongoTemplate getMongoTemplate(
        final MappingMongoConverter converter
        ) throws Exception {
        return new MongoTemplate(mongoDbFactory(uri),converter);
    }

}
