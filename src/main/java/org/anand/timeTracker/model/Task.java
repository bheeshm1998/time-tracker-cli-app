package org.anand.timeTracker.model;

import org.anand.timeTracker.utils.TimeUtils;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task {

    private String name;
    private TaskCategory category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private TaskStatus taskStatus;

    private Duration timeSpent;

    public Task(String name, TaskCategory category) {
        this.name = name;
        this.category = category;
        this.startTime = LocalDateTime.now();
        this.taskStatus = TaskStatus.IN_PROGRESS;
        this.timeSpent = Duration.ZERO;
    }

    @Override
    public String toString() {
        return  name + "," + category + "," + taskStatus + "," + startTime + "," + endTime;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Duration timeSpent) {
        this.timeSpent = timeSpent;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public static Task convertStringToTask(String str){
        String[] tokens = str.split(",");
        String taskName = tokens[0];
        String categoryName = tokens[1];
        TaskStatus status = TaskStatus.valueOf(tokens[2]);
        LocalDateTime startTime = LocalDateTime.parse(tokens[3]);
        LocalDateTime endTime = tokens[4].equals("null") ? null : LocalDateTime.parse(tokens[4]);

        Task task = new Task(taskName, new TaskCategory(categoryName));
        task.setTaskStatus(status);
        task.setStartTime(startTime);
        task.setEndTime(endTime);

        return task;
    }
}
