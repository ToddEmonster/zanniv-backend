package fr.todd.zanniv.controller;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.exception.BirthdayNotFoundException;
import fr.todd.zanniv.service.BirthdayService;
import fr.todd.zanniv.service.UserService;
import fr.todd.zanniv.service.dto.BirthdayCreateDTO;
import fr.todd.zanniv.service.dto.BirthdayDTO;
import fr.todd.zanniv.service.mapper.BirthdayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/birthdays")
public class BirthdayController {

    @Autowired
    private BirthdayMapper birthdayMapper;

    @Autowired
    private BirthdayService birthdayService;

    @Autowired
    private UserService userService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<BirthdayDTO>> getBirthdays() {
        List<BirthdayDTO> allBirthdays = this.birthdayService
                .getAllBirthdays()
                .stream()
                .map(this.birthdayMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allBirthdays, HttpStatus.OK);
    }

    @GetMapping(value = { "/{birthdayId}"})
    public ResponseEntity<BirthdayDTO> getBirthdayById(@PathVariable("birthdayId") Long birthdayId) {
        try {
            Birthday birthday = this.birthdayService.getBirthdayById(birthdayId);
            return new ResponseEntity<>(this.birthdayMapper.toDto(birthday), HttpStatus.OK);
        } catch (BirthdayNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value= "/userId={userId}")
    public ResponseEntity<List<BirthdayDTO>> getUserBirthdays(@PathVariable("userId") Long userId) {
        if (!this.userService.existsById(userId)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<BirthdayDTO> birthdaysDto = this.birthdayService
                .getBirthdaysByUserId(userId)
                .stream()
                .map(this.birthdayMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(birthdaysDto, HttpStatus.FOUND);
    }

    // TOEVOL : créer un DTO spécial Update pour virer le userId qui est un peu inutile
    @PutMapping(value="")
    public ResponseEntity<Boolean> update(
            @RequestBody BirthdayDTO updatedBirthdayDTO) {
        try {
            this.birthdayService.update(updatedBirthdayDTO);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (BirthdayNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/{birthdayId}")
    public ResponseEntity<Boolean> delete(@PathVariable("birthdayId") Long birthdayId) {
        try {
            this.birthdayService.delete(birthdayId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (BirthdayNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
