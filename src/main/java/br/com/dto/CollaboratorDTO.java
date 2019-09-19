package br.com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.components.Messages.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollaboratorDTO {

    @NotNull(message = CPF_EMPTY)
    private Long cpf;

    @NotBlank(message = NAME_EMPTY)
    private String name;

    @NotNull(message = PHONE_EMPTY)
    private Long phone;

    @NotBlank(message = MAIL_EMPTY)
    private String mail;

    @NotNull(message = AGE_EMPTY)
    private Integer age;

    @NotNull(message = SECTOR_EMPTY)
    private SectorDTO sector;
}