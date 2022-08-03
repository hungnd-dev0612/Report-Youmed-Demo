//package mongo_vertx.day1;
//
//import org.bson.Document;
//
//import java.util.Iterator;
//
//public class ConnectDB {
//    public static void main(String[] args) {
//        MongoClient driver = new MongoClient("localhost",27017);
//        MongoDatabase database = driver.getDatabase("local");
//        MongoCollection<Document> collection = database.getCollection("myCollection");
//        FindIterable<Document> result = collection.find();
//        Iterator it = result.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next());
//        }
//    }
//}
