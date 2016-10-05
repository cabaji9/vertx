package com.vertback.backend.daoImpl;

import com.vertback.backend.datastore.reader.ReadJson;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by HW on 10/4/16.
 */
public class GenericDaoImpl<T> {

    private static Logger logger = Logger.getLogger(GenericDaoImpl.class.getName());

    Gson gson = new Gson();

    public List<T> obtainFromJsonList(String jsonResult,Class<T[]> klass){
        List<T> list = null;
        try {
            T[] userArray = gson.fromJson(jsonResult, klass);
            list = Arrays.asList(userArray);
        }
        catch(Exception e){
            logger.log(Level.SEVERE,"",e);
        }
        return list;
    }


    protected String obtainJsonResult(String jsonFileName){
        ReadJson readJson = ReadJson.getInstance();
        InputStream input = readJson.getFileInputStreamInJar(jsonFileName);
        return readJson.readJsonByFileName(input);
    }

}
