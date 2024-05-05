package com.mstransferenciafinanceira.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {

    public static int returnDays(LocalDate dateTransfer, LocalDate dateScheduling) {
        int days = 0;

        try {
            LocalDate anotherDate = LocalDate.of(dateTransfer.getYear(), dateTransfer.getMonth(),
                    dateTransfer.getDayOfMonth());

            Period period = Period.between(anotherDate, dateScheduling);
            days = Math.abs(period.getDays());
        } catch (Exception ex) {
            return days;
        }
        return days;
    }

    public static BigDecimal arredondarDecimal(BigDecimal valor){
        return valor.setScale(2, RoundingMode.HALF_EVEN);
    }

}
