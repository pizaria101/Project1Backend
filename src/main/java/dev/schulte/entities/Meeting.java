package dev.schulte.entities;

public class Meeting {

    private int meetingId;

    private String address;

    private long time;

    private String meetingDesc;

    public Meeting(){

    }

    public Meeting(int meetingId, String address, long time, String meetingDesc) {
        this.meetingId = meetingId;
        this.address = address;
        this.time = time;
        this.meetingDesc = meetingDesc;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMeetingDesc() {
        return meetingDesc;
    }

    public void setMeetingDesc(String meetingDesc) {
        this.meetingDesc = meetingDesc;
    }
}
