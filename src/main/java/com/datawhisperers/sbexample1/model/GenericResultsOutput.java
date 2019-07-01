/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.sbexample1.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 *
 */
public class GenericResultsOutput {

    private String name;
    private long processTimeMillis;
    private String status;
    private String message;
    private HashMap<String, ArrayList<String>> requestParams;
    private long count;
    private String sql;
    private String dbConnectionName;
    private List<MetaData> metaData;
    private List<List<Object>> results;

    public GenericResultsOutput() {
        this.metaData = new ArrayList<MetaData>();
        this.results = new ArrayList<List<Object>>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProcessTimeMillis() {
        return processTimeMillis;
    }

    public void setProcessTimeMillis(long processTimeMillis) {
        this.processTimeMillis = processTimeMillis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, ArrayList<String>> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(HashMap<String, ArrayList<String>> requestParams) {
        this.requestParams = requestParams;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDbConnectionName() {
        return dbConnectionName;
    }

    public void setDbConnectionName(String dbConnectionName) {
        this.dbConnectionName = dbConnectionName;
    }

    public List<MetaData> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<MetaData> metaData) {
        this.metaData = metaData;
    }

    public List<List<Object>> getResults() {
        return results;
    }

    public void setResults(List<List<Object>> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GenericResultsOutput{" + "name=" + name + ", processTimeMillis=" + processTimeMillis + ", status=" + status + ", message=" + message + ", requestParams=" + requestParams + ", count=" + count + ", sql=" + sql + ", dbConnectionName=" + dbConnectionName + ", metaData=" + metaData + ", results=" + results + '}';
    }

}
