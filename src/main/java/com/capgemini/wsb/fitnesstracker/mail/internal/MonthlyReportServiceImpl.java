package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

import java.util.Calendar;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MonthlyReportServiceImpl {

    private final TrainingServiceImpl trainingService;
    private final UserServiceImpl userService;
    private final EmailSender emailSender;

    @Scheduled(cron = "0 * * * * *")
    public void generate() {
        List<User> users = userService.findAllUsers();

        for (User user : users) {
            List<Training> trainingsInMonth = trainingService.getTrainingsForUserBetweenDates
                    (user.getId(), getFirstDayOfTheMonth(), getLastDayOfTheMonth());

            EmailDto email = new EmailDto(
                    user.getEmail(),
                    "Monthly Training Report from Fitness Tracker WSB",
                    trainingsInMonth.toString()
            );

            emailSender.send(email);
        }


    }

    private Date getFirstDayOfTheMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    private Date getLastDayOfTheMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

}
