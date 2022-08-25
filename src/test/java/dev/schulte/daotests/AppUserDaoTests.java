package dev.schulte.daotests;

import dev.schulte.daos.appuser.AppUserDAO;
import dev.schulte.daos.appuser.AppUserDaoPostgres;
import dev.schulte.entities.AppUser;
import dev.schulte.entities.Role;
import dev.schulte.util.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppUserDaoTests {

    static AppUserDAO appUserDAO = new AppUserDaoPostgres("test_app_user");

    @BeforeAll
    static void setup() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "create table test_app_user(\n" +
                    "user_id serial primary key,\n" +
                    "username varchar(30),\n" +
                    "password varchar(30),\n" +
                    "role varchar(30) default 'UNREGISTERED'\n" +
                    ");\n";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_app_user_test(){
        AppUser appUser = new AppUser(0,"pizaria101","licish12", Role.UNREGISTERED);
        AppUser savedUser = appUserDAO.createAppUser(appUser);
        Assertions.assertEquals("pizaria101", savedUser.getUsername());
    }

    @AfterAll
    static void teardown(){

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "drop table test_app_user";
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
