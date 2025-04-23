package org.hylastix.mapper;

import org.hylastix.dto.DeletedItemDTO;
import org.hylastix.model.DeletedItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeletedItemMapper {

    DeletedItemDTO toDTO(DeletedItem deletedItem);

    DeletedItem toEntity(DeletedItemDTO dto);
}
