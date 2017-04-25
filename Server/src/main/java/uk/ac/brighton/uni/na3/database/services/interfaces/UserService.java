package uk.ac.brighton.uni.na3.database.services.interfaces;

import uk.ac.brighton.uni.na3.database.entities.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User delete(String username);

    List<User> findAll();

    User update(User user);

    User findOne(String username);
}