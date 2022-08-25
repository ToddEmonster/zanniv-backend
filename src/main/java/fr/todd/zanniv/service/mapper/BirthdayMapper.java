package fr.todd.zanniv.service.mapper;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.service.dto.BirthdayCreateDTO;
import fr.todd.zanniv.service.dto.BirthdayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface BirthdayMapper {

    BirthdayMapper MAPPER = Mappers.getMapper(BirthdayMapper.class);

    @Mapping(source = "birthday.id", target = "id")
    @Mapping(source = "birthday.date", target = "date")
    @Mapping(source = "birthday.firstname", target = "firstname")
    @Mapping(source = "birthday.lastname", target = "lastname")
    @Mapping(source = "birthday.user.id", target = "userId")
    BirthdayDTO toDto(Birthday birthday);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "birthdayDTO.date", target = "date")
    @Mapping(source = "birthdayDTO.firstname", target = "firstname")
    @Mapping(source = "birthdayDTO.lastname", target = "lastname")
    @Mapping(source = "user", target = "user")
    Birthday createDtoToEntity(BirthdayCreateDTO birthdayDTO, User user);

    @Mapping(source = "birthdayDTO.id", target = "id")
    @Mapping(source = "birthdayDTO.date", target = "date")
    @Mapping(source = "birthdayDTO.firstname", target = "firstname")
    @Mapping(source = "birthdayDTO.lastname", target = "lastname")
    @Mapping(source = "user", target = "user")
    Birthday toEntity(BirthdayDTO birthdayDTO, User user);

}