package sergio.retrofit2test.helpers;

import java.util.Date;

/**
 * @author s.ruiz
 */

public class DateParser {

    public static String parseDate(Date date) {
        String[] dateElements = date.toString().split(" ");
        StringBuilder dateString = new StringBuilder();
        dateString.append(dateElements[1] + " ")
                .append(dateElements[2] + " of ")
                .append(dateElements[dateElements.length - 1] + " at ")
                .append(dateElements[3]);
        return dateString.toString();
    }
}