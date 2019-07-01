/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.sbexample1.controllers;

import com.datawhisperers.sbexample1.databaseconnection.DBConnectionInformation;
import com.datawhisperers.sbexample1.databaseconnection.DBConnectionPoolSingleton;
import com.datawhisperers.sbexample1.model.GenericResultsOutput;
import com.datawhisperers.sbexample1.services.GenericDataService;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/db-rest")
@Api(value="db connection rest", description="Operations pertaining to products in Online Store")
public class DBController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DBController.class);

    @Autowired
    GenericDataService genericDataService;

    GsonBuilder gsonBuilder = new GsonBuilder();

    @GetMapping("createconnection/{dbName}")
    public String createconnection(@PathVariable String dbName,
            @RequestParam(value = "driver") String driver,
            @RequestParam(value = "url") String url,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) throws SQLException {

        long starttime = System.currentTimeMillis();
        LOG.info("DBController.createconnection() Start: ");

        Gson gson = gsonBuilder.setPrettyPrinting().create();

        DBConnectionInformation dbConnectionInformation = new DBConnectionInformation();

        dbConnectionInformation.setConnectionName(dbName);
        dbConnectionInformation.setConnectionDriver(driver);
        dbConnectionInformation.setConnectionURL(url);
        dbConnectionInformation.setConnectionUsername(username);
        dbConnectionInformation.setConnectionPassword(password);

        DBConnectionPoolSingleton.getDBConnectionPoolSingleton().setDBConnectionFor(dbConnectionInformation);

        long endtime = System.currentTimeMillis() - starttime;
        LOG.info("DBController.createconnection() End: " + endtime);

        return gson.toJson(dbConnectionInformation.toString());
    }

    @GetMapping("/sqlstmt/{dbName}")
    public String sqlstmt(@PathVariable String dbName, @RequestParam String sql) throws SQLException {
        
       HashMap<String, ArrayList<String>> requestParams = new HashMap();
       ArrayList list = new ArrayList<String>();
       list.add(sql);
       requestParams.put("sql",list);

        long starttime = System.currentTimeMillis();
        LOG.info("DBController.sqlstmt() Start: ");

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        GenericResultsOutput genericResultsOutput = genericDataService.runQueryResults(dbName, requestParams);

        long endtime = System.currentTimeMillis() - starttime;
        LOG.info("DBController.sqlstmt() : " + endtime);

        genericResultsOutput.setProcessTimeMillis(endtime);
        LOG.info(genericResultsOutput.toString());

        return gson.toJson(genericResultsOutput);
    }

}
