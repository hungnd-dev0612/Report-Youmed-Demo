package mongo_vertx.day3;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class MyFirstVerticle extends AbstractVerticle {
    public Map<Integer, User> users = new HashMap<>();

    @Override
    public void start() throws Exception {
        createUser();
        Router router = Router.router(vertx);
        router.get("/api/users")
                .handler(this::getAll)
        ;
    }

    public void createUser() {
        User danghung = new User("Dang Hung", "141/b To Hien Thanh, q10, Tp.HCM");
        User tannguyen = new User("Tan Nguyen", "531/ab Cach Mang Thang 8, q10, Tp.HCM");
        users.put(danghung.getId(), danghung);
        users.put(tannguyen.getId(), tannguyen);
    }

    public void getAll(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(users.values()));
    }
}
