package net.lezouave.mongodb.week2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Sorts.*;

public class FindWithSortSkipLimitTest {
    public static void main(String Args[]) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://jmynh:VuPyjryrCYKa9xUV@cluster0-vpjfo.mongodb.net/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("test");

        MongoCollection<Document> coll = dbTest.getCollection("findTest");

        coll.drop();

        for (int i = 0; i<10; i++) {
            for (int j = 0; j<10; j++) {
                coll.insertOne(new Document()
                        .append("i", i)
                        .append("j", j) );
            }
        }

        System.out.println(" Find with sort, skip & limit : ");

        Bson proj = fields(include("i", "j"), excludeId());

        //Bson sort = new Document("i", 1).append("j", -1);
        Bson sort = orderBy(ascending("j"), descending("i"));

        List<Document> all  = coll
                .find()
                .projection(proj)
                .sort(sort)
                .skip(20)
                .limit(50)
                .into(new ArrayList<Document>());

        for (Document doc : all) {
            System.out.println(doc.toJson());
        }
        System.out.println(" Count : " + coll.countDocuments());
    }
}
