package com.vertback.backend.datastore.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by HW on 10/4/16.
 */
public class ReadJson {

    private static Logger logger = Logger.getLogger(ReadJson.class.getName());
    private static String DATA_STORE_PACKAGE = "/json/";
    private static ReadJson readJson=null;

    public static ReadJson getInstance(){
        if(readJson == null){
            readJson = new ReadJson();
        }
        return  readJson;
    }
    private ReadJson(){
    }


    public  String readJsonByFileName(InputStream input) {
        String jsonResult = "";
        if(input != null) {
            try {
                String thisLine;
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
                while ((thisLine = inputReader.readLine()) != null) {
                    jsonResult = jsonResult + thisLine;
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "", e);
            }
        }
        return jsonResult;
    }


    public InputStream getFileInputStreamInJar(String fileName){
        InputStream input = null;
        try {
            input = getClass().getResourceAsStream(DATA_STORE_PACKAGE + fileName);
        }
        catch(Exception e){
            logger.log(Level.SEVERE, "", e);
        }
        return input;
    }
}
