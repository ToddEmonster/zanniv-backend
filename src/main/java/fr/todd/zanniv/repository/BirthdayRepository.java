package fr.todd.zanniv.repository;

import fr.todd.zanniv.entity.Birthday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {

}
