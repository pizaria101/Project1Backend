package dev.schulte.daos;

import dev.schulte.entities.Meeting;

import java.util.List;

public interface MeetingDAO {

    Meeting createMeeting(Meeting meeting);

    List<Meeting> getAllMeetings();

    Meeting updateMeeting(Meeting meeting);
}
