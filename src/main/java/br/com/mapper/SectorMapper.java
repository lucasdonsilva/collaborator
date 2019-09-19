package br.com.mapper;

import br.com.document.Sector;
import br.com.dto.SectorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectorMapper {

    public SectorDTO map(Sector sector);
}