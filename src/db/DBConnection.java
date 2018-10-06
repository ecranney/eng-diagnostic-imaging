package db;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    public Connection getConnection() throws SQLException {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("ec2-50-17-194-129.compute-1.amazonaws.com");
        ds.setDatabaseName("ddd1ffc2dcciud");
        ds.setUser("akasvjtcdtwoly");
        ds.setPassword("af2fb830b539917e1e7f2b5087c3d0028b4b0775651fb43a141a8e5816d705ed");
        ds.setPortNumber(5432);
        Connection dbConnection = ds.getConnection();
        return dbConnection;

//        PGSimpleDataSource ds = new PGSimpleDataSource();
//        ds.setServerName("localhost");
//        ds.setDatabaseName("dies");
//        ds.setUser("user");
//        ds.setPassword("password");
//        ds.setPortNumber(5432);
//        Connection dbConnection = ds.getConnection();
//        return dbConnection;
    }
}