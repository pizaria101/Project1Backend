package dev.schulte.daotests;

import dev.schulte.daos.meeting.MeetingDAO;
import dev.schulte.daos.meeting.MeetingDaoPostgres;
import dev.schulte.entities.Meeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MeetingDaoTests {

    static MeetingDAO meetingDAO = new MeetingDaoPostgres();

    @Test
    void get_all_meetings_test(){
        List<Meeting> meetings = meetingDAO.getAllMeetings();
        Assertions.assertTrue(meetings.size() > 0);
    }
}
