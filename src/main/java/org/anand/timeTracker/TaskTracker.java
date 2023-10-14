package org.anand.timeTracker;

import org.anand.timeTracker.model.*;
import org.anand.timeTracker.utils.FilesUtility;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TaskTracker {

    static Map<String, Task> tasks = new HashMap<>();

    public static void startTask(Task task) throws IOException {
        if(tasks.putIfAbsent(task.getName(), task) != null){
            Logger.log("Task already exists, skipping");
        };
        FilesUtility.writeTasksToFile(tasks.values().stream().toList());
    }

    public static void completeTask(String taskName) throws IOException {
        if(tasks.get(taskName) != null){
            Duration initialDuration = tasks.get(taskName).getTimeSpent();
            LocalDateTime startTime = tasks.get(taskName).getStartTime();
            tasks.get(taskName).setTaskStatus(TaskStatus.COMPLETED);
            tasks.get(taskName).setEndTime(LocalDateTime.now());
//            tasks.get(taskName).setTimeSpent(initialDuration.addTo(Duration.between(startTime, LocalDateTime.now())));
        } else{
            Logger.log("No such task found!");
        }
        FilesUtility.writeTasksToFile(tasks.values().stream().toList());
    }

    public static Map<String, Task> getAllTasks(){
        return tasks;
    }

    public static void getTaskDuration(){
        Map<String, Report> tasksReport = new HashMap<>();
        tasks.values().forEach(task -> {
            Duration taskDuration = Duration.ZERO;
            if(task.getEndTime() != null){
                taskDuration = Duration.between(task.getStartTime(), task.getEndTime());
            } else{
                taskDuration = Duration.between(LocalDateTime.now(), task.getStartTime());
            }
            tasksReport.put(task.getName(), new Report(task.getTaskStatus(), taskDuration));
        });
        System.out.println("CATEGORY\t\t\t\t\t\tSTATUS\t\t\t\t\t\t\tTIME TAKEN");
        tasksReport.forEach((key, value) -> {
            String formattedRow = String.format("%-10s\t\t\t\t\t\t%-10s", key, value);
            System.out.println(formattedRow);
        });
    }

    public static void getCategoryDuration(){
        Map<String, Duration> categoryReport = new HashMap<>();
        tasks.values().forEach(task -> {
            Duration duration = Duration.ZERO;
            Duration d1 = Duration.ZERO;
            Duration d2 = Duration.ZERO;
            if(categoryReport.containsKey(task.getCategory())){
                d1 =  categoryReport.get(task.getCategory());
            } else{
                if(task.getEndTime() != null){
                    duration = Duration.between(task.getStartTime(), task.getEndTime());
                } else{
                    duration = Duration.between(LocalDateTime.now(), task.getStartTime());
                }
            }
            duration = d1.plusSeconds(d2.getSeconds());
            categoryReport.put(task.getCategory().toString(), duration);
        });
        System.out.println("CATEGORY\t\t\t\t\t\tTIME TAKEN");

        categoryReport.forEach((key, value) -> {
            String formattedRow = String.format("%-32s\t\t\t\t\t\t%-32s", key, value);
            System.out.println(formattedRow);
        });
    }


}
