package dev.schulte.daos;

import dev.schulte.entities.Complaint;

import java.util.List;

public interface ComplaintDAO {

    Complaint createComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    Complaint updateComplaint(Complaint complaint);

    Complaint updateComplaintMeeting(Complaint complaint);

}
