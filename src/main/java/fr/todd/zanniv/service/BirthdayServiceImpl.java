package fr.todd.zanniv.service;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.exception.BirthdayNotFoundException;
import fr.todd.zanniv.exception.UserNotFoundException;
import fr.todd.zanniv.repository.BirthdayRepository;
import fr.todd.zanniv.service.dto.BirthdayCreateDTO;
import fr.todd.zanniv.service.dto.BirthdayDTO;
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
    public List<Birthday> getBirthdaysByUserId(Long userId) throws UserNotFoundException {
        try {
            User user = this.userService.getUserById(userId);
            return this.birthdayRepository.findAllByUser(user);
        } catch (UserNotFoundException e) {
            throw e;
        }
    }

    @Override
    public Birthday getBirthdayById(Long birthdayId) throws BirthdayNotFoundException {
        Optional<Birthday> optionalBirthday = this.birthdayRepository.findById(birthdayId);
        if (!optionalBirthday.isPresent()) {
            throw new BirthdayNotFoundException();
        }
        return optionalBirthday.get();
    }

    @Override
    public Birthday save(Birthday birthday) {
        return this.birthdayRepository.save(birthday);
        // TODO : cette personne a déjà un anniversaire ? (même firstname + lastname)
    }

    @Override
    public boolean update(BirthdayDTO updatedBirthday) throws BirthdayNotFoundException {
        if (!this.birthdayRepository.existsById(updatedBirthday.getId())) {
            throw new BirthdayNotFoundException();
        }
        return this.birthdayRepository.update(updatedBirthday.getId(), updatedBirthday) > 0;
    }

    @Override
    public void delete(Long birthdayId) throws BirthdayNotFoundException {
        if (!this.birthdayRepository.existsById(birthdayId)) {
            throw new BirthdayNotFoundException();
        } else {
            this.birthdayRepository.deleteById(birthdayId);
        }
    }
}
