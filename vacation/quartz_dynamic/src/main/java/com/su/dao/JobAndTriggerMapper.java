package com.su.dao;

import com.su.entity.JobAndTrigger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAndTriggerMapper {
    List<JobAndTrigger> getJobAndTriggerDetails();
}
