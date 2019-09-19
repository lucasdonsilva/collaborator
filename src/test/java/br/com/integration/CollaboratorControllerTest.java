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

import static br.com.components.AppConstants.REQUEST_PATH_COLLABORATOR;
import static br.com.components.Messages.ONLY_NUMBERS;
import static br.com.support.ScenarioBuilder.generateCollaborator;
import static br.com.support.ScenarioBuilder.generateCollaboratorJSON;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CollaboratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Test
    public void methodCreateShouldReturnStatus201() throws Exception{

        mockMvc.perform(post(REQUEST_PATH_COLLABORATOR)
                .content(generateCollaboratorJSON())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void methodCreateShouldReturnStatus400BecauseTheBodyWillNotBeSend() throws Exception{

        mockMvc.perform(post(REQUEST_PATH_COLLABORATOR)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void methodDeleteShouldReturnStatus204() throws Exception{

        mockMvc.perform(delete(REQUEST_PATH_COLLABORATOR + "/123456789"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void methodDeleteShouldReturnStatus400BecauseTheCPFContainsCharacterImproper() throws Exception{

        mockMvc.perform(delete(REQUEST_PATH_COLLABORATOR + "/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(ONLY_NUMBERS));
    }

    @Test
    public void methodGetShouldReturnStatus200() throws Exception{

        Collaborator c = generateCollaborator();
        Sector s = new Sector();
        s.setId(1L);
        s.setDescription("Recursos Humanos");
        c.setSector(s);

        collaboratorRepository.insert(c);

        mockMvc.perform(get(REQUEST_PATH_COLLABORATOR + "/" + c.getCpf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value(c.getCpf()))
                .andExpect(jsonPath("$.name").value(c.getName()))
                .andExpect(jsonPath("$.phone").value(c.getPhone()))
                .andExpect(jsonPath("$.mail").value(c.getMail()))
                .andExpect(jsonPath("$.age").value(c.getAge()))
                .andExpect(jsonPath("$.sector.id").value(c.getSector().getId()))
                .andExpect(jsonPath("$.sector.description").value(c.getSector().getDescription()));
    }

    @Test
    public void methodGetShouldReturnStatus400BecauseTheCPFContainsCharacterImproper() throws Exception{

        mockMvc.perform(get(REQUEST_PATH_COLLABORATOR + "/abc"))
                .andExpect(status().isBadRequest());
    }
}
