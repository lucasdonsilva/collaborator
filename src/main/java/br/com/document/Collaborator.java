package br.com.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Collaborator {

	@Id
	private Long cpf;
	
	private String name;
	
	private Long phone;
	
	private String mail;

	private Integer age;

	private Sector sector;
}
