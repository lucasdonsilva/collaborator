package br.com.mapper;

import br.com.document.Collaborator;
import br.com.dto.CollaboratorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollaboratorMapper {

    CollaboratorDTO map(Collaborator collaborator);
    Collaborator map(CollaboratorDTO collaboratorDTO);
    List<CollaboratorDTO> map(List<Collaborator> collaborators);
}