package dev.schulte.daotests;

import dev.schulte.daos.complaint.ComplaintDAO;
import dev.schulte.daos.complaint.ComplaintDaoPostgres;
import dev.schulte.entities.Complaint;
import dev.schulte.entities.Status;
import dev.schulte.util.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintDaoTests {

    static ComplaintDAO complaintDAO = new ComplaintDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table complaint(\n" +
                    "complaint_id serial primary key,\n" +
                    "description varchar(200),\n" +
                    "status varchar(30) default 'UNREVIEWED',\n" +
                    "meeting_id int references meeting(meeting_id) default -1\n" +
                    ");\n";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_complaint_test(){
        Complaint complaint = new Complaint(0,"Too many hollows", Status.UNREVIEWED,-1);
        Complaint savedComplaint = complaintDAO.createComplaint(complaint);
        Assertions.assertNotEquals(0, savedComplaint.getComplaintId());
    }

    @Test
    @Order(2)
    void get_all_complaints_test(){
        List<Complaint> complaints = complaintDAO.getAllComplaints();
        Assertions.assertTrue(complaints.size() > 0);
    }

    @AfterAll
    static void teardown(){

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "drop table complaint";
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
