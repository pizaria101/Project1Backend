package dev.schulte.handlers;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Complaint;
import dev.schulte.entities.Status;
import dev.schulte.services.complaint.ComplaintService;
import io.javalin.http.Handler;

import java.util.List;

public class ComplaintHandler {

    private Gson gson = new Gson();
    private ComplaintService cserv;

    public ComplaintHandler(ComplaintService complaintService){
        this.cserv = complaintService;
    }

    public Handler reportComplaint = (ctx) -> {
        Complaint complaint = this.gson.fromJson(ctx.body(), Complaint.class);
        complaint = this.cserv.reportComplaint(complaint);
        String complaintJSON = gson.toJson(complaint);
        ctx.status(201);
        ctx.result(complaintJSON);
    };

    public Handler getAllComplaints = (ctx) -> {
        List<Complaint> complaints = App.complaintService.getAllComplaints();
        String complaintJSON = gson.toJson(complaints);
        ctx.result(complaintJSON);
    };

    public Handler updateComplaintStatus = (ctx) -> {
        int complaintId = Integer.parseInt(ctx.pathParam("complaintId"));
        Complaint getComplaint;
        String newStatus = ctx.pathParam("status").toLowerCase();

        switch(newStatus){

            case "addressed":
                getComplaint = App.complaintService.updateComplaintStatus(complaintId, Status.ADDRESSED);
                break;

            case "high":
                getComplaint = App.complaintService.updateComplaintStatus(complaintId, Status.HIGH_PRIORITY);
                break;

            case "low":
                getComplaint = App.complaintService.updateComplaintStatus(complaintId, Status.LOW_PRIORITY);
                break;

            case "ignore":
                getComplaint = App.complaintService.updateComplaintStatus(complaintId, Status.IGNORED);
                break;

            default:
                ctx.status(400);
                ctx.result("Status change failed");
                return;
        }

        ctx.status(202);
        String complaintJSON = gson.toJson(getComplaint);

        ctx.result(complaintJSON);
    };

    public Handler addComplaintToMeeting = (ctx) -> {
        int complaintId = Integer.parseInt(ctx.pathParam("complaintId"));
        int newMeeting = Integer.parseInt(ctx.pathParam("meetingId"));
        Complaint assignedComplaint = App.complaintService.addComplaintToMeeting(complaintId, newMeeting);

        ctx.status(202);
        String complaintJSON = gson.toJson(assignedComplaint);

        ctx.result(complaintJSON);
    };
}
