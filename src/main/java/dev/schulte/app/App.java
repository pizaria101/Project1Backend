package dev.schulte.app;

import com.google.gson.Gson;
import dev.schulte.daos.appuser.AppUserDaoPostgres;
import dev.schulte.daos.complaint.ComplaintDaoPostgres;
import dev.schulte.daos.meeting.MeetingDaoPostgres;
import dev.schulte.dtos.LoginCredentials;
import dev.schulte.entities.AppUser;
import dev.schulte.exceptions.NoUserFoundException;
import dev.schulte.exceptions.PasswordMismatchException;
import dev.schulte.handlers.ComplaintHandler;
import dev.schulte.handlers.MeetingHandler;
import dev.schulte.services.complaint.ComplaintService;
import dev.schulte.services.complaint.ComplaintServiceImpl;
import dev.schulte.services.login.LoginService;
import dev.schulte.services.login.LoginServiceImpl;
import dev.schulte.services.meeting.MeetingService;
import dev.schulte.services.meeting.MeetingServiceImpl;
import io.javalin.Javalin;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDaoPostgres("complaint"));
    public static ComplaintHandler complaintHandler = new ComplaintHandler(complaintService);

    public static MeetingService meetingService = new MeetingServiceImpl(new MeetingDaoPostgres("meeting"));
    public static MeetingHandler meetingHandler = new MeetingHandler(meetingService);

    public static LoginService loginService = new LoginServiceImpl(new AppUserDaoPostgres("app_user"));

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
        app.patch("/complaints/{complaintId}/meetings/{meetingId}", complaintHandler.addComplaintToMeeting);

        app.post("/login", ctx -> {
           String body = ctx.body();
            Gson gson = new Gson();
            LoginCredentials credentials = gson.fromJson(body, LoginCredentials.class);

            AppUser appUser = loginService.validateUser(credentials.getUsername(), credentials.getPassword());
            String userJSON = gson.toJson(appUser);
            ctx.result(userJSON);
        });

        app.exception(NoUserFoundException.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.result("No user found " + exception.getMessage());
        });

        app.exception(PasswordMismatchException.class, (exception, ctx) -> {
            ctx.status(400);
            ctx.result("Password did not match");
        });

        app.start();


    }
}
