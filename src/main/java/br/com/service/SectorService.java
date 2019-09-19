package br.com.service;

import br.com.document.Sector;
import br.com.dto.CollaboratorDTO;
import br.com.dto.SectorDTO;
import br.com.mapper.CollaboratorMapper;
import br.com.mapper.SectorMapper;
import br.com.repository.CollaboratorRepository;
import br.com.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.configuration.CacheConfiguration.CACHE_SECTORS;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SectorService {
    
    private SectorRepository repository;
    private SectorMapper mapper;
    private CollaboratorRepository collaboratorRepository;
    private CollaboratorMapper collaboratorMapper;

    @Cacheable(CACHE_SECTORS)
    public List<SectorDTO> findAll() {
        List<CollaboratorDTO> collaborators = collaboratorMapper.map(collaboratorRepository.findAll());
        List<Sector> sectors = repository.findAll();
        List<SectorDTO> sectorsDTO = new ArrayList<>();

        sectors.forEach(s -> {
            SectorDTO sector = mapper.map(s);
            sector.setCollaborators(new ArrayList<>());
            collaborators.stream().filter(c -> c.getSector().getId().equals(s.getId())).forEach(c -> {
                CollaboratorDTO collaboratorDTO = new CollaboratorDTO();
                collaboratorDTO.setMail(c.getMail());
                collaboratorDTO.setName(c.getName());
                sector.getCollaborators().add(collaboratorDTO);
            });
            sectorsDTO.add(sector);
        });

        return sectorsDTO;
    }
}