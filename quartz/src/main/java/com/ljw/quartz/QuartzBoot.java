package com.ljw.quartz;

import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class QuartzBoot {
    private static Logger logger = LoggerFactory.getLogger(QuartzBoot.class);

    public static void main(String[] args) {
        try{
            //获取调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //开启调度器
            /**
             * 在调用scheduler.start()启动调度器后，可以使用scheduler.standby()将调度器转为待机状态，此状态下任务和触发器不会被触发
             * scheduler.shutdown()关闭调度器，是不可逆的，即调用后是不可以重新开始的
             * scheduler.shutdown() = scheduler.shutdown(false),方法会马上返回，正在执行的任务会继续执行
             * scheduler.shutdown(true)直到正在执行的任务执行完成才返回
             */
            scheduler.start();
            //注册一个示例任务和触发器
            registerJobAndTrigger(scheduler);
        }catch(SchedulerException se){
            logger.error("调度器初始化异常", se);
        }
    }
    /**
     * 注册一个任务和触发器
     * @param scheduler
     */
    public static void registerJobAndTrigger(Scheduler scheduler){
        JobDetail job = JobBuilder.newJob(MySimpleJob.class)
                .withIdentity("mySimpleJob", "simpleGroup")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("simpleTrigger", "simpleGroup")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            logger.error("注册任务和触发器失败", e);
        }
    }
    /**
     * 任务
     */
    public static class MySimpleJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            logger.info("执行了");
        }
    }
}
































