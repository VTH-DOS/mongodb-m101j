package net.lezouave.mongodb.week2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

public class DeleteTest {
    public static void main(String Args[]) {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://jmynh:VuPyjryrCYKa9xUV@cluster0-vpjfo.mongodb.net/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("test");

        MongoCollection<Document> coll = dbTest.getCollection("deleteTest");

        coll.drop();

        for (int i = 0; i < 8; i++) {
            coll.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i)
                    .append("y", true));
        }

        printCollection(coll);

        System.out.println("+--------------------------------------+");
        System.out.println(" Delete operations... ");
        System.out.println("+--------------------------------------+");

        coll.deleteOne(eq("x", 2));

        coll.deleteMany(gt("_id", 5));

        printCollection(coll);

    }

    private static void printCollection(MongoCollection<Document> p_coll) {
        List<Document> all  = p_coll
                .find()
                .into(new ArrayList<Document>());

        for (Document doc : all) {
            System.out.println(doc.toJson());
        }
    }
}
