# Collaborator.

O projeto consiste em um crud de colaborador, que tenha dados persistidos em uma base e que considere as boas práticas de desenvolvimento.

## Tecnologias utilizadas.

- Java 8.
- Spring Boot, Data, MVC e Actuator.
- Junit 4.
- Mockito.
- Embed mongo.

## Para rodar o projeto, execute os passos que estão descritos abaixo.

- Importar o projeto na sua IDE como um projeto maven.
- Baixar todas as dependências que estão configuradas no pom.
- Executar o main da classe br.com.CollaboratorApplication

## Endpoints disponíveis.

### Método POST: http://localhost:8080/collaborator

**Descrição:** Insere um novo colaborador.
Request body: Enviar um JSON com os campos "cpf", "name", "phone", "mail", "age" e "sector" com "id" como atributo. Todos os campos do JSON são obrigatórios.

Ex:

```
{
    "cpf": 123456789,
    "name": "Lucas",
    "phone": 123456789,
    "mail": "lucas@gmail.com",
    "age": 20,
    "sector": {
        "id": 1
    }
}
```

**Response Codes:** 201 (Created) - Criado com sucesso. 400 (Bad Request) - Conforme foi solicitado, qualquer requisição inválida recebe esse status. 404 (Not Found) - O id do sector não foi encontrado na base de dados.

### Método DELETE: http://localhost:8080/collaborator/{cpf}

**Descrição:** Deleta um colaborador pelo CPF.

**Response Codes:** 204 (No content) - Deletado com sucesso. 400 (Bad Request) - Conforme foi solicitado, qualquer requisição inválida recebe esse status. 404 (Not Found) - O cpf passado não foi encontrado na base de dados.

### Método GET: http://localhost:8080/collaborator/{cpf}

**Descrição:** Obtém um colaborador pelo CPF.

**Response Codes:** 200 (Success) - Obtido com sucesso. 400 (Bad Request) - Conforme foi solicitado, qualquer requisição inválida recebe esse status. 404 (Not Found) - O cpf passado não foi encontrado na base de dados.

**Response Data:** 

```
{
    "cpf": 123456789,
    "name": "Lucas",
    "phone": 123456789,
    "mail": "lucas@gmail.com",
    "age": 20,
    "sector": {
        "id": 4,
        "description" : "Desenvolvimento"
    }
}
```

### Método GET: http://localhost:8080/sector

**Descrição:** Obtém todos os setores com seus colaboradores agrupados.

**Response Codes:** 200 (Success) - Obtido com sucesso. 400 (Bad Request) - Conforme foi solicitado, qualquer requisição inválida recebe esse status. 404 (Not Found) - O cpf passado não foi encontrado na base de dados.

**Response Data:** 

```
[
    {
        "id": 4,
        "description": "Desenvolvimento",
        "collaborators": [
            {
                "name": "Lucas",
                "mail": "lucasdonsilva@gmail.com"
            }
        ]
    }
]
```

## Build do projeto e execução dos testes

Ao executar esse comando o maven irá realizar o processo de build que contém a execução dos testes.
 
 É recomendado o uso do primeiro comando pois garante que você executara o build na mesma versão do maven que foi criado. 

    ./mvnw clean package ou mvn clean package


## Rodando com docker

Para rodar o projeto com docker é necessário estar na pasta raiz do projeto e seguir os passos abaixo.

- executar ```docker-compose up``` para subir a aplicação.

## Monitoramento

Como o projeto utiliza o Actuator, é possível consultar a saúde do serviço por exemplo pelo endereço:

- http://localhost:8080/actuator/health
