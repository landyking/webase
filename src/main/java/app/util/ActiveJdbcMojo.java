package app.util;

import org.javalite.activejdbc.Base;

/**
 *  <br/>
 *
 * @author: landy
 * @date: 2017/2/18 11:43
 * note:
 */
public class ActiveJdbcMojo implements AutoCloseable {
    private boolean hasConnection = false;

    public ActiveJdbcMojo() {
        hasConnection = Base.hasConnection();
        if (!hasConnection) {
            Base.open();
        }
    }

    @Override
    public void close() {
        if (!hasConnection) {
            Base.close();
        }
    }
}
