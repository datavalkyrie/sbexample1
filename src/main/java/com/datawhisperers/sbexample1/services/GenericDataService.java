/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.sbexample1.services;

import com.datawhisperers.sbexample1.databaseconnection.DBConnectionPoolSingleton;
import com.datawhisperers.sbexample1.model.GenericResultsOutput;
import com.datawhisperers.sbexample1.model.MetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author steveo
 */
@Service
public class GenericDataService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GenericDataService.class);

    public GenericResultsOutput runQueryResults(
            String dbName,
            HashMap<String, ArrayList<String>> requestParams) {

        GenericResultsOutput genericResultsOutput = new GenericResultsOutput();
        genericResultsOutput.setName("GenericDataService.runQueryCount");
        genericResultsOutput.setRequestParams(requestParams);
        genericResultsOutput.setDbConnectionName(dbName);
        genericResultsOutput.setSql(requestParams.get("sql").get(0));

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            connection = DBConnectionPoolSingleton.getDBConnectionPoolSingleton().getDBConnectionFor(dbName);
            LOG.info("RequestParams: " + requestParams.size());

            pstmt = connection.prepareStatement(requestParams.get("sql").get(0));

            rs = pstmt.executeQuery();

            int rowCount = 0;
            Map<String, Integer> metaDataSet = new HashMap<>();
            this.getMetaData(rs, metaDataSet, genericResultsOutput);
            while (rs.next()) {

                List<Object> resultsArrayList = new ArrayList<>();
                this.resultsWithoutMetaData(rs, genericResultsOutput, resultsArrayList);
                genericResultsOutput.getResults().add(resultsArrayList);

                rowCount++;
            }

            genericResultsOutput.setCount(rowCount);
            genericResultsOutput.setStatus("OK");
        } catch (Exception e) {
            LOG.error("GenericDataService.runQueryResults(): " + e.getMessage(), e);
            genericResultsOutput.setMessage(e.getMessage());
            genericResultsOutput.setStatus("ERROR");
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                LOG.error("GenericDataService.runQueryResults() Close result set: " + e.getMessage(), e);
            }
            try {
                pstmt.close();
            } catch (Exception e) {
                LOG.error("GenericDataService.runQueryResults() Close prepared statement: " + e.getMessage(), e);
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOG.error("GenericDataService.runQueryResults() Close connection: " + e.getMessage(), e);
            }
        }

        return genericResultsOutput;
    }

    private void getMetaData(ResultSet rs, Map<String, Integer> metaDataSet, GenericResultsOutput genericResultsOutput) throws SQLException {

        LOG.info(">>>>Starting getMetaData");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<MetaData> metaDataArrayList = genericResultsOutput.getMetaData();

        for (int i = 1; i <= columnCount; i++) {
            metaDataSet.put(rsmd.getColumnName(i), Integer.valueOf(i));
            Map<String, Object> columnAttributes = new HashMap<>();
            columnAttributes.put("ColumnTypeName", rsmd.getColumnTypeName(i));
            columnAttributes.put("IndexNumber", i);
            MetaData metaData = new MetaData();
            metaData.setColumnName(rsmd.getColumnName(i));
            metaData.setColumnAttributes(columnAttributes);

            LOG.info("getColumnType : " + rsmd.getColumnType(i));
            LOG.info("getColumnTypeName : " + rsmd.getColumnTypeName(i));
            LOG.info("index : " + i);

            metaDataArrayList.add(metaData);
        }
        LOG.info(">>>>Ending getMetaData");
    }

    private void resultsWithoutMetaData(
            ResultSet rs,
            GenericResultsOutput genericResultsOutput,
            List<Object> resultsArrayList) throws SQLException {

        for (int i = 1; i <= genericResultsOutput.getMetaData().size(); i++) {
            resultsArrayList.add(rs.getObject(i));
        }

    }

}
