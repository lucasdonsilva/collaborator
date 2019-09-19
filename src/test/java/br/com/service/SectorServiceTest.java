package br.com.service;

import br.com.document.Collaborator;
import br.com.document.Sector;
import br.com.dto.CollaboratorDTO;
import br.com.dto.SectorDTO;
import br.com.mapper.CollaboratorMapper;
import br.com.mapper.SectorMapper;
import br.com.repository.CollaboratorRepository;
import br.com.repository.SectorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.support.ScenarioBuilder.*;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SectorServiceTest {

    @Mock
    private SectorRepository repository;

    @Mock
    private SectorMapper mapper;

    @Mock
    private CollaboratorRepository collaboratorRepository;

    @Mock
    private CollaboratorMapper collaboratorMapper;

    @InjectMocks
    private SectorService service;

    @Test
    public void methodFindAllShouldReturnSectorsWithCollaboratorsWithOnlyNameAndMail(){

        List<Collaborator> collaborators = singletonList(generateCollaborator());
        List<Sector> sectors = singletonList(generateSector());
        List<CollaboratorDTO> collaboratorsDTO = singletonList(generateCollaboratorDTO());
        SectorDTO sectorDTO = generateSectorDTO();

        when(collaboratorRepository.findAll()).thenReturn(collaborators);
        when(collaboratorMapper.map(collaborators)).thenReturn(collaboratorsDTO);
        when(repository.findAll()).thenReturn(sectors);
        when(mapper.map(any())).thenReturn(sectorDTO);

        List<SectorDTO> sectorsDTO = service.findAll();

        assertNull(sectorsDTO.get(0).getCollaborators().get(0).getSector());
        assertNull(sectorsDTO.get(0).getCollaborators().get(0).getAge());
        assertNull(sectorsDTO.get(0).getCollaborators().get(0).getPhone());
        assertNull(sectorsDTO.get(0).getCollaborators().get(0).getCpf());
        assertEquals(sectorsDTO.get(0).getId(), sectorDTO.getId());
        assertEquals(sectorsDTO.get(0).getDescription(), sectorDTO.getDescription());
        assertEquals(sectorsDTO.get(0).getCollaborators().get(0).getName(), collaboratorsDTO.get(0).getName());
        assertEquals(sectorsDTO.get(0).getCollaborators().get(0).getMail(), collaboratorsDTO.get(0).getMail());
    }
}
