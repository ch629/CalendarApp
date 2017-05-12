package uk.ac.brighton.uni.na3.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.na3.database.entities.UserAccount;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, String> {
    List<UserAccount> findByForename(String forename);

    List<UserAccount> findBySurname(String surname); //TODO: Check these, I may only need a search query: "forename LIKE %?% OR surname LIKE %?%";
}