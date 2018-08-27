package net.lezouave.mongodb.week2;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;

public class FindTest {
    public static void main(String Args[]) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://jmynh:VuPyjryrCYKa9xUV@cluster0-vpjfo.mongodb.net/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("test");

        MongoCollection<Document> coll = dbTest.getCollection("findTest");

        coll.drop();

        for (int i = 0; i<10; i++) {
            coll.insertOne(new Document("x", i));
        }

        System.out.println(" Find One : ");
        Document first = coll.find().first();
        System.out.println(first.toJson());

        System.out.println(" Find all with into : ");
        List<Document> all = coll.find().into(new ArrayList<Document>());
        for (Document doc : all) {
            System.out.println(doc.toJson());
        }

        System.out.println(" Find all with iteration : ");
        MongoCursor<Document> cursor = coll.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                System.out.println(cur.toJson());
            }
        } finally {
            cursor.close();
        }

        System.out.println(" Count : " + coll.countDocuments());

        System.out.println("+----------------------------------------------+");

        // ****************
        // new exercice
        // ****************
        coll.drop();

        for (int i = 0; i<10; i++) {
            coll.insertOne(new Document("x", new Random().nextInt(2)).append("y", new Random().nextInt(100)));
        }

        System.out.println(" Find with filter : ");
        // Bson filter = new Document("x", 0).append("y", new Document("$gt", 10).append("$lt", 90));
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        all = coll.find(filter).into(new ArrayList<Document>());
        for (Document doc : all) {
            System.out.println(doc.toJson());
        }
        System.out.println(" Count : " + coll.countDocuments(filter));
    }
}