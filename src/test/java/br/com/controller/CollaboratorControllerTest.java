package br.com.controller;

import br.com.dto.CollaboratorDTO;
import br.com.service.CollaboratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static br.com.support.ScenarioBuilder.generateCollaboratorDTO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorControllerTest {

    @Mock
    private CollaboratorService service;

    @InjectMocks
    private CollaboratorController controller;

    @Test
    public void methodCreateShouldReturnStatus201() {

        CollaboratorDTO collaboratorDTO = mock(CollaboratorDTO.class);

        ResponseEntity response = controller.create(collaboratorDTO);

        assertEquals(response.getStatusCode(), CREATED);
        verify(service).create(collaboratorDTO);
    }


    @Test
    public void methodDeleteShouldReturnStatus204() {

        Long cpf = 123L;

        ResponseEntity response = controller.delete(cpf);

        assertEquals(response.getStatusCode(), NO_CONTENT);
        verify(service).delete(cpf);
    }


    @Test
    public void methodGetShouldReturnStatus200() {

        CollaboratorDTO c = generateCollaboratorDTO();
        when(service.findById(c.getCpf())).thenReturn(c);

        ResponseEntity response = controller.findById(c.getCpf());

        CollaboratorDTO body = (CollaboratorDTO) response.getBody();

        assertEquals(response.getStatusCode(), OK);
        assertEquals(body.getCpf(), c.getCpf());
        assertEquals(body.getPhone(), c.getPhone());
        assertEquals(body.getAge(), c.getAge());
        assertEquals(body.getSector(), c.getSector());
        assertEquals(body.getMail(), c.getMail());
        assertEquals(body.getName(), c.getName());
    }
}