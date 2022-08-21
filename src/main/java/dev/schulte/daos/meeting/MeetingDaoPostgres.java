package dev.schulte.daos.meeting;

import dev.schulte.entities.Meeting;
import dev.schulte.util.ConnectionUtil;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoPostgres implements MeetingDAO{

    String tableName;

    public MeetingDaoPostgres(String tableName){
        this.tableName = tableName;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from " + this.tableName;
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Meeting> meetingList = new ArrayList();
            while(rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meeting_id"));
                meeting.setAddress(rs.getString("address"));
                meeting.setTime(rs.getLong("time"));
                meeting.setMeetingDesc(rs.getString("meeting_desc"));
                meetingList.add(meeting);
            }
            return meetingList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into " + this.tableName + " values (default, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, meeting.getAddress());
            ps.setLong(2, meeting.getTime());
            ps.setString(3, meeting.getMeetingDesc());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("meeting_id");
            meeting.setMeetingId(generatedKey);
            return meeting;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
