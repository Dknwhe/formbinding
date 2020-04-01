package se.ecutb.cai.form_binding.data;

import org.springframework.stereotype.Component;
import se.ecutb.cai.form_binding.entity.AppUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AppUserDaoInMemory implements AppUserDao {

    private List<AppUser> appUserList = new ArrayList<>();

    @Override
    public AppUser save(AppUser appUser) {
        if(appUserList.contains(appUser)) {
            throw new IllegalArgumentException();
        }
        appUserList.add(appUser);
        return findById(appUser.getUserId()).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Optional<AppUser> findById(String userId) {
        return appUserList.stream()
                .filter(appUser -> appUser.getUserId().equalsIgnoreCase(userId))
                .findFirst();
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return appUserList.stream()
                .filter(appUser -> appUser.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<AppUser> findAll() {
        return Collections.unmodifiableList(appUserList);
    }

    @Override
    public boolean delete(String userId) {
        Optional<AppUser> optional = findById(userId);
        if(optional.isPresent()) {
            return appUserList.remove(optional.get());
        }
        return false;
    }

    @Override
    public AppUser update(AppUser updatedUser) {
        AppUser original = findById(updatedUser.getUserId()).orElseThrow(IllegalArgumentException::new);
        original.setEmail(updatedUser.getEmail());
        original.setFirstName(updatedUser.getFirstName());
        original.setLastName(updatedUser.getLastName());
        original.setPassword(updatedUser.getPassword());
        return original;
    }

}
