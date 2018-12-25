package com.ljw.quartz.boottest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * Spring Scheduler Cron表达式
 * 格式：秒 分 时 日 月 周 年（可选）
 * 字段名        允许值             允许特殊字符
 * 秒            0-59                ,-/*
 * 分            0-59                ,-/*
 * 时            0-23                ,-/*
 * 日            1-31                ,-/*？L W C
 * 月            1-12 JAN-DEC        ,-/*
 * 周几          1-7 SUN-SAT         ,-*?/L C#
 * 年（可选）    empty               1970-2099,-/*
 * *:代表所有可能的值。在Month中表示每个月，在Day中表示每天，在Hours中表示每小时
 * -:表示指定范围
 * ,:表示列出枚举值。在Minutes中，5,20 表示在5分钟和20分钟触发
 * /：被用于指定增量。如Minutes中 0/15 表示从0分钟开始，每15分钟执行一次
 * ?:用在Day-of-Month和Day-of-Week中，指没有具体的指。当两个子表达式其中一个被指定了值以后，为了避免冲突，需要将另外一个的值设为?
 * L:用在day-of-month和day-of-week字串中，是last的缩写
 *      在day-of-month中，“L”表示一个月的最后一天，1月31号
 *      在day-of-week中，“L”表示一个星期的最后一天，7 或者  SAT
 *      若L前有具体内容，6L表示这个月的倒数第六天，FRIL表示这个月的最后一个星期五
 * W ：“Weekday”的缩写。只能用在day-of-month字段。用来描叙最接近指定天的工作日（周一到周五）。例如：在day-of-month字段用“15W”指“最接近这个月第15天的工作日”，即如果这个月第15天是周六，那么触发器将会在这个月第14天即周五触发；如果这个月第15天是周日，那么触发器将会在这个月第 16天即周一触发；如果这个月第15天是周二，那么就在触发器这天触发。注意一点：这个用法只会在当前月计算值，不会越过当前月。“W”字符仅能在 day-of-month指明一天，不能是一个范围或列表。也可以用“LW”来指定这个月的最后一个工作日，即最后一个星期五。
 * # ：只能用在day-of-week字段。用来指定这个月的第几个周几。例：在day-of-week字段用"6#3" or "FRI#3"指这个月第3个周五（6指周五，3指第3个）。如果指定的日期不存在，触发器就不会触发。
 *
 */
@Configuration
public class QuartzJobConfig {

    @Bean(name = "myFirstExerciseJobBean")
    public MethodInvokingJobDetailFactoryBean myFirstExerciseJobBean(MyFirstExerciseJob myFirstExerciseJob){
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false);//是否支持并发
        jobDetail.setName("general-myFirstExerciseJob");//任务名字
        jobDetail.setGroup("general");//任务的分组
        jobDetail.setTargetObject(myFirstExerciseJob);//被执行对象
        jobDetail.setTargetMethod("myJobBusinessMethod");//被执行方法
        return jobDetail;
    }
    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "myFirstexerciseJobTrigger")
    public CronTriggerFactoryBean myFirstExerciseJobTrigger(@Qualifier("myFirstExerciseJobBean")MethodInvokingJobDetailFactoryBean myFirstExerciseJobBean){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(myFirstExerciseJobBean.getObject());
        trigger.setCronExpression("0 * * * * ?");// 什么是否触发，Spring Scheduler Cron表达式
        trigger.setName("general-myFirstExerciseJobTrigger");
        return trigger;
    }
    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "mySecondExerciseJobBean")
    public MethodInvokingJobDetailFactoryBean mySecondExerciseJobBean(MySecondExerciseJob mySecondExerciseJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false); // 是否并发
        jobDetail.setName("general-mySecondExerciseJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setTargetObject(mySecondExerciseJob); // 被执行的对象
        jobDetail.setTargetMethod("myJobBusinessMethod"); // 被执行的方法
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "mySecondExerciseJobTrigger")
    public CronTriggerFactoryBean mySecondExerciseJobTrigger(@Qualifier("mySecondExerciseJobBean") MethodInvokingJobDetailFactoryBean mySecondExerciseJobDetailFactoryBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(mySecondExerciseJobDetailFactoryBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-mySecondExerciseJobTrigger");
        return tigger;
    }
}


































































