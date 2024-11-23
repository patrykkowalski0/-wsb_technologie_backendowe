package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.CreateOrUpdateTrainingDto;

public interface TrainingService {

    Training createTraining(CreateOrUpdateTrainingDto createDto);

    Training updateTraining(Long id, CreateOrUpdateTrainingDto updateDto);

}
