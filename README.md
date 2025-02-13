<h1 align="center">
  Lista de contatos
</h1>


 Este projeto é uma API RESTful para gerenciar um catálogo de contatos, permitindo as operações de criar, ler, atualizar e excluir contatos.
## Tecnologias
---
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [PostgreSQL](https://www.postgresql.org/download/)

## Práticas adotadas
---

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Como Executar
---

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/ListaDeContatos-0.0.1-SNAPSHOT.jar
```

O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## Configuração do Banco de Dados
---

- Crie um banco de dados no PostgreSQL(ou outro banco que voçê usa).

- Atualize o arquivo application.properties com as configurações do banco de dados

~~~properties

spring.datasource.url=jdbc:postgresql://localhost:5432/listContact
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update

~~~


## API Endpoints
---

### 1. `GET /list`  
- **Descrição**: Retorna a lista completa de contatos salvos.

**Resposta**:  
- **200 OK**: Retorna a lista de contatos em formato JSON. Se não houver contatos, retorna uma lista vazia [].

- **Exemplo de resposta:**  

~~~json
    [
  {
    "name": "João Silva",
    "number": "12345678910",
    "email": "joao@exemplo.com"
  },
  {
    "name": "Maria Oliveira",
    "number": "98765432110",
    "email": ""
  }
]
~~~

### 2. `GET /get/{name}`  
- **Descrição**: Retorna um ou mais contatos que contenham o nome especificado.  
 
- **Parâmetros:**  
- **name**: Nome (ou parte do nome) do contato a ser pesquisado.

**Resposta**:  
- **200 OK**: Retorna os contatos encontrados que correspondem ao nome fornecido e se não houver contatos, retorna uma lista vazia [].   

- **Exemplo de URL:** **/get/maria** 

- **Exemplo de resposta:**   

~~~json
    [
  {
    "name": "Maria Oliveira",
    "number": "98765432110",
    "email": ""
  }
]
~~~

### 3. `POST /newContact`  
- **Descrição**: Cria um novo contato com os dados fornecidos no corpo da requisição.    
  
- **Requisitos obrigatórios:**  
 `name`: Não será válido o uso de nomes repetidos ou deixar o campo em branco.  
 `number`: É necessário que o número tenha entre que 10 a 15 dígitos.    
 `email`: É possível deixar esse campo vazio, mas se for colocar um email, para ele ser válido será necessário que haja o @ em sua composição.

 
- **Body:**
  
~~~json  
  {
    "name": "Maria Oliveira",
    "number": "98765432110",
    "email": "maria@gmail.com"
  }   
           OU
           
   {
    "name": "Maria Oliveira",
    "number": "98765432110",
    "email": ""
  } 
~~~

**Resposta**:  
- **200 OK**: Retorna o contato criado.  
- **400 Bad Request**: Se os dados fornecidos não forem válidos (por exemplo, se o nome ou o número estiverem ausentes).  

### 4. `PUT /update/{name}`

- **Descrição:** Atualiza os dados de um contato existente com base no nome fornecido.
- **Parâmetros:**  
  `name`: Nome do contato a ser atualizado.

- **Body:**
  
~~~json  
  {
    "name": "João Silva",
    "number": "98765432110",
    "email": "João@gmail.com"
  }   
~~~

**Resposta**:  
- **200 OK**: Retorna a lista de contatos após a atualização.  
- **400 Bad Request**: Se os dados forem inválidos ou se o nome digitado não existir no Banco de dados.

### 5. `DELETE /delete/{name}`

- **Descrição:** Deleta o contato com o nome especificado.
- **Parâmetros:**  
  `name`: Nome do contato a ser deletado.


**Resposta**:  
- **200 OK**: Retorna a lista de contatos após a exclusão.  
- **400 Bad Request**: Se o contato não for encontrado.   

  


