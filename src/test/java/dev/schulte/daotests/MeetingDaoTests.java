package dev.schulte.daotests;

import dev.schulte.daos.meeting.MeetingDAO;
import dev.schulte.daos.meeting.MeetingDaoPostgres;
import dev.schulte.entities.Meeting;
import dev.schulte.util.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingDaoTests {

    static MeetingDAO meetingDAO = new MeetingDaoPostgres("test_meeting");

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table test_meeting(\n" +
                    "meeting_id serial primary key,\n" +
                    "address varchar(130),\n" +
                    "time int, -- use unix epoch time\n" +
                    "meeting_desc varchar(130)\n" +
                    ");\n";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void get_all_meetings_test(){
        List<Meeting> meetings = meetingDAO.getAllMeetings();
        Assertions.assertTrue(meetings.size() > 0);
    }

    @Test
    @Order(1)
    void create_meeting_test(){
        Meeting meeting = new Meeting(0, "Town Hall", 1661065939, "Discussing complaints about too many Hollows and not enough Soul Reapers");
        Meeting savedMeeting = meetingDAO.createMeeting(meeting);
        Assertions.assertNotEquals(0, savedMeeting.getMeetingId());
    }

    @AfterAll
    static void teardown(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "drop table test_meeting";
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
