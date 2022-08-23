package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.Birthday;

import java.util.List;

public interface BirthdayService {
    public List<Birthday> getAllBirthdays();
    public List<Birthday> getBirthdaysByUserId(Long userId);
    public Birthday save(Birthday birthday);
}
