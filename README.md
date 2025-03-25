# Gestão de Clientes

## Descrição do Projeto
Este projeto é um sistema simples de gestão de clientes, onde é possível cadastrar, visualizar, editar e excluir clientes. A aplicação foi desenvolvida com Spring Boot (backend) e HTML, CSS e JavaScript (frontend).

## Funcionalidades
- [x]  Listagem de clientes  
- [x] Cadastro de novos clientes  
- [x] Edição de clientes existentes  
- [x] Exclusão de clientes  
- [x] Pesquisa de clientes por nome ou CPF  
- [x] Exibição de detalhes do cliente

## Tecnologias Utilizadas
### Backend
- Java (Spring Boot)
- Spring Data JPA (persistência de dados)
- MySQL (banco de dados em memória)
- Hibernate(mapeamento objeto relacional)
- Spring Validation (validações)

### Frontend
- HTML5, CSS3 e JavaScript
- Fetch API (para consumir a API REST)

## Como Rodar o Projeto
### **1️⃣ Pré-requisitos**
Antes de começar, você precisará ter instalado:

- JDK 21+
- Maven
- Banco de Dados MySQL

### **2️⃣ Clonar o Repositório**
```bash
git clone https://github.com/seuusuario/gestao-clientes.git
cd gestao-clientes
```

### **3️⃣ Configurar Banco de Dados**
 Usando Mysql, configure o `application.properties` no backend:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestaoContatos?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
```
> **Nota:** Lembre-se de alterar os dados de `username` e `password` para suas credenciais do banco de dados.

### **4️⃣ Rodar a Aplicação**
Para rodar a aplicação use o comando abaixo no terminal ou se estiver usando uma IDE apenas execute o `GestaodecontatosApplication.java` 
```bash
mvn spring-boot:run
```
A aplicação será executada em:
http://localhost:8080

### **5️⃣Popular banco de dados**
Para popular o banco de dados abra o terminal ou mysql:
```bash
mysql -u seu-usuario -p
use gestaoContatos
```
Após isso acesse copie o script de população de dados do banco no diretorio `gestaodecontatos\src\main\resources\db\data.sql`, copie o codigo e insira no terminal após isso o banco de dados está populado com dados hipoteticos para teste.

## Endpoints da API
### **Cliente**

| Método | Endpoint                        | Descrição                                          |
|--------|---------------------------------|----------------------------------------------------|
| GET    | /clientes/listar                | Lista todos os clientes                            |
| GET    | /clientes/{id}                  | Busca um cliente pelo ID                           |
| POST   | /clientes/salvar                | Adiciona um novo cliente                           |
| PUT    | /clientes/{id}                  | Atualiza um cliente                                |
| DELETE | /clientes/{id}                  | Remove um cliente                                  |
| GET    | /clientes/buscar                | Busca clientes por nome ou CPF                     |



### Contato

| Método | Endpoint                        | Descrição                                          |
|--------|---------------------------------|----------------------------------------------------|
| GET    | /contatos/cliente/{clienteId}   | Lista os contatos de um cliente pelo `clienteId`   |
| POST   | /contatos/{clienteId}           | Adiciona um novo contato para o `clienteId`        |
| PUT    | /contatos/{id}                  | Atualiza um contato existente pelo `id`            |
| DELETE | /contatos/{id}                  | Remove um contato pelo `id`                        |

## Possíveis Melhorias
- Melhorar interface incluindo tratamentos de erro e exeções melhorando a experiencia do usuário.
- Criando maneiras de organizar e filtrar dados dos clientes listados.
- Criar testes automatizados para o backend.

## Considerações Finais

Este projeto foi desenvolvido como parte de um desafio de estágio, com o objetivo de demonstrar o conhecimento nas tecnologias de frontend (HTML, CSS, JavaScript) e backend (Java com Spring Boot), além da capacidade de interpretar requisitos e transformá-los em uma solução tecnológica funcional.

Durante o desenvolvimento, foi possível aplicar e aprofundar conceitos fundamentais de ambas as tecnologias, como:

- **Frontend**:
    - Estruturação e estilização de páginas web com HTML e CSS.
    - Interatividade com JavaScript e consumo de APIs RESTful para integrar o frontend com o backend.

- **Backend**:
    - Construção de uma API RESTful utilizando Spring Boot, com persistência de dados via Spring Data JPA.
    - Validação de dados, gerenciamento de exceções e integração com o banco de dados.

Além de aplicar esses conceitos técnicos, o desafio também exigiu a habilidade de transformar requisitos técnicos e funcionais em uma solução completa, com uma boa estrutura de código, documentação clara e interface de fácil utilização.

Esse exercício contribuiu significativamente para o meu aprendizado prático e para o desenvolvimento de soluções reais dentro do contexto de tecnologias amplamente utilizadas no mercado de trabalho.

Acredito que este projeto reflete a minha capacidade de resolver problemas, aplicar boas práticas de desenvolvimento e entregar uma solução de software funcional e bem estruturada.

Estou ansioso para aplicar esses conhecimentos e continuar desenvolvendo minhas habilidades profissionais.

## Referências
- [Boxicons - 2.1.1](https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css) - Biblioteca de ícones simples e leve para uso em projetos web.
- [Spring Boot Documentation](https://spring.io/projects/spring-boot) - Documentação oficial do Spring Boot.
- [Spring Boot Accessing data with Mysql](https://spring.io/guides/gs/accessing-data-mysql) - Documentação para usar Mysql com Spring Boot
- [Spring Boot CRUD 2024 Java MySQL Backend](https://www.youtube.com/watch?v=68EIdHbE74U) - Video do Youtube.
- [[ADI #1] - Criando um CRUD com Java Spring Boot e MySQL](https://www.youtube.com/watch?v=Tnl4YnB6E54&t=359s) - Video do Youtube.
- [MySQL Documentation](https://dev.mysql.com/doc/) - Documentação oficial do MySQL.
- [MDN Web Docs](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript) - Documentação JavaScript.
- [W3 Schools](https://www.w3schools.com/js/js_htmldom_document.asp)- Documentação JavaScript.
- [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) - Documentação da Fetch API.

## Autor
Desenvolvido por Daniel Almeida Andrade.


