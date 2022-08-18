package dev.schulte.services.complaint;

import dev.schulte.entities.Complaint;

import java.util.List;

public interface ComplaintService {

    Complaint reportComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();
}
