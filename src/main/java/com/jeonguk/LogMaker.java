package com.jeonguk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogMaker extends Thread {

    private Logger logger = LogManager.getLogger(this.getName());
    private String logNum;
    private String toDay;

    private LogStatusInfo cInfo = new LogStatusInfo();

    public LogMaker(String logNum, String toDay ) {
        this.logNum = logNum;
        this.toDay = toDay;
        cInfo.setLogNum(logNum);
        System.setProperty("logFilename", "./log-generator/LogInfo_" + toDay + ".txt");
        org.apache.logging.log4j.core.LoggerContext ctx =
                (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        ctx.reconfigure();
    }

    @Override
    public void run() {

        int count = 86400;
        for(int i=0; i <= count ; i++) {
            if(i==0) {
                logger.info("Log Status Information"
                        + ",CarNum"

                        + ",TireFL"
                        + ",TireFR"
                        + ",TireBL"
                        + ",TireBR"

                        + ",LightFL"
                        + ",LightFR"
                        + ",LightBL"
                        + ",LightBR"

                        + ",EngineInfo"
                        + ",BreakInfo"
                        + ",BatteryInfo");
            }
            logger.info(toDay + getSecToTime(i)
                    + "," + cInfo.getLogNum()

                    + "," + cInfo.getTireFL()
                    + "," + cInfo.getTireFR()
                    + "," + cInfo.getTireBL()
                    + "," + cInfo.getTireBR()

                    + "," + cInfo.getLightFL()
                    + "," + cInfo.getLightFR()
                    + "," + cInfo.getLightBL()
                    + "," + cInfo.getLightBR()

                    + ","+ cInfo.getEngineInfo()
                    + ","+ cInfo.getBreakInfo()
                    + ","+ cInfo.getBatteryInfo());
            i+=3;
        }

    }

    public static String getSecToTime(int inSec) {
        String time = String.valueOf(inSec/3600);
        if(time.length() == 1) time = "0" + time;

        String min = String.valueOf(inSec%3600/60);
        if(min.length() == 1) min = "0" + min;

        String sec = String.valueOf(inSec%3600%60%60);
        if(sec.length() == 1) sec = "0" + sec;

        return time + min + sec;
    }

}
