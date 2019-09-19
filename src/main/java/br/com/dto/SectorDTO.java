package br.com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

import static br.com.components.Messages.SECTOR_ID_EMPTY;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectorDTO{

    @NotNull(message = SECTOR_ID_EMPTY)
    private Long id;

    private String description;

    private List<CollaboratorDTO> collaborators;
}
