package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

// TODO javadoc
@Data
@Builder
public class CreateOrUpdateTrainingDto {

    private Long userId;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private Double distance;
    private Double averageSpeed;
}
