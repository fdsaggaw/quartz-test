package com.su.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.su.entity.ScheduleJob;
import com.su.enums.JobOperateEnum;
import com.su.mapper.ScheduleJobMapper;
import com.su.service.QuartzService;
import com.su.service.ScheduleJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper,ScheduleJob> implements ScheduleJobService {
    @Autowired
    private QuartzService quartzService;

    @Override
    public void add(ScheduleJob job) {

        //此处省去数据验证
        this.save(job);

        //加入job
        try {
            quartzService.addJob(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(int id) {
        //此处省去数据验证
        ScheduleJob job = this.getById(id);
        job.setStatus(1);
        this.updateById(job);

        //执行job
        try {
            quartzService.operateJob(JobOperateEnum.START, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause(int id) {
        //此处省去数据验证
        ScheduleJob job = this.getById(id);
        job.setStatus(2);
        this.updateById(job);

        //执行job
        try {
            quartzService.operateJob(JobOperateEnum.PAUSE, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        //此处省去数据验证
        ScheduleJob job = this.getById(id);
        this.removeById(id);

        //执行job
        try {
            quartzService.operateJob(JobOperateEnum.DELETE, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startAllJob() {
        //此处省去数据验证
        ScheduleJob job = new ScheduleJob();
        job.setStatus(1);
        this.update(job, new QueryWrapper<>());

        //执行job
        try {
            quartzService.startAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseAllJob() {
        //此处省去数据验证
        ScheduleJob job = new ScheduleJob();
        job.setStatus(2);
        this.update(job, new QueryWrapper<>());

        //执行job
        try {
            quartzService.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
