package com.su.jobs;

import com.su.service.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("newJob")
public class NewJob implements BaseJob {
//    private static Logger log = LoggerFactory.getLogger(NewJob.class);

    public NewJob() {

    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        log.info("Hello Job执行时间: " + new Date());
        System.out.println("Hello Job执行时间: " + new Date());
    }
}
