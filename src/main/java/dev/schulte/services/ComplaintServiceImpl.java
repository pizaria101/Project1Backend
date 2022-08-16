package dev.schulte.services;

import dev.schulte.daos.ComplaintDAO;
import dev.schulte.entities.Complaint;

public class ComplaintServiceImpl implements ComplaintService{

    private ComplaintDAO complaintDAO;

    public ComplaintServiceImpl(ComplaintDAO complaintDAO){
        this.complaintDAO = complaintDAO;
    }
    @Override
    public Complaint reportComplaint(Complaint complaint) {

        Complaint savedComplaint = this.complaintDAO.createComplaint(complaint);
        return savedComplaint;
    }
}
