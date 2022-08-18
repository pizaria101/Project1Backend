package dev.schulte.daos.meeting;

import dev.schulte.entities.Meeting;

import java.util.List;

public interface MeetingDAO {

    List<Meeting> getAllMeetings();
}
