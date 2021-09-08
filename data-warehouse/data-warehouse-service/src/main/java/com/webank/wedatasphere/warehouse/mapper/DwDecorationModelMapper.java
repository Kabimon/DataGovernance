package com.webank.wedatasphere.warehouse.mapper;

import com.webank.wedatasphere.warehouse.dao.domain.DwDecoration;
import com.webank.wedatasphere.warehouse.dto.DwDecorationDTO;
import com.webank.wedatasphere.warehouse.dto.DwDecorationListItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DwDecorationModelMapper {
    DwDecorationModelMapper INSTANCE = Mappers.getMapper(DwDecorationModelMapper.class);

    DwDecorationDTO toDTO(DwDecoration bean);

    List<DwDecorationListItemDTO> toList(List<DwDecoration> layers);
}
