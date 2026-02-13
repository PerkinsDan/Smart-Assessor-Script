package org.example.objects;

import lombok.Getter;

@Getter
public class OTJEntry {
    String unitId = "ef974f73-5d9d-447e-8652-379ba9535229";
    String learnerId = "be605ce9-44ff-439e-8d55-47a8637a0313";
    String activityDate;
    String activityImpact;
    int activityType = 13;
    String activityTime; // Start time
    int hours; // Duration
    int minutes; // Duration

    public OTJEntry(String activityDate, String activityImpact, String activityTime, int hours, int minutes) {
        this.activityDate = activityDate;
        this.activityImpact = activityImpact;
        this.activityTime = activityTime;
        this.hours = hours;
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "unitId=" + unitId +
                "&learnerId=" + learnerId +
                "&activityDate=" + activityDate +
                "&activityImpact=" + activityImpact +
                "&activityType=" + activityType +
                "&activityTime=" + activityTime +
                "&hours=" + hours +
                "&minutes=" + minutes;
    }
}
