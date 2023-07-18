package com.authmodule.account.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ActivityWindow {
    private List<Activity> activityList;
    public void addActivity(Activity withdrawal) {
        activityList.add(withdrawal);
    }
}
