package org.anand.timeTracker.utils;

import java.time.Duration;

public class TimeUtils {

    public static String formatDuration(Duration duration){
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        StringBuilder formattedDuration = new StringBuilder();

        if (hours > 0) {
            formattedDuration.append(hours).append("hr ");
        }
        if (minutes > 0) {
            formattedDuration.append(minutes).append("min ");
        }
        if (seconds > 0) {
            formattedDuration.append(seconds).append("sec");
        }

        // If the duration is 0 seconds, return "0 sec" to avoid an empty string
        if (formattedDuration.length() == 0) {
            return "0 sec";
        }

        return formattedDuration.toString();
    }
}
