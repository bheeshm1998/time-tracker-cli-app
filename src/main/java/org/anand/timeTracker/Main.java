package org.anand.timeTracker;

import org.anand.timeTracker.model.*;
import org.anand.timeTracker.utils.ArgsUtil;
import org.anand.timeTracker.utils.FilesUtility;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        getAllTasks();

        Args arguments = ArgsUtil.parseArgs(args);

        switch (arguments.getCommand()){
            case START_TASK -> {
                String taskName = arguments.getTaskName();
                String categoryName = arguments.getCategoryName();
                Task newTask = new Task(taskName, new TaskCategory(categoryName));
                TaskTracker.startTask(newTask);
                }

            case STOP_TASK -> {
                String taskName = arguments.getTaskName();
                TaskTracker.completeTask(taskName);
            }

            case REPORT_TASK -> {
                TaskTracker.getTaskDuration();
            }

            case REPORT_CATEGORY -> {
                TaskTracker.getCategoryDuration();
            }

            case RESTART -> {
                TaskTracker.tasks.keySet().removeIf(entry -> true);
                FilesUtility.writeTasksToFile(new ArrayList<>());
            }

            case EXIT -> {

            }
        }
        System.out.println("Current tasks " + TaskTracker.tasks);
    }

    private static void getAllTasks() throws IOException {
        FilesUtility.readTasksFromFile().forEach(task -> {
            TaskTracker.tasks.put(task.getName(), task);
        });
    }
}