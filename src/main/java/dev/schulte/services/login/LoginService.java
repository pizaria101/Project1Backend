package dev.schulte.services.login;

import dev.schulte.entities.AppUser;

public interface LoginService {

    AppUser validateUser(String username, String password);
}
