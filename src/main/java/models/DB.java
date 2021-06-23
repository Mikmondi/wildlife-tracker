package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

import java.net.URI;
import java.net.URISyntaxException;

public class DB {

    private static URI dbUri;
    public static Sql2o sql2o;

    static {
        Logger logger = LoggerFactory.getLogger(DB.class);

        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("postgres://localhost:5432/wild_tracker");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? DatabaseProps.username : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? DatabaseProps.password : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        } catch (URISyntaxException e ) {
            logger.error("Unable to connect to database.");
        }
    }

    //production database
//    public static Sql2o sql2o = new Sql2o(
//            "jdbc:postgresql://ec2-54-247-79-178.eu-west-1.compute.amazonaws.com:5432/d7esparbsibo8e",
//            "zhechbctmwouul",
//            "0787c335a37c42a426954f7337810b2ef68c27affb9b96c22bf487d9bd606ebd");

    //development database
//    public static Sql2o sql2o = new Sql2o(
//            "jdbc:postgresql://localhost:5432/wildlife_tracker",
//            "User",
//            "7181");
}