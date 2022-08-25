package fr.todd.zanniv.repository;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.service.dto.BirthdayCreateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {

    boolean existsById(Long birthdayId);
    List<Birthday> findAllByUser(User user);
    Optional<Birthday> findByFirstnameAndLastname(String firstname, String lastname);

    @Modifying
    @Transactional
    @Query("Update " +
                "Birthday birthday " +
            "Set " +
                "birthday.date = :#{#updatedBirthday.date}, " +
                "birthday.firstname = :#{#updatedBirthday.firstname}, " +
                "birthday.lastname = :#{#updatedBirthday.lastname} " +
            "Where " +
                "birthday.id = :id "
    )
    int update(@Param("id") Long id, @Param("updatedBirthday") BirthdayCreateDTO updatedBirthday);
}
