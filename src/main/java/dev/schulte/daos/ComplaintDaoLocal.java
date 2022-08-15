package dev.schulte.daos;

import dev.schulte.entities.Complaint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplaintDaoLocal implements ComplaintDAO{

    private Map<Integer, Complaint> complaintTable = new HashMap();
    private int idMaker = 1;

    @Override
    public Complaint createComplaint(Complaint complaint) {
        complaint.setComplaintId(idMaker);
        idMaker++;
        complaintTable.put(complaint.getComplaintId(), complaint);
        return complaint;
    }

    @Override
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>(this.complaintTable.values());
        return complaints;
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        this.complaintTable.put(complaint.getComplaintId(), complaint);
        return complaint;
    }

}
