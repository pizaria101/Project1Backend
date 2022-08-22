package dev.schulte.daos.complaint;

import dev.schulte.daos.complaint.ComplaintDAO;
import dev.schulte.entities.Complaint;
import dev.schulte.entities.Status;
import dev.schulte.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDaoPostgres implements ComplaintDAO {

    String tableName;

    public ComplaintDaoPostgres(String tableName){
        this.tableName = tableName;
    }

    @Override
    public Complaint createComplaint(Complaint complaint) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into " + this.tableName + " values (default, ?, default, default)";
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
            String sql = "select * from " + this.tableName;
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

    @Override
    public Complaint getComplaintById(int complaintId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from " + this.tableName + " where complaint_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, complaintId);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()){
                return null;
            }

            Complaint complaint = new Complaint();
            complaint.setComplaintId(rs.getInt("complaint_id"));
            complaint.setComplaintDesc(rs.getString("description"));
            complaint.setStatus(Status.valueOf(rs.getString("status")));
            complaint.setMeetingId(rs.getInt("meeting_id"));

            return complaint;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Complaint updateComplaintStatus(int complaintId, Status status) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update " + this.tableName + " set status = ? where complaint_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.toString());
            ps.setInt(2, complaintId);

            ps.executeUpdate();
            return getComplaintById(complaintId);

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Complaint updateComplaintMeetingId(int complaintId, int meetingId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update " + this.tableName + " set meeting_id = ? where complaint_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, meetingId);
            ps.setInt(2, complaintId);

            ps.executeUpdate();
            return getComplaintById(complaintId);

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
