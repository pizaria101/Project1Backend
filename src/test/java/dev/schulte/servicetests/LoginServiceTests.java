package dev.schulte.servicetests;

import dev.schulte.daos.appuser.AppUserDAO;
import dev.schulte.entities.AppUser;
import dev.schulte.entities.Role;
import dev.schulte.exceptions.NoUserFoundException;
import dev.schulte.exceptions.PasswordMismatchException;
import dev.schulte.services.login.LoginService;
import dev.schulte.services.login.LoginServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginServiceTests {

    @Test
    void username_must_exist_test(){
        AppUserDAO appUserDAO = Mockito.mock(AppUserDAO.class);
        AppUser appUser = new AppUser(0, "pizzaria101", "licish12", Role.UNREGISTERED);
        Mockito.when(appUserDAO.createAppUser(appUser)).thenReturn(appUser);
        Mockito.when(appUserDAO.getUserByUsername(appUser.getUsername())).thenReturn(appUser);
        LoginService loginService = new LoginServiceImpl(appUserDAO);

        Assertions.assertThrows(NoUserFoundException.class, () ->{
           loginService.validateUser("pizaria101", "licish12");
        });
    }

    @Test
    void password_does_not_match_test(){
        AppUserDAO appUserDAO = Mockito.mock(AppUserDAO.class);
        AppUser appUser = new AppUser(0, "pizaria101", "licish12", Role.UNREGISTERED);
        Mockito.when(appUserDAO.createAppUser(appUser)).thenReturn(appUser);
        Mockito.when(appUserDAO.getUserByUsername(appUser.getUsername())).thenReturn(appUser);
        LoginService loginService = new LoginServiceImpl(appUserDAO);

        Assertions.assertThrows(PasswordMismatchException.class, () -> {
            loginService.validateUser("pizaria101", "licish121");
        });
    }
}
