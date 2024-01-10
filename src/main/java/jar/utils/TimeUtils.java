package jar.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@UtilityClass
@Slf4j
public class TimeUtils {
    public static Long getDateStart(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date formattedDate = df.parse(date);
            return formattedDate.getTime();
        } catch (ParseException e){
            log.error("Exception while TimeUtils getDateStart : {} ", e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    public static Long getDateEnd(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date formattedDate = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(df.format(formattedDate)));
            c.add(5, 1);
            c.add(14, -1);
            return c.getTime().getTime();
        } catch (ParseException e) {
            log.error("Exception while TimeUtils getDateEnd : {} ", e.getStackTrace());
            throw new RuntimeException(e);
        }
    }
}
