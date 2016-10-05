package com.vertback.datastore.reader;

import com.vertback.backend.daoImpl.GenericDaoImpl;
import com.vertback.backend.datastore.reader.ReadJson;
import com.vertback.backend.vo.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by HW on 10/4/16.
 */


public class ReadJsonTest {

    private static Logger logger = Logger.getLogger(ReadJsonTest.class.getName());

    @Test
    public void testReadJson() {

        InputStream input = null;
        try {
            input = new FileInputStream("/Users/User/java/codigo/personal/vertx_rest/src/main/resources/json/users.json");
        } catch (Exception e
                ) {
        }
        String jsonResult = ReadJson.getInstance().readJsonByFileName(input);
        logger.info(jsonResult);
        if(!jsonResult.isEmpty())
        {
            GenericDaoImpl<User> genericDao = new GenericDaoImpl<>();
            List<User> userList =
                    genericDao.obtainFromJsonList(jsonResult,User[].class);
            assert(!userList.isEmpty());
            logger.info(userList.get(0).getAddress());
        }
    }

}
