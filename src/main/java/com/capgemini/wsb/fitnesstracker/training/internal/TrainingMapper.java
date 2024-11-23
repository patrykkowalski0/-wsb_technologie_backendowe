package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// TODO javadoc
@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final UserMapper userMapper;

    public TrainingDto fromTraining(Training training) {
        return TrainingDto.builder()
                .Id(training.getId())
                .user(userMapper.toDto(training.getUser()))
                .startTime(training.getStartTime())
                .endTime(training.getEndTime())
                .distance(training.getDistance())
                .averageSpeed(training.getAverageSpeed())
                .activityType(training.getActivityType())
                .build();
    }

}