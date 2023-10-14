package org.anand.timeTracker.utils;

import org.anand.timeTracker.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilesUtility {

    static String path = "./time-tracker.csv";

    public static List<Task> readTasksFromFile() throws IOException {
        List<String> listOfTasks = new ArrayList<>();
        if(Files.exists(Path.of(path))){
            listOfTasks = Files.readAllLines(Path.of(path));
        } else{
            Files.createFile(Path.of("time-tracker.csv"));
        }
        List<Task> list = listOfTasks.stream().map(Task::convertStringToTask).toList();
        return list;
    }


    public static void writeTasksToFile(List<Task> listOfTasks) throws IOException {
        if(!Files.exists(Path.of(path))){
            Files.createFile(Path.of("time-tracker.csv"));
        }
        System.out.println("Writing tasks to a file");
        Files.write(Path.of(path), listOfTasks.stream().map(task -> task.toString()).collect(Collectors.toList()));
    }
}
