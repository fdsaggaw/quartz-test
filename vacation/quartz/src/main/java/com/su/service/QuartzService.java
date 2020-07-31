package com.su.service;

import com.su.entity.ScheduleJob;
import com.su.enums.JobOperateEnum;
import org.quartz.SchedulerException;

import java.util.Map;

public interface QuartzService {

    /**
     * 服务器启动执行定时任务
     *
     *
     */
    void timingTask();

    /**
     * 新增定时任务
     *
     *
     * @param job 任务
     */
    void addJob(ScheduleJob job);

    /**
     * 操作定时任务
     *
     *
     * @param jobOperateEnum 操作枚举
     * @param job 任务
     */
    void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob job) throws SchedulerException;

    /**
     * 启动所有任务
     *
     *
     */
    void startAllJob() throws SchedulerException;

    /**
     * 暂停所有任务
     *
     *
     */
    void pauseAllJob() throws SchedulerException;
}
