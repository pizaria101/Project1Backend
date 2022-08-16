package dev.schulte.daos;

import dev.schulte.entities.Complaint;
import dev.schulte.entities.Status;
import dev.schulte.util.ConnectionUtil;

import java.sql.*;

public class ComplaintDaoPostgres implements ComplaintDAO{
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
}
