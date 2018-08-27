package net.lezouave.mongodb.week2.homework;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Question_3_1 {
    public static void main(String args[]) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost/test?retryWrites=true");

        MongoDatabase dbTest = mongoClient.getDatabase("school");

        MongoCollection<Document> coll = dbTest.getCollection("students");

        System.out.println(" Count : " + coll.countDocuments());

        Bson filter = eq("scores.type", "homework");

        Bson updateFilter = eq("_id", "-1");

        List<Document> listDoc = coll.find(filter)
                .into(new ArrayList<Document>());

        System.out.println(" taille de listDoc : " + listDoc.size());

        int studentId = -1;
        int countUpdated = 0;
        double dblScore = 200.00;
        int position = 0;
        Document scoreToRemove = null;

        for (Document doc : listDoc) {
            ArrayList<Document> listScores = new ArrayList<Document>();
            listScores = doc.get("scores", new ArrayList<Document>());
            if (doc.getInteger("_id") < 2) {
                System.out.println(" taille de listScores : " + listScores.size());
            }

            dblScore = 200.00;
            position = 0;
            scoreToRemove = null;

            for (Document score: listScores) {
                if ("homework".equals(score.getString("type"))) {
                    if (doc.getInteger("_id") < 2) {
                        System.out.println(" homework score found : " + dblScore);
                    }
                    if (score.getDouble("score") < dblScore) {
                        position++;
                        dblScore = score.getDouble("score");
                        scoreToRemove = score;
                    }
                }
            }
            if (scoreToRemove != null) {
                listScores.remove(scoreToRemove);
                countUpdated++;


                doc.replace("scores", listScores);

                updateFilter = eq("_id", doc.get("_id"));
                // décommenter pour remplacer :
                // coll.replaceOne(updateFilter, doc);
            }

        }

        System.out.println(" nb de doc mis à jour : " + countUpdated);
    }
}