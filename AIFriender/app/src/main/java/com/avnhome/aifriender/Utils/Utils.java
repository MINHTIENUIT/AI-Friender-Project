package com.avnhome.aifriender.Utils;

import java.util.Calendar;

public class Utils {
    public static int AgeCalculator(long birthDate){
        Calendar birth = Calendar.getInstance();
        birth.setTimeInMillis(birthDate);

        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());

        return now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
    }
}
