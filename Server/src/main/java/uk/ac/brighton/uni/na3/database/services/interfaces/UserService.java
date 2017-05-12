package uk.ac.brighton.uni.na3.database.services.interfaces;

import uk.ac.brighton.uni.na3.database.entities.UserAccount;

import java.util.List;

public interface UserService {
    UserAccount create(UserAccount user);

    UserAccount delete(String username);

    List<UserAccount> findAll();

    UserAccount update(UserAccount user);

    UserAccount findOne(String username);
}