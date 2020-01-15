package TimerandSchedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloQuartz {

    public static void main(String[] args) throws SchedulerException {

        //构建SchedulerFactory实例
        SchedulerFactory schedFact = new StdSchedulerFactory();
        //获取Scheduler实例
        Scheduler scheduler = schedFact.getScheduler();
        //jobdetail 实例
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("hellojob","hello").build();


        //构建Trigger实例
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("helloTrigger", "hello")//给trigger命名并分组
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        //将JobDetail实例和Trigger实例加入到调度容器
        scheduler.scheduleJob(jobDetail, trigger);
        //启动容器
        scheduler.start();
    }
}
