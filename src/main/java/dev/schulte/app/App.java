package dev.schulte.app;

import dev.schulte.daos.ComplaintDaoPostgres;
import dev.schulte.handlers.ComplaintHandler;
import dev.schulte.services.ComplaintService;
import dev.schulte.services.ComplaintServiceImpl;
import io.javalin.Javalin;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDaoPostgres());
    public static ComplaintHandler complaintHandler = new ComplaintHandler(complaintService);

    public static void main(String[] args) {
        Javalin app = Javalin.create(config->{
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        app.post("/complaints", complaintHandler.reportComplaint);

        app.start();


    }
}
