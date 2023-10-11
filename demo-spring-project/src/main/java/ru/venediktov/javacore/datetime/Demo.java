package ru.venediktov.javacore.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Demo {

    public static void main(String[] args) {
        Instant arrivalPlanDate = Instant.parse("2021-10-15T06:00:00Z");
        System.out.println("Primary Instant " + arrivalPlanDate);

        LocalDateTime localDateTimeUTC = LocalDateTime.ofInstant(arrivalPlanDate, ZoneOffset.UTC);
        System.out.println("LocalDateTime UTC-0 " + localDateTimeUTC);

        ZonedDateTime zonedDateTime = localDateTimeUTC.atZone(ZoneId.of("Asia/Novosibirsk"));
        System.out.println("ZonedDateTime в часовом поясе юзера " + zonedDateTime);

        ZonedDateTime serverZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("ZonedDateTime в часовом поясе сервера " + serverZonedDateTime);

        LocalDateTime localDateTimeServer = serverZonedDateTime.toLocalDateTime();
        System.out.println("ZonedDateTime в часовом поясе сервера " + localDateTimeServer);

        System.out.println("\nОтдельный пример:");
        DateTime.dateConversion();
    }
}
