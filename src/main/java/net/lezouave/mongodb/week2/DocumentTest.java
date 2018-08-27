package net.lezouave.mongodb.week2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class DocumentTest {

    public static void main(String Args[]) {
        Document doc = new Document();

        doc.append("str", "Hello MongoDB");

        String sResult = doc.getString("str");

        System.out.println(sResult);

        //mongodb+srv://jmynh:<PASSWORD>@cluster0-vpjfo.mongodb.net/test?retryWrites=true
        MongoClient mongoClient = MongoClients.create("mongodb+srv://jmynh:VuPyjryrCYKa9xUV@cluster0-vpjfo.mongodb.net/test?retryWrites=true");

        MongoDatabase database = mongoClient.getDatabase("video");

        MongoCollection<Document> collMovie = database.getCollection("movieDetails");

        long nbDocs = collMovie.countDocuments();

        System.out.println("nombre de documents dans la collection : " + nbDocs);


        Document myDoc = collMovie.find(Filters.eq("year", 2009)).first();

        System.out.println("myDoc : " + myDoc.toJson());
    }

}
