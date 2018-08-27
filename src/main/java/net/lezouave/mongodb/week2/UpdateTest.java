package net.lezouave.mongodb.week2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Updates.*;

public class UpdateTest {
    public static void main(String Args[]) {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://jmynh:VuPyjryrCYKa9xUV@cluster0-vpjfo.mongodb.net/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("test");

        MongoCollection<Document> coll = dbTest.getCollection("updateTest");

        coll.drop();

        for (int i = 0; i < 8; i++) {
            coll.insertOne(new Document()
                        .append("_id", i)
                        .append("x", i)
                        .append("y", true));
        }

        printCollection(coll);

        System.out.println("+--------------------------------------+");
        System.out.println(" Update operations... ");
        System.out.println("+--------------------------------------+");

        coll.replaceOne(eq("x", 5), new Document("x", 20).append("updated", true));
        //coll.updateOne(eq("x", 6), new Document("$set",
        //        new Document("x", 24).append("updated", true)));
        coll.updateOne(eq("_id", 6), combine(set("x", 24), set("updated", true)));
        coll.updateOne(eq("_id", 9),
                combine(set("x", 36), set("updated", true)),
                new UpdateOptions().upsert(true));

        coll.updateMany(lt("x", 5), inc("x", -1));

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
