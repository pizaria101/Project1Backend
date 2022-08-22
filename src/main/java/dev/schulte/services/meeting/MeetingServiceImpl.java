package dev.schulte.services.meeting;

import dev.schulte.daos.meeting.MeetingDAO;
import dev.schulte.entities.Meeting;

import java.util.List;

public class MeetingServiceImpl implements MeetingService{

    private final MeetingDAO meetingDAO;

    public MeetingServiceImpl(MeetingDAO meetingDAO){
        this.meetingDAO = meetingDAO;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return this.meetingDAO.getAllMeetings();
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        Meeting savedMeeting = this.meetingDAO.createMeeting(meeting);
        return savedMeeting;
    }
}
