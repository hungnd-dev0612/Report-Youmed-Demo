import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.reactivex.Observable;
import org.bson.Document;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MongoClient driver = new MongoClient("localhost",27017);
        MongoDatabase database = driver.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("myCollection");
        FindIterable<Document> result = collection.find();
        Iterator it = result.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
