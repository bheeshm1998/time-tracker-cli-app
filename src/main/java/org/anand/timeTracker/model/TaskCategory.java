package org.anand.timeTracker.model;

public class TaskCategory {

    public static final String NONE = "no-category";
    private String name;
    private Integer totalTime;

    public TaskCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    @Override
    public String toString() {
        return name;
    }
}
