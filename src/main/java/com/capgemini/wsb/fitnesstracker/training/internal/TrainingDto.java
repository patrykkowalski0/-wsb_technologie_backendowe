package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

// TODO javadoc
@Data
@Builder
public class TrainingDto {

    @Nullable
    private Long Id;
    private UserDto user;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

}
