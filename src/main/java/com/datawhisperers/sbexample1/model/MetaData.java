/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.sbexample1.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author steveo
 */
public class MetaData {

    private String columnName;
    private Map<String, Object> columnAttributes;

    /**
     *
     */
    public MetaData() {
        this.columnAttributes = new HashMap<String, Object>();
    }

    /**
     *
     * @return
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     *
     * @param columnName
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     *
     * @return
     */
    public Map<String, Object> getColumnAttributes() {
        return columnAttributes;
    }

    /**
     *
     * @param columnAttributes
     */
    public void setColumnAttributes(Map<String, Object> columnAttributes) {
        this.columnAttributes = columnAttributes;
    }

}
