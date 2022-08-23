package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.repository.BirthdayRepository;
import fr.todd.zanniv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirthdayServiceImpl implements BirthdayService {

    @Autowired
    private BirthdayRepository birthdayRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<Birthday> getAllBirthdays() {
        return this.birthdayRepository.findAll();
    }

    @Override
    public List<Birthday> getBirthdaysByUserId(Long userId) {
        // TODO surround with try catch from userService error "user not found"
        User user = this.userService.findById(userId);
        return this.birthdayRepository.findAllByUser(user);
    }

    @Override
    public Birthday save(Birthday birthday) {
        return this.birthdayRepository.save(birthday);
    }
}
