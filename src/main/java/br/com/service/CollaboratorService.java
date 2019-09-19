package br.com.service;

import br.com.document.Collaborator;
import br.com.dto.CollaboratorDTO;
import br.com.dto.SectorDTO;
import br.com.exception.AgeInvalidException;
import br.com.exception.CollaboratorAlreadyExistsException;
import br.com.exception.CollaboratorNotFoundException;
import br.com.exception.SectorNotFoundException;
import br.com.mapper.CollaboratorMapper;
import br.com.mapper.SectorMapper;
import br.com.repository.CollaboratorRepository;
import br.com.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.components.AppConstants.TWENTY;
import static br.com.components.CalculatorUtil.calculatePercentage;
import static br.com.components.Messages.*;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CollaboratorService {

    private CollaboratorRepository repository;
    private CollaboratorMapper mapper;
    private SectorRepository sectorRepository;
    private SectorMapper sectorMapper;

	public void create(CollaboratorDTO collaborator) {

	    Optional<Collaborator> c = repository.findById(collaborator.getCpf());

	    if(c.isPresent()) throw new CollaboratorAlreadyExistsException(COLLABORATOR_ALREADY_EXISTS);

        SectorDTO sectorDTO = sectorMapper.map(sectorRepository.findById(collaborator.getSector().getId()).orElseThrow(() -> new SectorNotFoundException(SECTOR_ID_NOT_FOUND)));

        if(nonNull(sectorDTO)) {
            ageValidate(collaborator);
            collaborator.getSector().setDescription(sectorDTO.getDescription());
            repository.insert(mapper.map(collaborator));
        }
	}

    public void delete(Long cpf) {
        repository.deleteById(cpf);
    }

    public CollaboratorDTO findById(Long cpf) {
        Collaborator c = repository.findById(cpf).orElseThrow(() -> new CollaboratorNotFoundException(CPF_NOT_FOUND));
        return mapper.map(c);
    }

    private void ageValidate(CollaboratorDTO collaborator) {

	    if(collaborator.getAge() < 18) {
            List<Collaborator> collaborators = repository.findBySectorIdIs(collaborator.getSector().getId());
            Long count = collaborators.stream().filter(c -> c.getAge() < 18).count() + 1;
            if(calculatePercentage(count, collaborators.size() + 1).compareTo(TWENTY) > 0) throw new AgeInvalidException(AGE_LESS_THAN_18);

        } else if (collaborator.getAge() > 65){
            List<Collaborator> collaborators = repository.findAll();
            Long count = collaborators.stream().filter(c -> c.getAge() > 65).count() + 1;
            if(calculatePercentage(count, collaborators.size() + 1).compareTo(TWENTY) > 0) throw new AgeInvalidException(AGE_GREATHER_THAN_65);

        }
    }
}
