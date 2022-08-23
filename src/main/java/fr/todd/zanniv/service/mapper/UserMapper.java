package fr.todd.zanniv.service.mapper;

import fr.todd.zanniv.entity.User;
import fr.todd.zanniv.service.dto.UserCreateDTO;
import fr.todd.zanniv.service.dto.UserGetDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = { BirthdayMapper.class }
)
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class );

    UserGetDTO userToUserGetDto(User user);

    @InheritInverseConfiguration
    User userPostDtoToUser(UserCreateDTO userCreateDto);
}