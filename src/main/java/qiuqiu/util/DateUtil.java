package qiuqiu.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jing Tao
 * @date 2025/10/10 11:04
 */
public class DateUtil {

    public static String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getToday());
    }
}
