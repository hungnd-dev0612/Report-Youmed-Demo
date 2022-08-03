
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import mongo_vertx.day2.Verticle1;


public class Main extends AbstractVerticle {

    public static void main(String[] args) throws InterruptedException {
        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        Vertx.vertx().deployVerticle(new Verticle1(), options);
    }
}
