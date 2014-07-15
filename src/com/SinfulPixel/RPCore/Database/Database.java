package com.SinfulPixel.RPCore.Database;

import com.SinfulPixel.RPCore.RPCore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class Database {
    protected RPCore plugin;
    protected Database(RPCore plugin) {
        this.plugin = plugin;
    }
    public abstract Connection openConnection() throws SQLException, ClassNotFoundException;
    public abstract boolean checkConnection() throws SQLException;
    public abstract Connection getConnection();
    public abstract boolean closeConnection() throws SQLException;
    public abstract ResultSet querySQL(String query) throws SQLException, ClassNotFoundException;
    public abstract int updateSQL(String query) throws SQLException, ClassNotFoundException;
}