package br.com;

import br.com.document.Sector;
import br.com.repository.SectorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableCaching
@Slf4j
public class ContributorApplication implements CommandLineRunner {

	@Autowired
	private SectorRepository repository;

	@Override
	public void run(String... args) {

		Sector s1 = Sector.builder().id(1L).description("Recursos Humanos").build();
		Sector s2 = Sector.builder().id(2L).description("Diretoria").build();
		Sector s3 = Sector.builder().id(3L).description("Serviços Gerais").build();
		Sector s4 = Sector.builder().id(4L).description("Desenvolvimento").build();
		Sector s5 = Sector.builder().id(5L).description("Pré venda").build();

		List<Sector> sectors = asList(s1, s2, s3, s4, s5);

		repository.saveAll(sectors);
		log.info("Sectors available: " + sectors);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(ContributorApplication.class).run(args);
	}
}
