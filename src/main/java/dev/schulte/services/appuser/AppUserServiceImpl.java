package dev.schulte.services.appuser;

import dev.schulte.daos.appuser.AppUserDAO;
import dev.schulte.entities.AppUser;
import dev.schulte.exceptions.PasswordShortException;
import dev.schulte.exceptions.UsernameShortException;

public class AppUserServiceImpl implements AppUserService {

    private AppUserDAO appUserDAO;

    public AppUserServiceImpl(AppUserDAO appUserDAO){
        this.appUserDAO = appUserDAO;
    }

    @Override
    public AppUser registerUser(AppUser appUser) {

        if(appUser.getUsername().length() < 3){
            throw new UsernameShortException("Username must be more than 3 characters");
        }

        if(appUser.getPassword().length() < 5){
            throw new PasswordShortException("Password must be more than 5 characters");
        }

        AppUser savedUser = this.appUserDAO.createAppUser(appUser);
        return savedUser;
    }
}
