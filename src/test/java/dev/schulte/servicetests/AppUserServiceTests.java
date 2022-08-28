package dev.schulte.servicetests;

import dev.schulte.daos.appuser.AppUserDAO;
import dev.schulte.entities.AppUser;
import dev.schulte.entities.Role;
import dev.schulte.exceptions.PasswordShortException;
import dev.schulte.exceptions.UsernameShortException;
import dev.schulte.services.appuser.AppUserService;
import dev.schulte.services.appuser.AppUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AppUserServiceTests {

    @Test
    void register_user_must_have_username_test(){
        AppUserDAO appUserDAO = Mockito.mock(AppUserDAO.class);
        AppUser appUser = new AppUser(0, "pi", "licish12", Role.UNREGISTERED);
        Mockito.when(appUserDAO.createAppUser(appUser)).thenReturn(appUser);
        AppUserService appUserService = new AppUserServiceImpl(appUserDAO);

        Assertions.assertThrows(UsernameShortException.class, () ->{
            appUserService.registerUser(appUser);
        });
    }

    @Test
    void register_user_must_have_password_test(){
        AppUserDAO appUserDAO = Mockito.mock(AppUserDAO.class);
        AppUser appUser = new AppUser(0, "pizaria101", "li", Role.UNREGISTERED);
        Mockito.when(appUserDAO.createAppUser(appUser)).thenReturn(appUser);
        AppUserService appUserService = new AppUserServiceImpl(appUserDAO);

        Assertions.assertThrows(PasswordShortException.class, () ->{
           appUserService.registerUser(appUser);
        });
    }
}
