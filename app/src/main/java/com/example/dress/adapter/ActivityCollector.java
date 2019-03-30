package com.example.dress.adapter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<>();
    public static List<Activity> tempactivities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }

    public static void addStampActivity(Activity activity){
        tempactivities.add(activity);
    }
    public static void removeStampActivity(Activity activity){
        tempactivities.remove(activity);
    }
    public static void finishAllStampActivity(){
        for(Activity activity:tempactivities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        tempactivities.clear();
    }

}
