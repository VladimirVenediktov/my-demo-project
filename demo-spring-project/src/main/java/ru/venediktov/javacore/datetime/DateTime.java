package ru.venediktov.javacore.datetime;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTime {

    // TODO рефакторинг
    public static void datetime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println( "LocalDateTime: " + now);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZoneDateTime: " + zonedDateTime);

        Clock clk = Clock.systemDefaultZone();
        System.out.println("Clock: " + ZonedDateTime.ofInstant(clk.instant(), clk.getZone()));

        Date date = new Date(2021, 04,12, 00, 00, 00);
        date.setTime(Instant.now().toEpochMilli());
        System.out.println("Date: " + date);

        /*Date date = new Date();
        System.out.println("Date = " + date.toString() + " ms: " + date.getTime());
        Instant instant = date.toInstant();
        System.out.println("Instant = " + instant + " ms: " + instant.toEpochMilli());*/

        // RegEX
        Pattern pattern = Pattern.compile("^V.*a$");
        Matcher matcher = pattern.matcher("Vova");
        System.out.println(matcher.find());

        Pattern pattern2 = Pattern.compile("[Vavo]");
        String text = "Vova";
        Matcher matcher2 = pattern2.matcher(text);
        while (matcher2.find()) {
            System.out.println(text.substring(matcher2.start(), matcher2.end()));
        }

    }

    // план. дата-время встречи у Отгрузки (Склад)
    public static void dateConversion() {
        final Instant arrivalPlanDate = Instant.parse("2021-10-15T06:00:00Z");
        final int moscowZoneOffset = 3;

        System.out.println("При сохранении из ЗНО:");
        System.out.println("Primary Instant " + arrivalPlanDate);
        LocalDateTime localDateTimeUTC = LocalDateTime.ofInstant(arrivalPlanDate, ZoneOffset.UTC);
        System.out.println("LocalDateTime UTC-0 " + localDateTimeUTC);

        OffsetDateTime offsetDateTime = localDateTimeUTC.atOffset(ZoneOffset.ofHours(3 + 4));
        System.out.println("OffsetDateTime (atOffset) " + offsetDateTime);
        System.out.println("Instant from offsetDateTime " + offsetDateTime.toInstant());

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(arrivalPlanDate, ZoneOffset.ofHours(3 + 4));
        System.out.println("ZoneDateTime " + zonedDateTime);
        Instant instantFromZDT = zonedDateTime.toInstant();
        System.out.println("Instant for ZoneDateTime " + instantFromZDT);

        System.out.println("Date from Instant from ZoneDateTime " + Date.from(instantFromZDT));

        //с фронта
        LocalDate localDate = LocalDate.of(2021, 11, 02);
        System.out.println("\nПри проставлении с фронта:" + localDate);
        //из утилит Склада
        //DateUtils dateUtils = new DateUtils();
        //Branch branch = new Branch("270");
        //branch.setTimeZoneMsk(4);
        //Date dateFromLocalDate = DateUtils.dateFromLocalDate(localDate, branch);
        //System.out.println("Через утилитный класс " + dateFromLocalDate);
        OffsetDateTime offsetDateTime1 =
                localDate.atTime(00, 00).atOffset(ZoneOffset.ofHours(moscowZoneOffset + 4));
        System.out.println("OffsetDateTime (atOffset) " + offsetDateTime1);
        System.out.println("Instant for OffsetDateTime " + offsetDateTime1.toInstant());
        System.out.println("Date from Instant from OffsetDateTime" + Date.from(offsetDateTime1.toInstant()));

        //ZoneDateTime для вывода в модальное окно времен (при проставлении дат у Отгрузки):
        System.out.println("\nZonedDateTime");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("UTC+3"));
        System.out.println(zonedDateTime1);
        ZonedDateTime zonedDateTime2 = zonedDateTime1.plusHours(4);
        System.out.println(zonedDateTime2);
        System.out.println("Через ZoneDateTime: " + zonedDateTime2.toLocalDateTime());

        System.out.println("Сразу черех LocalDateTime: " + LocalDateTime.now(ZoneOffset.ofHours(7)));
    }
}
