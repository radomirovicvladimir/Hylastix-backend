package org.hylastix.mapper;

import org.hylastix.dto.ItemDTO;
import org.hylastix.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "itemName", target = "itemName"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "timeStored", target = "timeStored"),
            @Mapping(source = "bestBefore", target = "bestBefore")
    })
    ItemDTO toDTO(Item item);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "itemName", target = "itemName"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "timeStored", target = "timeStored"),
            @Mapping(source = "bestBefore", target = "bestBefore")
    })
    Item toEntity(ItemDTO itemDTO);
}