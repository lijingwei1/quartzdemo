package com.ljw.quartz.boottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MyFirstExerciseJob {
    private static Logger logger = LoggerFactory.getLogger(MyFirstExerciseJob.class);

    public void myJobBusinessMethod(){
        logger.info("第一个任务---------------");
    }
}
