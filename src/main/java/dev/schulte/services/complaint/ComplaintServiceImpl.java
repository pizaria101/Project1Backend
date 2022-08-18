package dev.schulte.services.complaint;

import dev.schulte.daos.complaint.ComplaintDAO;
import dev.schulte.entities.Complaint;
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
}
