package mongo_vertx.day2;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.resolver.ResolverProvider;
import io.vertx.ext.mongo.MongoClient;

import java.sql.PreparedStatement;
import java.util.Scanner;


public class Verticle1 extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(ResolverProvider.class);
    public static MongoClient client;

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        JsonObject config = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "local");
        client = MongoClient.createShared(vertx, config);
        JsonObject query = new JsonObject().put("name", "hand");
        Scanner scan = new Scanner(System.in);
        Thread.sleep(100);
        logger.info("Nhập số 1 hoặc 2 hoặc 3 để sài chức năng: ");
        int choose = scan.nextInt();
        String name;
        switch (choose) {
            case 1:
                logger.info("bạn chọn tìm thông tin user");
                logger.info("nhập tên bạn muốn tìm: ");
                name = scan.nextLine();
                findOne(name);
                break;
            case 2:
                logger.info("bạn chọn thêm mới user");
                insert();
                break;
            case 3:
                logger.info("bạn chọn cập nhật thông tin user");
                logger.info("nhập tên user cần tìm: ");
                String findByName = scan.nextLine();
                update(findByName);
                break;
            case 4:
                scan.nextLine();
                logger.info("bạn chọn xóa user");
                logger.info("nhập tên user cần xóa: ");
                String deleteByName = scan.nextLine();
                delete(deleteByName);
                break;
            default:
                System.out.println("Chọn số đi");
                return;
        }


    }

    public void insert() {

        Scanner scan = new Scanner(System.in);
        System.out.println("nhập tên: ");
        String name = scan.nextLine();
        System.out.println("nhập tuổi: ");

        int age = scan.nextInt();

        System.out.println("nhập địa chỉ: ");
        scan.nextLine();
        String address = scan.nextLine();
        JsonObject document = new JsonObject()
                .put("name", name)
                .put("age", age)
                .put("location", address);
        JsonObject query = new JsonObject().put("name", name);
        client.insert("myCollection", document, res -> {
            if (res.succeeded()) {
                String id = res.result();
                JsonObject showDetailWhenInsertSuccess = new JsonObject().put("name", name);
                client.findOne("myCollection", query, document, res2 -> {
                    if (res2.succeeded()) {
                        logger.info("Đây là thông tin user bạn vừa thêm vào: ");
                        System.out.println(res2.result());
                    } else {
                        logger.error("????????");
                    }
                });
            } else {
                logger.error("Có lỗi rồi ");
                res.cause().printStackTrace();
            }
        });
    }

    public void update(String name) {
        JsonObject query = new JsonObject().put("name", name);
        Scanner scan = new Scanner(System.in);
        System.out.println("nhập tên mới: ");
        String newName = scan.nextLine();
        System.out.println("nhập tuổi mới: ");
        int newAge = scan.nextInt();
        System.out.println("nhập địa chỉ mới: ");
        scan.nextLine();
        String newAddress = scan.nextLine();
        JsonObject update = new JsonObject().put("$set", new JsonObject()
                .put("name", newName)
                .put("age", newAge)
                .put("location", newAddress));

        client.updateCollection("myCollection", query, update, res -> {
            if (res.succeeded()) {
                logger.info("Update thành công");
            } else {
                logger.error("Không update được");
                res.cause().printStackTrace();
                ;
            }
        });
    }

    public void delete(String name) {
        JsonObject query = new JsonObject().put("name", name);
        client.removeDocuments("myCollection", query, res -> {
            if (res.succeeded()) {
                logger.info("Xóa thành công");
            } else {
                logger.error("Không xóa được");
                res.cause().printStackTrace();
                ;
            }
        });
    }

    public void findOne(String name) {
        JsonObject query = new JsonObject().put("name", name);
        client.find("myCollection", query, res -> {
            if (res.succeeded()) {
                logger.info("Đây là danh sách những người có tên là " + name);
//                for (JsonObject jayson : res.result()) {
//                    logger.info(jayson.encodePrettily());
//                }
                System.out.println(res.result());
            } else {
                logger.error("Không có người tên như này: ", res.cause());
            }
        });
    }

    public void findAll() {

    }

    public void loopFeature(){

    }

}
