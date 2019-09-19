package br.com.controller;

import br.com.dto.CollaboratorDTO;
import br.com.service.CollaboratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.components.AppConstants.REQUEST_PATH_COLLABORATOR;
import static br.com.configuration.CacheConfiguration.CACHE_SECTORS;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(REQUEST_PATH_COLLABORATOR)
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CollaboratorController {

	private CollaboratorService service;
	
	@PostMapping
	@CacheEvict(value = CACHE_SECTORS, allEntries = true)
	public ResponseEntity create(@Valid @RequestBody CollaboratorDTO collaborator) {
		
		log.info("Creating a collaborator with values: {}", collaborator);

		service.create(collaborator);

		return ResponseEntity.status(CREATED).build();
	}
	
	@DeleteMapping("/{cpf}")
	@CacheEvict(value = CACHE_SECTORS, allEntries = true)
	public ResponseEntity delete(@PathVariable Long cpf){

		log.info("Deleting a collaborator with cpf: {}", cpf);

		service.delete(cpf);
        
	    return ResponseEntity.noContent().build();
	}

    @GetMapping("/{cpf}")
    public ResponseEntity findById(@PathVariable Long cpf){

		log.info("Searching a collaborator with cpf: {}", cpf);
        
        CollaboratorDTO c = service.findById(cpf);
        
        return ResponseEntity.ok().body(c);
    }
}