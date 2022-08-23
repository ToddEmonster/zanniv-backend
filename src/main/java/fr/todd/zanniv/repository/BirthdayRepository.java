package fr.todd.zanniv.repository;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {
    List<Birthday> findAllByUser(User user);
}
