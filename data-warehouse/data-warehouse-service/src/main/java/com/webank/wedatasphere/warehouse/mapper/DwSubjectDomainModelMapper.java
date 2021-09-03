package com.webank.wedatasphere.warehouse.mapper;

import com.webank.wedatasphere.warehouse.dao.domain.DwSubjectDomain;
import com.webank.wedatasphere.warehouse.dto.DwSubjectDomainDTO;
import com.webank.wedatasphere.warehouse.dto.DwSubjectDomainListItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DwSubjectDomainModelMapper {

    DwSubjectDomainModelMapper INSTANCE = Mappers.getMapper(DwSubjectDomainModelMapper.class);

    DwSubjectDomainDTO toDTO(DwSubjectDomain layer);

    List<DwSubjectDomainListItemDTO> toList(List<DwSubjectDomain> layers);

}
