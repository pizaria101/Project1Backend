package dev.schulte.handlers;

import com.google.gson.Gson;
import dev.schulte.entities.Complaint;
import dev.schulte.services.ComplaintService;
import io.javalin.http.Handler;

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
}
