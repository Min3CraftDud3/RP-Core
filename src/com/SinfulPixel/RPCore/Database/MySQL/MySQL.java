package com.SinfulPixel.RPCore.Database.MySQL;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.Database.Database;

import java.sql.*;

public class MySQL extends Database {
    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;
    private Connection connection;

    public MySQL(RPCore plugin, String hostname, String port, String database,
                 String username, String password) {
        super(plugin);
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        this.connection = null;
    }
    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + this.hostname + ":" + this.port + "/" + this.database,
                this.user, this.password);
        return connection;
    }
    @Override
    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }
    @Override
    public Connection getConnection() {
        return connection;
    }
    @Override
    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }
        connection.close();
        return true;
    }
    @Override
    public ResultSet querySQL(String query) throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            openConnection();
        }
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }
    @Override
    public int updateSQL(String query) throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            openConnection();
        }
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }
}