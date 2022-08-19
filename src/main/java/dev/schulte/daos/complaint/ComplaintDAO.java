package dev.schulte.daos.complaint;

import dev.schulte.entities.Complaint;
import dev.schulte.entities.Status;

import java.util.List;

public interface ComplaintDAO {

    Complaint createComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    Complaint getComplaintById(int complaintId);

    Complaint updateComplaintStatus(int complaintId, Status status);

}
