package dev.schulte.entities;

import java.util.Objects;

public class Complaint {

    private int complaintId;

    private String category;

    private String description;

    private Status status;

    private int meetingId;

    public Complaint(){

    }

    public Complaint(int complaintId, String category, String description, Status status, int meetingId) {
        this.complaintId = complaintId;
        this.category = category;
        this.description = description;
        this.status = status;
        this.meetingId = meetingId;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return complaintId == complaint.complaintId && meetingId == complaint.meetingId && category.equals(complaint.category) && description.equals(complaint.description) && status == complaint.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(complaintId, category, description, status, meetingId);
    }
}
