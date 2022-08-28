package dev.schulte.services.login;

import dev.schulte.daos.appuser.AppUserDAO;
import dev.schulte.entities.AppUser;
import dev.schulte.exceptions.NoUserFoundException;
import dev.schulte.exceptions.PasswordMismatchException;

public class LoginServiceImpl implements LoginService{

    private AppUserDAO appUserDAO;

    public  LoginServiceImpl(AppUserDAO appUserDAO){
        this.appUserDAO = appUserDAO;
    }
    @Override
    public AppUser validateUser(String username, String password) {
        AppUser appUser = this.appUserDAO.getUserByUsername(username);
        System.out.println(appUser);

        if(appUser == null){
            throw new NoUserFoundException("No user found with that username");
        }

        if(!appUser.getPassword().equals(password)){
            throw new PasswordMismatchException("Password does not match");
        }
        return appUser;
    }
}
