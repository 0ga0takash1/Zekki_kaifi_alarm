package zekki.kaihi.alarm;

import java.util.ArrayList;

public class AlarmInfo {
    private int alarmID = -1;
    private String alarmName = null;
    private String time = null;
    private ArrayList<Integer> subAlarm = new ArrayList<>();
    private String timing = null;
    private boolean alarm_switch = false;

    public String getAlarmName() {
        return alarmName;
    }
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getHour(){
        return getTime().substring(0,2);
    }
    public String getMin(){
        return getTime().substring(3,5);
    }


    public int getAlarmID() {
        return alarmID;
    }
    public void setAlarmID(int alarmID) {
        this.alarmID = alarmID;
    }

    public ArrayList<Integer> getSubAlarm() { return this.subAlarm; }
    public void addSubAlarm(int time) {
        this.subAlarm.add(time);
        this.subAlarm.stream().distinct();
    }
    public void delSubAlarm(int num) {
        if ( this.subAlarm.size() > 0 ) this.subAlarm.remove(num); 
    }

    public String getTiming() {
        return timing;
    }
    public void setTiming(String timing) {
        this.timing = timing;
    }

    public boolean isAlarm_switch() {
        return alarm_switch;
    }
    public void setAlarm_switch(boolean alarm_switch) {
        this.alarm_switch = alarm_switch;
    }
}
