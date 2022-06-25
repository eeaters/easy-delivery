package io.eeaters.base;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampTest {

    @Test
    public void test() {
        Date date = new Date(1611139080951l);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        System.out.println("format = " + format);

    }

}
