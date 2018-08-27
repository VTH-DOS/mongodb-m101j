package net.lezouave.mongodb.week2;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class FindWithProjectionTest {
    public static void main(String Args[]) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://jmynh:VuPyjryrCYKa9xUV@cluster0-vpjfo.mongodb.net/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("test");

        MongoCollection<Document> coll = dbTest.getCollection("findTest");

        coll.drop();

        for (int i = 0; i<10; i++) {
            coll.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));
        }

        System.out.println(" Find with filter : ");

        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));
        //Bson proj = new Document("y", 1).append("_id", 0);
        Bson proj = exclude("x", "i");
        proj = excludeId();
        proj = fields(include("y"), excludeId());

        List<Document> all  = coll
                .find(filter)
                .projection(proj)
                .into(new ArrayList<Document>());

        for (Document doc : all) {
            System.out.println(doc.toJson());
        }
        System.out.println(" Count : " + coll.countDocuments(filter));
    }
}
