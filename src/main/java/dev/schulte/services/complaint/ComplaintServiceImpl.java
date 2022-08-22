package dev.schulte.services.complaint;

import dev.schulte.daos.complaint.ComplaintDAO;
import dev.schulte.entities.Complaint;
import dev.schulte.entities.Status;
import dev.schulte.services.complaint.ComplaintService;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService {

    private ComplaintDAO complaintDAO;

    public ComplaintServiceImpl(ComplaintDAO complaintDAO){
        this.complaintDAO = complaintDAO;
    }
    @Override
    public Complaint reportComplaint(Complaint complaint) {
        Complaint savedComplaint = this.complaintDAO.createComplaint(complaint);
        return savedComplaint;
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return this.complaintDAO.getAllComplaints();
    }

    @Override
    public Complaint getComplaintById(int complaintId) {
        return this.complaintDAO.getComplaintById(complaintId);
    }

    @Override
    public Complaint updateComplaintStatus(int complaintId, Status status) {
        Complaint complaint = this.complaintDAO.getComplaintById(complaintId);
        complaint.setStatus(status);
        this.complaintDAO.updateComplaintStatus(complaint.getComplaintId(), status);
        return complaint;
    }

    @Override
    public Complaint addComplaintToMeeting(int complaintId, int meetingId) {
        Complaint complaint = this.complaintDAO.getComplaintById(complaintId);
        complaint.setMeetingId(meetingId);
        this.complaintDAO.updateComplaintMeetingId(complaint.getComplaintId(), meetingId);
        return complaint;
    }
}
