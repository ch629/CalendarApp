package uk.ac.brighton.uni.na3.database.services.implementations;

import org.springframework.stereotype.Service;
import uk.ac.brighton.uni.na3.database.entities.User;
import uk.ac.brighton.uni.na3.database.repositories.UserRepository;
import uk.ac.brighton.uni.na3.database.services.interfaces.UserService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User delete(String username) {
        User deletedUser = userRepository.findOne(username);
        if (deletedUser == null) return null;
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User update(User user) {
        User updatedUser = userRepository.findOne(user.getUsername());
        if (updatedUser == null) return null; //TODO: This depends on what needs updating with the user
        updatedUser.setAttendingEvents(user.getAttendingEvents());
        updatedUser.setAdmin(user.isAdmin());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setForename(user.getForename());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setPosition(user.getPosition());
        updatedUser.setSurname(user.getSurname());
        return updatedUser;
    }

    @Override
    @Transactional
    public User findOne(String username) {
        User user = userRepository.findOne(username);
        if (user != null) user.getAttendingEvents().size();
        return user;
    }
}
