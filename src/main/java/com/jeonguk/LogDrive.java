package com.jeonguk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogDrive extends Thread {

    private String logNum;
    private String toDay;

    private Logger logger = LogManager.getLogger(this.getName());

    private LogDriveInfo cInfo = new LogDriveInfo();

    public LogDrive(String logNum, String toDay, boolean isAdd) {
        this.logNum = logNum;
        this.toDay = toDay;
        cInfo.setLogNum(logNum);
        cInfo.setIsAdd(isAdd);
        cInfo.setAreaTypeNum(LogMakerMain.randomRange(0,5));

        System.setProperty("logFilename", "./log-generator/realtime-log.log");

        org.apache.logging.log4j.core.LoggerContext ctx =
                (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        ctx.reconfigure();

    }

    @Override
    public void run() {

        int count = 86400;
        for(int i=0; i <= count ; i++) {
            if(i==0) {
                logger.info("Driver Status Infomation"
                        + ",CarNum"

                        + ",AccStep"
                        + ",BrkStep"
                        + ",WheelStep"

                        + ",DirLightStep"
                        + ",Speed"
                        + ",AreaNum");

            }
            logger.info(toDay + LogMaker.getSecToTime(i)
                    + "," + cInfo.getLogNum()

                    + "," + cInfo.getAccStep()
                    + "," + cInfo.getBrkStep()
                    + "," + cInfo.getWheelStep()

                    + "," + cInfo.getDirLightStep()
                    + "," + cInfo.getSpeed()
                    + "," + cInfo.getAreaNum());
            try {
                sleep(1 * 100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i+=1;
        }

    }

}
