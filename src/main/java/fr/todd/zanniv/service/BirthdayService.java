package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.exception.BirthdayNotFoundException;
import fr.todd.zanniv.exception.UserNotFoundException;
import fr.todd.zanniv.service.dto.BirthdayCreateDTO;
import fr.todd.zanniv.service.dto.BirthdayDTO;

import java.util.List;

public interface BirthdayService {
    public List<Birthday> getAllBirthdays();
    public List<Birthday> getBirthdaysByUserId(Long userId) throws UserNotFoundException;
    public Birthday getBirthdayById(Long birthdayId) throws BirthdayNotFoundException;
    public Birthday save(Birthday birthday);
    public boolean update(BirthdayDTO updatedBirthday) throws BirthdayNotFoundException;
    public void delete(Long birthdayId) throws BirthdayNotFoundException;
}
