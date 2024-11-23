package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// TODO javadoc
interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> getAllTrainingsForUserWithId(Long id) {
        return findAll()
                .stream()
                .filter(training -> training.getUser().getId().equals(id))
                .toList();
    }

    default List<Training> getTrainingsEndedAfterDate(Date date) {
        return findAll()
                .stream()
                .filter(training -> training.getEndTime().after(date))
                .toList();
    }

    default List<Training> getTrainingsByActivity(ActivityType activityType) {
        return findAll()
                .stream()
                .filter(training -> training.getActivityType().equals(activityType))
                .toList();
    }

    default List<Training> getTrainingsByUserIdForMonth(Long id, Date start, Date end) {
        return findAll().stream()
                .filter(training -> training.getUser().getId().equals(id)
                        && training.getEndTime().after(start)
                        && training.getEndTime().before(end)
                )
                .toList();
    }
}
