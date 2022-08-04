package mongo_vertx.day3;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import model.User;

import java.util.HashMap;
import java.util.Map;


public class StartHttpServer extends AbstractVerticle {
    public Map<Integer, User> users = new HashMap<>();

    @Override
    public void start(Future<Void> startFuture) throws Exception {
//        Logger log = LoggerFactory.getLogger(StartHttpServer.class);
//        createSomeData();
//        Router router = Router.router(vertx);
//        vertx.createHttpServer().requestHandler(handler -> {
//            router.route("/api/users").handler(routingContext -> {
//                HttpServerResponse response = routingContext.response();
//                response.putHeader("content-type", "application/json; charset=utf-8")
//                        .end(Json.encodePrettily(users.values()));
//            });
//        }).listen(config().getInteger("http.port",8080), result -> {
//            if(result.succeeded()){
//                startFuture.complete();
//            }else{
//                startFuture.fail(result.cause());
//            }
//        });

        Router router = Router.router(vertx);
        createSomeData();
        router.route("/api/users").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(users.values()));
        });

        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                startFuture.complete();
                            } else {
                                startFuture.fail(result.cause());
                            }
                        }
                );

    }

    public void allSetup(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(users.values()));
    }

    private void createSomeData() {
        User danghung = new User("Dang Hung", "141/b To Hien Thanh, q10, Tp.HCM");
        users.put(danghung.getId(), danghung);
        User tannguyen = new User("Tan Nguyen", "531/ab Cach Mang Thang 8, q10, Tp.HCM");
        users.put(tannguyen.getId(), tannguyen);
    }
}
