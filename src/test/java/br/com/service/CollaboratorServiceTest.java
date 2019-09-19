package br.com.service;

import br.com.document.Collaborator;
import br.com.document.Sector;
import br.com.dto.CollaboratorDTO;
import br.com.exception.CollaboratorAlreadyExistsException;
import br.com.exception.CollaboratorNotFoundException;
import br.com.exception.SectorNotFoundException;
import br.com.mapper.CollaboratorMapper;
import br.com.mapper.SectorMapper;
import br.com.repository.CollaboratorRepository;
import br.com.repository.SectorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.support.ScenarioBuilder.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorServiceTest {

    @Mock
    private CollaboratorRepository repository;

    @Mock
    private CollaboratorMapper mapper;

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private SectorMapper sectorMapper;

    @InjectMocks
    private CollaboratorService service;

    @Test
    public void methodCreateShouldReturnSuccess() {

        Sector sector = generateSector();
        CollaboratorDTO collaboratorDTO = generateCollaboratorDTO();
        Collaborator collaborator = generateCollaborator();

        when(repository.findById(anyLong())).thenReturn(empty());
        when(sectorRepository.findById(999L)).thenReturn(of(sector));
        when(sectorMapper.map(sector)).thenReturn(generateSectorDTO());
        when(mapper.map(collaboratorDTO)).thenReturn(collaborator);

        service.create(collaboratorDTO);

        verify(repository).insert(collaborator);
    }

    @Test(expected = CollaboratorAlreadyExistsException.class)
    public void methodCreateShouldThrowCollaboratorAlreadyExistsException() {

        when(repository.findById(anyLong())).thenReturn(of(generateCollaborator()));

        service.create(generateCollaboratorDTO());
    }

    @Test(expected = SectorNotFoundException.class)
    public void methodCreateShouldThrowSectorNotFoundException() {

        CollaboratorDTO collaboratorDTO = generateCollaboratorDTO();
        Collaborator collaborator = generateCollaborator();

        when(repository.findById(anyLong())).thenReturn(empty());
        when(sectorRepository.findById(999L)).thenReturn(empty());

        service.create(collaboratorDTO);

        verify(repository).insert(collaborator);
    }

    @Test
    public void methodDeleteShouldReturnSuccess() {

        service.delete(123L);
        verify(repository).deleteById(123L);
    }

    @Test
    public void methodFindByIdShouldReturnSuccess(){

        CollaboratorDTO collaboratorDTO = generateCollaboratorDTO();
        Collaborator collaborator = generateCollaborator();

        when(repository.findById(123L)).thenReturn(of(collaborator));
        when(mapper.map(collaborator)).thenReturn(collaboratorDTO);

        CollaboratorDTO c = service.findById(123L);

        assertEquals(collaboratorDTO.getCpf(), c.getCpf());
        assertEquals(collaboratorDTO.getPhone(), c.getPhone());
        assertEquals(collaboratorDTO.getAge(), c.getAge());
        assertEquals(collaboratorDTO.getMail(), c.getMail());
        assertEquals(collaboratorDTO.getName(), c.getName());
        assertEquals(collaboratorDTO.getSector(), c.getSector());
    }

    @Test(expected = CollaboratorNotFoundException.class)
    public void methodFindByIdShouldReturnCollaboratorNotFoundException(){

        when(repository.findById(123L)).thenReturn(empty());
        CollaboratorDTO c = service.findById(123L);
    }
}
