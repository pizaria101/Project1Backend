package dev.schulte.daos.complaint;

import dev.schulte.daos.complaint.ComplaintDAO;
import dev.schulte.entities.Complaint;
import dev.schulte.entities.Status;
import dev.schulte.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDaoPostgres implements ComplaintDAO {
    @Override
    public Complaint createComplaint(Complaint complaint) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into complaint values (default, ?, default, default)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, complaint.getComplaintDesc());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("complaint_id");
            complaint.setComplaintId(generatedKey);
            return complaint;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Complaint> getAllComplaints() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from complaint";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Complaint> complaintList = new ArrayList();
            while(rs.next()){
                Complaint complaint = new Complaint();
                complaint.setComplaintId(rs.getInt("complaint_id"));
                complaint.setComplaintDesc(rs.getString("description"));
                complaint.setStatus(Status.valueOf(rs.getString("status")));
                complaint.setMeetingId(rs.getInt("meeting_id"));
                complaintList.add(complaint);
            }
            return complaintList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
