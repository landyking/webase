package app.service;

import app.util.ActiveJdbcMojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/14 9:32
 * note:
 */
@Service
public class TestDataService {

    public static final Logger LOG = LoggerFactory.getLogger("TestDataInit");

    public void initTestData() {
        try (ActiveJdbcMojo mojo = new ActiveJdbcMojo()) {
            LOG.info("start init test data");

            Random rd = new Random();

            LOG.info("end init test data");
        }
    }

}
