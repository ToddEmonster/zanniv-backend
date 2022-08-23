package fr.todd.zanniv.service.mapper;

import fr.todd.zanniv.entity.Birthday;
import fr.todd.zanniv.service.dto.BirthdayDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface BirthdayMapper {

    BirthdayMapper MAPPER = Mappers.getMapper(BirthdayMapper.class);

    Birthday toOrder(BirthdayDTO orderItemDto);

    @InheritInverseConfiguration
    BirthdayDTO fromOrder(Birthday orderItem);
}