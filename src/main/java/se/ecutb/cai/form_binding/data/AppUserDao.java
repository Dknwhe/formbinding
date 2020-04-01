package se.ecutb.cai.form_binding.data;

import se.ecutb.cai.form_binding.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserDao {

    AppUser save(AppUser appUser);
    Optional<AppUser> findById(String userId);
    Optional<AppUser> findByEmail(String email);
    List<AppUser> findAll();
    boolean delete(String userId);
    AppUser update(AppUser updatedUser);

}
