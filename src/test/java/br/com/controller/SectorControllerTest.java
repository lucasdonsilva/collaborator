package br.com.controller;

import br.com.dto.SectorDTO;
import br.com.service.SectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.support.ScenarioBuilder.generateListSectorDTO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class SectorControllerTest {

    @Mock
    private SectorService service;

    @InjectMocks
    private SectorController controller;

    @Test
    public void methodFindAllShouldReturnStatus200() {

        when(service.findAll()).thenReturn(generateListSectorDTO());

        ResponseEntity response = controller.findAll();

        List<SectorDTO> sectors = (List<SectorDTO>) response.getBody();

        assertEquals(response.getStatusCode(), OK);
        assertEquals(sectors.size(), 1);
    }
}
