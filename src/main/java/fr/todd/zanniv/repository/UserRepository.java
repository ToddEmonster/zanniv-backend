package fr.todd.zanniv.repository;

import fr.todd.zanniv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(Long userId);

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query("Update " +
                "User user " +
            "Set " +
                "user.password = :password " +
            "Where "
                + "user.id = :id "
    )
    int updatePassword(@Param("id") Long userId, @Param("password") String newPassword);
}
