package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// TODO javadoc
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        log.info("Getting all trainings");
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsForUserWithId(Long id) {
        log.info("Getting all trainings for user with id:" + id);
        return trainingRepository.getAllTrainingsForUserWithId(id);
    }

    @Override
    public List<Training> getTrainingsEndedAfterDate(Date date) {
        log.info("Getting all trainings ended after date:" + date);
        return trainingRepository.getTrainingsEndedAfterDate(date);
    }

    @Override
    public List<Training> getTrainingsByActivity(ActivityType activityType) {
        log.info("Getting all trainings with activity type:" + activityType);
        return trainingRepository.getTrainingsByActivity(activityType);
    }

    @Override
    public Training createTraining(CreateOrUpdateTrainingDto createDto) {
        log.info("Creating new training");
        User user = userRepository.findById(createDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(createDto.getUserId()));
        return trainingRepository.save(createTrainingFromDtoWithUser(createDto, user));
    }

    @Override
    public Training updateTraining(Long id, CreateOrUpdateTrainingDto updateDto) {
        log.info("Updating training");
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));
        if (updateDto.getUserId() != null) {
            User user = userRepository.findById(updateDto.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(updateDto.getUserId()));
            return trainingRepository.save(mergeNewChangesWith(training, updateDto, user));
        } else {
            return trainingRepository.save(mergeNewChangesWith(training, updateDto, null));
        }
    }

    public List<Training> getTrainingsForUserBetweenDates(Long userId, Date start, Date end) {
        return trainingRepository.getTrainingsByUserIdForMonth(userId, start, end);
    }

    private Training createTrainingFromDtoWithUser(CreateOrUpdateTrainingDto createDto, User user) {
        return new Training(
                user,
                createDto.getStartTime(),
                createDto.getEndTime(),
                createDto.getActivityType(),
                createDto.getDistance(),
                createDto.getAverageSpeed());
    }

    private Training mergeNewChangesWith(Training oldTraining, CreateOrUpdateTrainingDto updateDto, User user) {
        return new Training(
                updateDto.getUserId() != null ? user : oldTraining.getUser(),
                updateDto.getStartTime() != null ? updateDto.getStartTime() : oldTraining.getStartTime(),
                updateDto.getEndTime() != null ? updateDto.getEndTime() : oldTraining.getEndTime(),
                updateDto.getActivityType() != null ? updateDto.getActivityType() : oldTraining.getActivityType(),
                updateDto.getDistance() != null ? updateDto.getDistance() : oldTraining.getDistance(),
                updateDto.getAverageSpeed() != null ? updateDto.getAverageSpeed() : oldTraining.getAverageSpeed());
    }

}
