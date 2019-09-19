package br.com.integration;

import br.com.document.Collaborator;
import br.com.document.Sector;
import br.com.repository.CollaboratorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.components.AppConstants.REQUEST_PATH_SECTOR;
import static br.com.support.ScenarioBuilder.generateCollaborator;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Test
    public void methodGetShouldReturnStatus200AndBodyWithSectorsWithCollaboratorsOnlyNameAndMail() throws Exception{

        Collaborator c = generateCollaborator();
        c.setCpf(123L);
        Sector s = new Sector();
        s.setId(1L);
        s.setDescription("Recursos Humanos");
        c.setSector(s);

        collaboratorRepository.insert(c);

        mockMvc.perform(get(REQUEST_PATH_SECTOR))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(s.getId()))
                .andExpect(jsonPath("$[0].description").value(s.getDescription()))
                .andExpect(jsonPath("$[0].collaborators[0].name").value(c.getName()))
                .andExpect(jsonPath("$[0].collaborators[0].mail").value(c.getMail()));
    }
}
