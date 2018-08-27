package net.lezouave.mongodb.week2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;


public class InsertTest {
    public static void main(String Args[]) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://jmynh:VuPyjryrCYKa9xUV@cluster0-vpjfo.mongodb.net/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("test");

        MongoCollection<Document> collContact = dbTest.getCollection("contact");

        long nbDocs = collContact.countDocuments();

        System.out.println("nombre de documents dans la collection avant insertion : " + nbDocs);


        Document docSmith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");

        Document docJones = new Document("name", "Jones")
                .append("age", 25)
                .append("profession", "hacker");

        System.out.println("docSmith : " + docSmith.toJson());
        System.out.println("docJones : " + docJones.toJson());

        collContact.insertMany(Arrays.asList(docSmith, docJones));

        System.out.println("docSmith : " + docSmith.toJson());
        System.out.println("docJones : " + docJones.toJson());
    }
}
