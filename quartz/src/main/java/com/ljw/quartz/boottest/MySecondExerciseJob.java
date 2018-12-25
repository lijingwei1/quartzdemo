package com.ljw.quartz.boottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MySecondExerciseJob {
    private static Logger logger = LoggerFactory.getLogger(MySecondExerciseJob.class);

    public void myJobBusinessMethod(){
        logger.info("第二个任务---------------");
    }
}
