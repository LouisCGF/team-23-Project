package com.example.team_23_project.databaseConnection;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    String username, password, ip, port, database;

    public Connection connectionClass() {
        ip = "cs-db.ncl.ac.uk";
        database = "csc2033_team23";
        username = "csc2033_team23";
        password = "WhimHer84War";
        port = "3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" +
                    "database name=" + database + ";username=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (Exception e) {
            Log.e("Error ", e.getMessage());
        }
        return connection;
    }
}
