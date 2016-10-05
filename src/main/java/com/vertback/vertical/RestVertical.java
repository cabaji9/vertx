package com.vertback.vertical;

import com.vertback.backend.dao.UserDao;
import com.vertback.backend.daoImpl.UserDaoImpl;
import com.vertback.backend.vo.User;
import com.vertback.util.GsonSingleton;
import com.vertback.util.SecurityUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.logging.Logger;


public class RestVertical extends AbstractVerticle {

    private static Logger logger = Logger.getLogger(RestVertical.class.getName());

    GsonSingleton gsonSingleton = GsonSingleton.getInstance();
    UserDao userDao = new UserDaoImpl();

    @Override
    public void start(Future<Void> fut) {

        Router router = Router.router(vertx);
        enableCors(router);
        router.route().handler(BodyHandler.create());
        router.post("/api/lgnusr").handler(this::verifyUser);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8081),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }


    private void verifyUser(RoutingContext routingContext) {
        String user = routingContext.request().getParam("user");
        String password = routingContext.request().getParam("password");
        User userObj = null;
        if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
            userObj = userDao.obtainUserByUserName(user);
            if (userObj != null) {
                logger.info("USER FINDED" +userObj.getUser());
                String codedPassword = SecurityUtils.get_SHA_512_SecurePassword(userObj.getPassword());
                if (!codedPassword.equals(password)) {
                     userObj = null; //not return user not password
                }
            }
        }
        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(userObj != null ? gsonSingleton.getGson().toJson(userObj) : "");
    }


    private void enableCors(Router router) {
        //enable cors for js crossdomain ajax.
        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedHeader("X-PINGARUNER")
                .allowedHeader("Content-Type"));
    }
}