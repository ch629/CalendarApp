package uk.ac.brighton.uni.na3.database.services.implementations;

import org.springframework.stereotype.Service;
import uk.ac.brighton.uni.na3.database.entities.UserAccount;
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
    public UserAccount create(UserAccount user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserAccount delete(String username) {
        UserAccount deletedUser = userRepository.findOne(username);
        if (deletedUser == null) return null;
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    @Transactional
    public List<UserAccount> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserAccount update(UserAccount user) {
        UserAccount updatedUser = userRepository.findOne(user.getUsername());
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
    public UserAccount findOne(String username) {
        UserAccount user = userRepository.findOne(username);
        if (user != null) user.getAttendingEvents().size();
        return user;
    }
}
