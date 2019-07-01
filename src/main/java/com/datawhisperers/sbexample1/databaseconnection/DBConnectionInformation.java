/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.sbexample1.databaseconnection;

/**
 *
 * @author steveo
 */
public class DBConnectionInformation {
    
    private String connectionName;
    private String connectionURL;
    private String connectionDriver;
    private String connectionUsername;
    private String connectionPassword;

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getConnectionDriver() {
        return connectionDriver;
    }

    public void setConnectionDriver(String connectionDriver) {
        this.connectionDriver = connectionDriver;
    }

    public String getConnectionUsername() {
        return connectionUsername;
    }

    public void setConnectionUsername(String connectionUsername) {
        this.connectionUsername = connectionUsername;
    }

    public String getConnectionPassword() {
        return connectionPassword;
    }

    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }

    @Override
    public String toString() {
        return "DBConnectionInformation{" + "connectionName=" + connectionName + ", connectionURL=" + connectionURL + ", connectionDriver=" + connectionDriver + ", connectionUsername=" + connectionUsername + ", connectionPassword=" + connectionPassword + '}';
    }
    
    
    
    
}
