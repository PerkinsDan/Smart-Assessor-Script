package org.example.objects;

import lombok.Getter;

@Getter
public class OTJEntry {
    String Id = "0";
    String DateCreated = "01/01/0001+00:00:00";
    String OriginId = "Unknown";
    String SessionLinkHasFeedback = "False";
    String IsDfeFundingRuleDateToBeValidated = "False";
    String ActivityImpactRequired = "True";
    String hdnDfeFundingRuleDate = "01/08/2023";
    String TimeWithAssessorId = "501133f8-46bf-4565-b6b1-82c4d038f437";
    String OnTheJob = "1";
    String IsAssessorApproved = "false";
    String UnitId = "{ef974f73-5d9d-447e-8652-379ba9535229}";
    String Date;
    String ParentModuleId;
    String TimeValue; // Time Spend on Activity
    String ActivityStartTimeValue; // Start Time
    String Comments;
    String ParentActivityId; // Activity type

    public OTJEntry(String date,
                    String parentModuleId,
                    String timeValue,
                    String activityStartTimeValue,
                    String parentActivityId,
                    String comments) {
        Date = date;
        ParentModuleId = parentModuleId;
        TimeValue = timeValue;
        ActivityStartTimeValue = activityStartTimeValue;
        ParentActivityId = parentActivityId;
        Comments = comments;
    }

    @Override
    public String toString() {
        return "Id=" + Id +
                "&DateCreated=" + DateCreated +
                "&OriginId=" + OriginId +
                "&SessionLinkHasFeedback=" + SessionLinkHasFeedback +
                "&IsDfeFundingRuleDateToBeValidated=" + IsDfeFundingRuleDateToBeValidated +
                "&ActivityImpactRequired=" + ActivityImpactRequired +
                "&hdnDfeFundingRuleDate=" + hdnDfeFundingRuleDate +
                "&ParentActivityId=" + ParentActivityId +
                "&TimeWithAssessorId=" + TimeWithAssessorId +
                "&OnTheJob=" + OnTheJob +
                "&IsAssessorApproved=" + IsAssessorApproved +
                "&UnitId=" + UnitId +
                "&Date=" + Date +
                "&ParentModuleId=" + ParentModuleId +
                "&TimeValue=" + TimeValue +
                "&ActivityStartTimeValue=" + ActivityStartTimeValue +
                "&Comments=" + Comments;
    }
}
