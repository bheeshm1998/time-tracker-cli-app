package org.anand.timeTracker;

import org.anand.timeTracker.model.*;
import org.anand.timeTracker.utils.ArgsUtil;
import org.anand.timeTracker.utils.FilesUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        getAllTasks();

        Scanner scanner = new Scanner(System.in);
        String input ;

        while (true) {
            System.out.print(">");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application");
                break;
            }
            if(input.equals("")){
                continue;
            }
            processInput(input);
        }
        scanner.close();
    }

    static boolean processInput(String input) throws IOException {
        String[] args = input.split(" ");
        Args arguments = ArgsUtil.parseArgs(args);
        if(arguments.getCommand() == null){
            return false;
        }
        switch (arguments.getCommand()){

            case START_TASK -> {
                String taskName = arguments.getTaskName();
                String categoryName = arguments.getCategoryName();
                Task newTask = new Task(taskName, new TaskCategory(categoryName));
                TaskTracker.startTask(newTask);
            }

            case STOP_TASK -> {
                String taskName = arguments.getTaskName();
                TaskTracker.stopTask(taskName);
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

            default -> {
                return false;
            }
        }
        return true;
    }

    private static void getAllTasks() throws IOException {
        FilesUtility.readTasksFromFile().forEach(task -> {
            TaskTracker.tasks.put(task.getName(), task);
        });
    }
}