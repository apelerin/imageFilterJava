package org.axel.imageFilterJava;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logger {
    /**
     *
     * @param message we want to log
     * @throws IOException
     */
    public void log(String message) throws IOException {
        FileWriter myWriter = new FileWriter("filter.log", true);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        myWriter.write(date + message);
        myWriter.close();
    }
}
