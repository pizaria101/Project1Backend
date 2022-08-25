package dev.schulte.daos.appuser;

import dev.schulte.entities.AppUser;

public interface AppUserDAO {

    AppUser createAppUser(AppUser appUser);

    AppUser getUserByUsername(String username);
}
