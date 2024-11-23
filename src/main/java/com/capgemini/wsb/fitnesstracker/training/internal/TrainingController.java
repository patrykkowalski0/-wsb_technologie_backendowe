package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

// TODO javadoc
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::fromTraining)
                .toList();
    }

    @GetMapping("/{id}")
    public List<TrainingDto> getTrainingsForUserWithId(@PathVariable("id") Long id) {
        return trainingService.getTrainingsForUserWithId(id)
                .stream()
                .map(trainingMapper::fromTraining)
                .toList();
    }

    @GetMapping("/finished/{afterDate}")
    public List<TrainingDto> getTrainingsEndedAfterDate(@PathVariable("afterDate") String afterDate) {
        Date date = Date.from(LocalDate.parse(afterDate).atStartOfDay(ZoneId.of("UTC")).toInstant());
        return trainingService.getTrainingsEndedAfterDate(date)
                .stream()
                .map(trainingMapper::fromTraining)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivity(@RequestParam("activityType") ActivityType activityType) {
        return trainingService.getTrainingsByActivity(activityType)
                .stream()
                .map(trainingMapper::fromTraining)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody CreateOrUpdateTrainingDto createTrainingDto) {
        return trainingMapper.fromTraining(
                trainingService.createTraining(createTrainingDto)
        );
    }

    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable("id") Long trainingId, @RequestBody CreateOrUpdateTrainingDto updateTrainingDto) {
        return trainingMapper.fromTraining(
                trainingService.updateTraining(trainingId, updateTrainingDto)
        );
    }
}
