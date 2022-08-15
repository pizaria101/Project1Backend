package dev.schulte.entities;

import java.util.Objects;

public class Meeting {

    private int meetingId;

    private String meetingDate;

    private String meetingDesc;

    public Meeting(){

    }

    public Meeting(int meetingId, String meetingDate, String meetingDesc) {
        this.meetingId = meetingId;
        this.meetingDate = meetingDate;
        this.meetingDesc = meetingDesc;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingDesc() {
        return meetingDesc;
    }

    public void setMeetingDesc(String meetingDesc) {
        this.meetingDesc = meetingDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return meetingId == meeting.meetingId && meetingDate.equals(meeting.meetingDate) && meetingDesc.equals(meeting.meetingDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, meetingDate, meetingDesc);
    }
}
