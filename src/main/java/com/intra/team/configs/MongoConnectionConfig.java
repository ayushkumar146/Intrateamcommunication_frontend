package com.intra.team.configs;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class MongoConnectionConfig {

    private final MongoClient mongoClient;

    public MongoConnectionConfig(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @PostConstruct
    public void checkMongoConnection() {
        System.out.println("checked");
        try {
            mongoClient.listDatabaseNames().first(); // ping Mongo
            System.out.println("✅ MongoDB is CONNECTED");
        } catch (Exception e) {
            System.out.println("❌ MongoDB is NOT CONNECTED: " + e.getMessage());
        }
    }
}
