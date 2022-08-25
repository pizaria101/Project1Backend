package dev.schulte.daos.appuser;

import dev.schulte.entities.AppUser;
import dev.schulte.entities.Role;
import dev.schulte.exceptions.PasswordShortException;
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

    @Override
    public AppUser getUserByUsername(String username) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from " + this.tableName + " where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            rs.next();

            AppUser appUser = new AppUser();
            appUser.setUserId(rs.getInt("user_id"));
            appUser.setUsername(rs.getString("username"));
            appUser.setPassword(rs.getString("password"));
            appUser.setRole(Role.valueOf(rs.getString("role")));

            return appUser;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
