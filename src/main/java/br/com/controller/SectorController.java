package br.com.controller;

import br.com.dto.SectorDTO;
import br.com.service.SectorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sector")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SectorController {

    private SectorService service;
    
    @GetMapping
    public ResponseEntity<List<SectorDTO>> findAll(){
        
        log.info("Searching all sectors.");
        
        List<SectorDTO> list = service.findAll();
        
        return ResponseEntity.ok().body(list);
    }
}
