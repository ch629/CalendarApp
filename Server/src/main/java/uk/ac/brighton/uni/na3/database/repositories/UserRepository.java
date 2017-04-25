package uk.ac.brighton.uni.na3.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.na3.database.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByForename(String forename);

    List<User> findBySurname(String surname); //TODO: Check these, I may only need a search query: "forename LIKE %?% OR surname LIKE %?%";
}