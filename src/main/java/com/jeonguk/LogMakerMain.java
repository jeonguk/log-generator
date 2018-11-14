package com.jeonguk;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Running Background
 * mvn clean install
 * cd target/
 * java -cp com.jeonguk.log-1.0-SNAPSHOT.jar com.jeonguk.LogMakerMain YYYYMMDD 3 &
 *
 * tail -f log-file-name
 */
public class LogMakerMain {

    public static void main(String[] args) {

        String toDay =  getToDate();

        int carCount = 100;
        if(args != null) toDay = args[0];
        if(args != null) carCount = Integer.parseInt(args[1]);

        ExecutorService exc = Executors.newFixedThreadPool(carCount);

        int wildDrivercnt = (int)(carCount * 0.1);

        Set<Integer> wildCarSet = new HashSet<>();
        for (int i = 0 ; i < wildDrivercnt; i++) {
            wildCarSet.add(randomRange(1,carCount));
        }

        Iterator<Integer> itr = wildCarSet.iterator();
        boolean isWild = false;
        int tmpWildCarNum;
        for(int i = 1; i <= carCount; i++) {
            while(itr.hasNext()) {
                tmpWildCarNum = itr.next();
                if( tmpWildCarNum == i) {
                    isWild = true; break;
                }else{
                    isWild = false;
                }
            }
            itr = wildCarSet.iterator();
            exc.submit(new LogDrive( getCarNum(i), toDay, isWild));
        }

    }

    private static String getCarNum(int num) {
        final String[] carNumPrefix = {"A", "B" , "C" , "D" , "E" , "F", "G", "H", "I", "J", "K", "L", "M", "N"
                , "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        final String prefixNum = carNumPrefix[randomRange(0, 25)] ;
        final DecimalFormat format = new DecimalFormat("0000");
        final String carNum = format.format(num);
        return prefixNum + carNum;
    }

    private static String getToDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static int randomRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
