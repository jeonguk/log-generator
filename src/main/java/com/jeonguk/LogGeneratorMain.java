package com.jeonguk;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Running Background
 * mvn clean install
 * cd target/
 * java -cp com.jeonguk.log-1.0-SNAPSHOT.jar com.jeonguk.LogGeneratorMain YYYYMMDD 3 &
 */
public class LogGeneratorMain {

    public static void main(String[] args) {
        String toDay = getToDate();

        int count = 100;
        if(args != null ) toDay = args[0];
        if(args != null ) count = Integer.parseInt(args[1]);

        ExecutorService exc = Executors.newFixedThreadPool(count);

        for(int i=1; i <= count ; i++) {
            exc.submit(new LogMaker( getLogNum(i), toDay));
        }
    }

    private static String getLogNum(int num) {
        final String[] carNumPrefix = {"A", "B" , "C" , "D" , "E" , "F", "G", "H", "I", "J", "K", "L", "M", "N"
                , "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String prefixNum = carNumPrefix[getRandomNumberInRange()] ;
        DecimalFormat format = new DecimalFormat("0000");
        String carNum = format.format(num);
        return prefixNum + carNum;
    }

    // 0 ~ 25
    private static int getRandomNumberInRange() {
        final Random r = new Random();
        return r.nextInt((25) + 1);
    }

    private static String getToDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
