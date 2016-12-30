package com.example.handler;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by alexc_000 on 2016-12-29.
 */
public class DateTimeFieldHandler extends GeneralizedFieldHandler {
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    private static String dateFormatPattern;
    private static DateTimeFormatter dateTimeFormatter;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-time-format");
        dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormatPattern)
                .withLocale(DEFAULT_LOCALE)
                .withZone(DEFAULT_ZONE_ID);
    }

    @Override
    public Object convertUponGet(Object value) {
        Instant dateTime = (Instant) value;

        return format(dateTime);
    }

    @Override
    public Object convertUponSet(Object value) {
        String dateTimeString = (String) value;

        return parse(dateTimeString);
    }

    @Override
    public Class<Instant> getFieldType() {
        return Instant.class;
    }

    public static String format(final Instant dateTime) {
        String dateTimeString = "";

        if (Optional.ofNullable(dateTime).isPresent()) {
            dateTimeString = dateTimeFormatter.format(dateTime);
        }

        return dateTimeString;
    }

    public static Instant parse(final String dateTimeString) {
        Instant instant = Instant.MIN;

        if (Optional.ofNullable(dateTimeString).isPresent()) {
            LocalDateTime dt = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
            ZonedDateTime zdt = dt.atZone(DEFAULT_ZONE_ID);
            instant = zdt.toInstant();
        }

        return instant;
    }
}

