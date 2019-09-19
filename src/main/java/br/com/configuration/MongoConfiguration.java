package br.com.configuration;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoTemplate mongoTemplate(@Value("${mongo.host}") String host, @Value("${mongo.name}") String name) throws IOException {

        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(host);
        MongoClient mongoClient = mongo.getObject();
        return new MongoTemplate(mongoClient, name);
    }
}
