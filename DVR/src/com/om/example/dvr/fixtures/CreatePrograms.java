package com.om.example.dvr.fixtures;
 
public class CreatePrograms {
    private String name;
    private int channel;
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setChannel(int channel) {
        this.channel = channel;
    }
 
    public void setDayOfWeek(String dayOfWeek) {
    }
 
    public void setTimeOfDay(String timeOfDay) {
    }
 
    public void setDurationInMinutes(int durationInMinutes) {
    }
 
    public String id() {
        return String.format("[%s:%d]", name, channel);
    }
}