/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.sbexample1.databaseconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;

/**
 *
 * @author steveo
 */
public class DBConnectionPoolSingleton {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DBConnectionPoolSingleton.class);

    private static DBConnectionPoolSingleton dbConnectionPoolSingleton;

    private final HashMap<String, BasicDataSource> hashMapdbConnectionPool = new HashMap();

    private DBConnectionPoolSingleton() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public static DBConnectionPoolSingleton getDBConnectionPoolSingleton() {

        LOG.info("DBConnectionPoolSingleton.getDBConnectionPoolSingleton start: ");
        if (dbConnectionPoolSingleton == null) {
            LOG.info("Creating connection DBConnectionPoolSingleton.getDBConnectionPoolSingleton pass 1");
            synchronized (DBConnectionPoolSingleton.class) {
                if (dbConnectionPoolSingleton == null) {
                    LOG.info("Creating connection DBConnectionPoolSingleton.getDBConnectionPoolSingleton -- There can be only one!");
                    dbConnectionPoolSingleton = new DBConnectionPoolSingleton();
                }
            }
        }
        return dbConnectionPoolSingleton;
    }

    public void setDBConnectionFor(DBConnectionInformation db) throws SQLException {
        LOG.info("DBConnectionPoolSingleton.setDBConnectionFor start: " + db.getConnectionName());
        if (!hashMapdbConnectionPool.containsKey(db.getConnectionName())) {
            LOG.info("Creating connection DBConnectionPoolSingleton.setDBConnectionFor pass 1");
            synchronized (DBConnectionPoolSingleton.class) {
                if (!hashMapdbConnectionPool.containsKey(db.getConnectionName())) {
                    LOG.info("Creating connection DBConnectionPoolSingleton.setDBConnectionFor -- There can be only one!");
                    hashMapdbConnectionPool.put(db.getConnectionName(), setDBConnectionPoolFor(db));
                }
            }
        }
        
        LOG.info("DBConnectionPoolSingleton.setDBConnectionFor end: " + db.getConnectionName());

    }

    public Connection getDBConnectionFor(String db) throws SQLException {

        LOG.info("DBConnectionPoolSingleton.getDBConnectionFor start: " + db);
        Connection connection;
        connection = hashMapdbConnectionPool.get(db).getConnection();
        LOG.info("DBConnectionPoolSingleton.getDBConnectionFor end: " + db);

        return connection;
    }

    private BasicDataSource setDBConnectionPoolFor(DBConnectionInformation db) {
        LOG.info("DBConnectionPoolSingleton.setDBConnectionPoolFor start: " + db.getConnectionName());

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(db.getConnectionDriver());
        ds.setUrl(db.getConnectionURL());
        ds.setUsername(db.getConnectionUsername());
        ds.setPassword(db.getConnectionPassword());
        ds.setMinIdle(5);
        ds.setMaxIdle(8);
        ds.setMaxOpenPreparedStatements(100);
        LOG.info("DBConnectionPoolSingleton.setDBConnectionPoolFor end: " + db.getConnectionName());
        return ds;
    }

}
