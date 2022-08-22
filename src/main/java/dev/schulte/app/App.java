package dev.schulte.app;

import dev.schulte.daos.complaint.ComplaintDaoPostgres;
import dev.schulte.daos.meeting.MeetingDaoPostgres;
import dev.schulte.handlers.ComplaintHandler;
import dev.schulte.handlers.MeetingHandler;
import dev.schulte.services.complaint.ComplaintService;
import dev.schulte.services.complaint.ComplaintServiceImpl;
import dev.schulte.services.meeting.MeetingService;
import dev.schulte.services.meeting.MeetingServiceImpl;
import io.javalin.Javalin;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDaoPostgres("complaint"));
    public static ComplaintHandler complaintHandler = new ComplaintHandler(complaintService);

    public static MeetingService meetingService = new MeetingServiceImpl(new MeetingDaoPostgres("meeting"));
    public static MeetingHandler meetingHandler = new MeetingHandler(meetingService);

    public static void main(String[] args) {
        Javalin app = Javalin.create(config->{
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        app.post("/complaints", complaintHandler.reportComplaint);
        app.get("/meetings", meetingHandler.getAllMeetings);
        app.get("/complaints", complaintHandler.getAllComplaints);
        app.patch("/complaints/{complaintId}/{status}", complaintHandler.updateComplaintStatus);
        app.post("/meetings", meetingHandler.createMeeting);
        app.patch("complaints/{complaintId}/{meetingId}", complaintHandler.addComplaintToMeeting);

        app.start();


    }
}
