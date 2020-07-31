package com.su.jobs;

import com.github.pagehelper.PageInfo;
import com.su.entity.JobAndTrigger;
import com.su.service.BaseJob;
import com.su.service.JobAndTriggerService;
import com.su.util.SpringUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("helloJob")
public class HelloJob implements BaseJob {
//    private static Logger log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobAndTriggerService jobAndTriggerService = (JobAndTriggerService) SpringUtil.getBean("jobAndTriggerServiceImpl");

        PageInfo<JobAndTrigger> jobAndTriggerDetails = jobAndTriggerService.getJobAndTriggerDetails(1, 10);
        System.out.println("--------------------------------------------");
        System.out.println(jobAndTriggerDetails.getTotal());
        System.out.println("-----------------------------------------------");
//        log.info("Hello Job执行时间: " + new Date());
        System.out.println("Hello Job执行: " + new Date());
    }
}
