package dev.schulte.daos.appuser;

import dev.schulte.entities.AppUser;
import dev.schulte.util.ConnectionUtil;

import java.sql.*;

public class AppUserDaoPostgres implements AppUserDAO{

    String tableName;

    public AppUserDaoPostgres(String tableName){
        this.tableName = tableName;
    }

    @Override
    public AppUser createAppUser(AppUser appUser) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into " + this.tableName + " values (default, ?, ?, default)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appUser.getUsername());
            ps.setString(2, appUser.getPassword());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("user_id");
            appUser.setUserId(generatedKey);
            return appUser;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
