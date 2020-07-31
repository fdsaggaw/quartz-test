package com.su.service;

import com.github.pagehelper.PageInfo;
import com.su.entity.JobAndTrigger;

public interface JobAndTriggerService {
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);

}
