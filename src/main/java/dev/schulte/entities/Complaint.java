package dev.schulte.entities;

public class Complaint {

    private int complaintId;

    private String complaintDesc;

    private Status status;

    private int meetingId;

    public Complaint(){

    }

    public Complaint(int complaintId, String complaintDesc, Status status, int meetingId) {
        this.complaintId = complaintId;
        this.complaintDesc = complaintDesc;
        this.status = status;
        this.meetingId = meetingId;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintDesc() {
        return complaintDesc;
    }

    public void setComplaintDesc(String complaintDesc) {
        this.complaintDesc = complaintDesc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

}
