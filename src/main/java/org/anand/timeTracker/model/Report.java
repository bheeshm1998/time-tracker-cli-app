package org.anand.timeTracker.model;

import java.time.Duration;

public class Report {

    private TaskStatus status;

    private Duration duration;

    public Report(TaskStatus status, Duration duration) {
        this.status = status;
        this.duration = duration;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return  status + "  ------  " + duration ;
    }
}
