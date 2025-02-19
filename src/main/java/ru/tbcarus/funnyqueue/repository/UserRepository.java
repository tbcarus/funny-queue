package ru.tbcarus.funnyqueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.tbcarus.funnyqueue.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String Email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled = NOT u.enabled WHERE u.id = :id")
    void reverseEnableUser(int id);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r = 'SUPERUSER'")
    List<User> findAllQueueOwners();

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);
}
