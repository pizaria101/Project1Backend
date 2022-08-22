package dev.schulte.handlers;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Meeting;
import dev.schulte.services.meeting.MeetingService;
import io.javalin.http.Handler;

import java.util.List;

public class MeetingHandler {

    private Gson gson = new Gson();

    private MeetingService mserv;

    public MeetingHandler(MeetingService meetingService){
        this.mserv = meetingService;
    }

    public Handler getAllMeetings = (ctx) -> {
        List<Meeting> meetings = App.meetingService.getAllMeetings();
        String meetingJSON = gson.toJson(meetings);
        ctx.result(meetingJSON);
    };

    public Handler createMeeting = (ctx) -> {
        Meeting meeting = this.gson.fromJson(ctx.body(), Meeting.class);
        meeting = this.mserv.createMeeting(meeting);
        String meetingJSON = gson.toJson(meeting);
        ctx.status(201);
        ctx.result(meetingJSON);
    };
}
