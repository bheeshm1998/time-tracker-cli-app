package org.anand.timeTracker;

import org.anand.timeTracker.model.*;
import org.anand.timeTracker.utils.FilesUtility;
import org.anand.timeTracker.utils.TimeUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TaskTracker {

    static Map<String, Task> tasks = new HashMap<>();

    public static void startTask(Task task) throws IOException {
        if(tasks.containsKey(task.getName())){
            if(task.getTaskStatus().equals(TaskStatus.IN_PROGRESS)){
                Logger.log("Task already exists, skipping");
            } else{
                Task runningTask = tasks.get(task.getName());
                runningTask.setTaskStatus(TaskStatus.IN_PROGRESS);
                runningTask.setStartTime(LocalDateTime.now());
                runningTask.setEndTime(null);
            }
        } else{
            tasks.put(task.getName(), task);
        }
        tasks.values().forEach(t -> {
            if(t.getTaskStatus().equals(TaskStatus.IN_PROGRESS) && !t.getName().equals(task.getName())){
                try {
                    stopTask(t.getName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        FilesUtility.writeTasksToFile(tasks.values().stream().toList());
    }

    public static void stopTask(String taskName) throws IOException {
        if(tasks.get(taskName) != null){
            Duration initialDuration = tasks.get(taskName).getTimeSpent();
            LocalDateTime startTime = tasks.get(taskName).getStartTime();
            tasks.get(taskName).setTaskStatus(TaskStatus.COMPLETED);
            tasks.get(taskName).setEndTime(LocalDateTime.now());
            Duration additionalTimeSpent = Duration.between(startTime, LocalDateTime.now());
            Duration totalDuration = initialDuration.plus(additionalTimeSpent);
            tasks.get(taskName).setTimeSpent(totalDuration);
        } else{
            Logger.log("No such task found!");
        }
        FilesUtility.writeTasksToFile(tasks.values().stream().toList());
    }

    public static void getTaskDuration(){
        Map<String, Report> tasksReport = new HashMap<>();
        tasks.values().forEach(task -> {
            Duration taskDuration = Duration.ZERO;
            if(task.getEndTime() != null){
                taskDuration = Duration.between(task.getStartTime(), task.getEndTime());
            } else{
                taskDuration = Duration.between(task.getStartTime(), LocalDateTime.now());
            }
            tasksReport.put(task.getName(), new Report(task.getTaskStatus(), taskDuration));
        });
        String formattedHeader = String.format("%-10s\t\t\t%-10s\t\t\t%-10s\t\t\t%-10s", "TASK", "CATEGORY", "STATUS", "TIME TAKEN");
        System.out.println(formattedHeader);
        tasksReport.forEach((key, value) -> {
            String formattedRow = String.format("%-10s\t\t\t%-10s\t\t\t%-10s", key, tasks.get(key).getCategory(),  value);
            System.out.println(formattedRow);
        });
    }

    public static void getCategoryDuration(){
        Map<String, Duration> categoryReport = new HashMap<>();
        tasks.values().forEach(task -> {
            Duration duration = Duration.ZERO;
            Duration d1 = Duration.ZERO;
            Duration d2 = Duration.ZERO;
            if(categoryReport.containsKey(task.getCategory().getName())){
                d1 =  categoryReport.get(task.getCategory().getName());
            }
            if(task.getEndTime() != null){
                d2 = Duration.between(task.getStartTime(), task.getEndTime());
            } else{
                d2 = Duration.between(task.getStartTime(), LocalDateTime.now());
            }

            duration = d1.plusSeconds(d2.getSeconds());
            categoryReport.put(task.getCategory().getName(), duration);
        });
        System.out.println("CATEGORY\t\t\t\t\t\tTIME TAKEN");

        categoryReport.forEach((key, value) -> {
            String formattedRow = String.format("%-10s\t\t\t\t\t\t%-10s", key, TimeUtils.formatDuration(value));
            System.out.println(formattedRow);
        });
    }
}
