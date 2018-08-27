package net.lezouave.mongodb.week2.homework;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class Question_2_3 {
    public static void main (String args[]) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("students");

        MongoCollection<Document> coll = dbTest.getCollection("grades");

        System.out.println(" Count : " + coll.countDocuments());

        Bson filter = eq("type", "homework");
        Bson sort = Sorts.ascending("student_id", "score");

        Bson deleteFilter = eq("_id", "-1");

        List<Document> listDoc = coll.find(filter)
                .sort(sort)
                .into(new ArrayList<Document>());

        System.out.println(" taille de listDoc : " + listDoc.size());

        int studentId = -1;
        int countDeleted = 0;
        for (Document doc : listDoc) {
            if (studentId == doc.getInteger("student_id")) {
                // nothing
            } else {
                System.out.println("Je dois supprimer _id : " + doc.get("_id")
                        + " --- student : " + doc.getInteger("student_id")
                        + " --> note : " + doc.get("score"));
                studentId = doc.getInteger("student_id");

                deleteFilter = eq("_id", doc.get("_id"));
                //coll.deleteOne(deleteFilter);
                countDeleted++;
            }
        }

        System.out.println(" nb de doc supprimés : " + countDeleted);

    }
}
