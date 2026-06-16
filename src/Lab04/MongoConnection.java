package Lab04;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConnection {
    private static MongoClient mongoClient;

    private MongoConnection() {
    }

    public static MongoCollection<Document> getProductCollection() {
        return getDatabase().getCollection(getEnv("MONGO_COLLECTION"));
    }

    private static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            String uri = getEnv("MONGO_URI");
            mongoClient = MongoClients.create(uri);
        }

        return mongoClient.getDatabase(getEnv("MONGO_DATABASE"));
    }

    private static String getEnv(String keyName) {
        String value = System.getenv(keyName);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing key: " + keyName + " please set into environment");
        }

        return value;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}