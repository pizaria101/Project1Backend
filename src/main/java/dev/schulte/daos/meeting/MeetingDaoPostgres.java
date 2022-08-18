package dev.schulte.daos.meeting;

import dev.schulte.entities.Meeting;
import dev.schulte.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoPostgres implements MeetingDAO{
    @Override
    public List<Meeting> getAllMeetings() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from meeting";
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
}
