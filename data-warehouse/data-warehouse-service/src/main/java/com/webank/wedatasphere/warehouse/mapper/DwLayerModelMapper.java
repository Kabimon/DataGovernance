package com.webank.wedatasphere.warehouse.mapper;

import com.webank.wedatasphere.warehouse.dao.domain.DwLayer;
import com.webank.wedatasphere.warehouse.dto.DwLayerDTO;
import com.webank.wedatasphere.warehouse.dto.DwLayerListItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DwLayerModelMapper {

    DwLayerModelMapper INSTANCE = Mappers.getMapper(DwLayerModelMapper.class);

    DwLayerDTO toDTO(DwLayer layer);

    List<DwLayerListItemDTO> toList(List<DwLayer> layers);

}
