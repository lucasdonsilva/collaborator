package br.com.support;

import br.com.document.Collaborator;
import br.com.document.Sector;
import br.com.dto.CollaboratorDTO;
import br.com.dto.SectorDTO;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.util.StreamUtils.copyToString;

public class ScenarioBuilder {

    private static final String COLLABORATOR_JSON = "collaborator.json";

    public static String generateCollaboratorJSON() throws IOException {

        return copyToString(new ClassPathResource(COLLABORATOR_JSON).getInputStream(), Charset.forName("UTF-8"));
    }

    public static CollaboratorDTO generateCollaboratorDTO() {
        CollaboratorDTO c = new CollaboratorDTO();
        c.setCpf(123456789L);
        c.setName("Lucas");
        c.setPhone(123456789L);
        c.setMail("lucas@mail.com");
        c.setAge(20);
        c.setSector(generateSectorDTO());
        return c;
    }

    public static Collaborator generateCollaborator() {
        Collaborator c = new Collaborator();
        c.setCpf(123456789L);
        c.setName("Lucas");
        c.setPhone(123456789L);
        c.setMail("lucas@mail.com");
        c.setAge(20);
        return c;
    }

    public static List<SectorDTO> generateListSectorDTO() {
        SectorDTO s = new SectorDTO();
        s.setId(1L);
        s.setDescription("RH");
        return singletonList(s);
    }

    public static SectorDTO generateSectorDTO() {
        SectorDTO s = new SectorDTO();
        s.setId(999L);
        s.setDescription("RH");
        return s;
    }

    public static Sector generateSector() {
        Sector s = new Sector();
        s.setId(999L);
        s.setDescription("RH");
        return s;
    }
}
