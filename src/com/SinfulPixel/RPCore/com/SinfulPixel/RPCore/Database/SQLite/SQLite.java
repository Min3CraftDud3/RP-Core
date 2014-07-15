package com.SinfulPixel.RPCore.com.SinfulPixel.RPCore.Database.SQLite;

import com.SinfulPixel.RPCore.RPCore;
import com.SinfulPixel.RPCore.com.SinfulPixel.RPCore.Database.Database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;


public class SQLite extends Database {
    private final String dbLocation;

    private Connection connection;

    public SQLite(RPCore plugin, String dbLocation) {
        super(plugin);
        this.dbLocation = dbLocation;
        this.connection = null;
    }
    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        File file = new File(plugin.getDataFolder(), dbLocation);
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE,
                        "Unable to create database!");
            }
        }
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager
                .getConnection("jdbc:sqlite:"
                        + plugin.getDataFolder().toPath().toString() + "/"
                        + dbLocation);
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