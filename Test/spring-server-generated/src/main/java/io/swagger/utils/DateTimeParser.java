package io.swagger.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeParser {
    @Autowired
    static DateTimeUtils dateTimeUtils;

    public OffsetDateTime parseFromSqlTimeStamp(Timestamp timestamp){
        Calendar c = new GregorianCalendar();
        c.setTime(timestamp);
        ZonedDateTime zonedDateTime = dateTimeUtils.toZonedDateTime(c);
        return  OffsetDateTime.ofInstant(zonedDateTime.toInstant(),zonedDateTime.getZone());
    }
}
