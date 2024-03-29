# eDoe API
## Sobre a API
### API REST para o gerenciamento de doações para universitários no Campus IV da UFPB.

# 🔰 Tabela de conteúdo
* [Tecnologias envolvidas](#tec-env)
* [Executar o projeto](#exec)
* [Status do Projeto](#status)
* [Documentação da API](#doc)
* [Demonstração em vídeo](#demo)
* [Coleção JSON Insonmia](#json)

<h3 id="tec-env">⚡ Tecnologias envolvidas</h3>

 - [Java 11](#https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
 - [Spring Boot 2.5.6](#https://spring.io/projects/spring-boot)
 - [H2 Database](#https://mvnrepository.com/artifact/com.h2database/h2)
 - Swagger 3.0
 - Maven 

<h3 id="features">✅ Casos de Uso</h3>

 - [x] US 1 - Cadastro de usuários
 - [x] US 2 - Configurar papéis de usuários
 - [x] US 3 - Autenticar usuários no sistema
 - [x] US 4 - Cadastro de itens a serem doados
 - [x] US 5 - Gerar listagens dos itens a serem doados
 - [x] US 6 - Cadastro de itens necessários
 - [x] US 7 - Gerar listagens dos itens necessários
 - [x] US 8 - Identificar possíveis matches  de itens para doação e itens necessários
 - [x] US 9 - Realizar doações e manter o histórico de doações
 - [x] US 10 - Listar doações realizadas
 
 <h3 id="features">✅ Requisitos não funcionais</h3>
 
  - [ ] Um vídeo demonstração de no máximo 3 minutos da aplicação em execução.
  - [ ] Uma versão operacional implantada (deployed) do sistema, será optativo, mas altamente desejável.
  - [x] Todos os dados desta aplicação devem persistir em um banco de dados.
  - [x] Autenticação/autorização via JWT - JSON Web Token. O período de validade de um token deve ser definido pelo grupo e justificado no README.md.
  - [x] Manter repositório privado no github para backend . Os repositórios devem ser compartilhados com a professora (raquelvl).
  - [x] Os repositórios devem ser privados e compartilhados exclusivamente com os membros do grupo (além da professora). O grupo será penalizado se houver compartilhamento irregular em qualquer momento do desenvolvimento.
  - [x] Um readme.md no repositório do backend explicando sucintamente a API e informando os seguintes links: documentação swagger da API em operação e link para o backend implantado em operação (opcional).
  - [ ] Para quem implantar no heroku: a aplicação operacional já deve ter algumas campanhas e usuários criados previamente. No readme.md do repositório devem ser informadas as credenciais de pelo menos um usuário fictício já criado.
 
<h3 id="status">✅ Status do Projeto</h3>
<h5 align="center">🚧 Em construção 🚧</h5>
<h3 id="exec">✅ Execução do projeto</h3>

- Para executar o proejto basta entrar no diretório **api/** e rodar o comando **mvn spring-boot:run**.
- Obs.: É necessário ter o Maven instalado no SO.

<h3 id="doc">📖 Documentação da API</h3>
<p align="center">http://localhost:8080/v1/api/swagger-ui/ (Localmente)</p>
<h3 id="demo">🎬 Demonstração em vídeo</h3>
<h6>Vídeo curto (~ 3 min): https://youtu.be/9dSCThgUC64</h6>
<h6>Vídeo longo (mais detalhado por caso de uso): https://youtu.be/hu9gewj9d4k</h6>
<h5 id="json">Download da coleção de json do Insonmia: https://github.com/jobson-batista/edoe/files/7622090/Insomnia_2021-11-29.zip</h5>
