package com.library.util;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.concurrent.TimeUnit;

/**
 * Singleton MongoDB connection manager.
 *
 * DNS FIX for university WiFi:
 *   University DNS servers often don't serve SRV records, which breaks the
 *   "mongodb+srv://" connection string. We force the JVM (and the JNDI DNS
 *   context the MongoDB driver uses for SRV lookups) to use Google's public
 *   DNS (8.8.8.8) instead. This happens before any MongoClient is created.
 */
public class MongoDBManager {

    static {
        // Redirect all JVM DNS queries to Google's public DNS.
        // This fixes "mongodb+srv://" SRV lookup failures on university WiFi.
        System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8,1.1.1.1");
        System.setProperty("sun.net.spi.nameservice.provider.1",  "dns,sun");
        // Also redirect the JNDI DNS context that the MongoDB driver
        // uses specifically for SRV record resolution.
        System.setProperty("com.sun.jndi.dns.url", "dns://8.8.8.8");
    }

    private static volatile MongoDBManager instance;
    private final MongoClient  mongoClient;
    private final MongoDatabase database;

    private MongoDBManager() {
        String uri    = ConfigLoader.get("mongodb.uri",      "mongodb://localhost:27017");
        String dbName = ConfigLoader.get("mongodb.database", "lmsdb");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .applyToClusterSettings(b -> b
                        .serverSelectionTimeout(15, TimeUnit.SECONDS))
                .applyToSocketSettings(b -> b
                        .connectTimeout(5,  TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS))
                .build();

        mongoClient = MongoClients.create(settings);
        database    = mongoClient.getDatabase(dbName);
    }

    public static MongoDBManager getInstance() {
        if (instance == null) {
            synchronized (MongoDBManager.class) {
                if (instance == null) {
                    instance = new MongoDBManager();
                }
            }
        }
        return instance;
    }

    public MongoDatabase getDatabase() { return database; }

    public void close() {
        if (mongoClient != null) mongoClient.close();
    }
}
