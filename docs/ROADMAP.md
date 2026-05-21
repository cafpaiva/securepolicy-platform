# Roadmap SecurePolicy

Este roadmap transforma o prototipo atual em uma aplicacao profissional, evoluindo em entregas pequenas, revisaveis e testaveis.

## Visao do produto

Criar uma plataforma interna de seguradora para gestao de apolices, sinistros e risco operacional, com backend Java/Spring Boot, frontend React, micro-frontends e uma base forte de qualidade de codigo.

## Objetivos tecnicos

- Adotar Clean Architecture no backend.
- Separar dominio, use cases, adapters REST e adapters de persistencia.
- Criar testes unitarios, testes de API e testes BDD.
- Evoluir o React para uma arquitetura por features.
- Incluir testes unitarios e de integracao no frontend.
- Evoluir micro-frontends de demonstracao para composicao profissional.
- Implantar padroes de mercado: GitFlow, Conventional Commits, PR review, CI e documentacao.

## Fases

### Fase 0 - Fundacao do projeto

Status: em andamento.

Entregas:

- Estrutura inicial backend Spring Boot.
- Estrutura inicial React.
- Micro-frontend Angular demonstrativo.
- Documentacao de roadmap, sprints, GitFlow e issues.
- Inicializacao Git com branch `main`.

### Fase 1 - Clean Architecture no backend

Entregas:

- Separar `domain`, `application` e `infrastructure`.
- Criar use cases para apolices, sinistros e dashboard.
- Criar portas de entrada e saida.
- Isolar JPA em adapters de persistencia.
- Remover regra de negocio dos controllers e services Spring.

### Fase 2 - Qualidade e testes backend

Entregas:

- Testes unitarios com JUnit 5 e Mockito.
- Testes de repository com `@DataJpaTest`.
- Testes REST com MockMvc.
- Testes BDD de API com Cucumber ou RestAssured.
- Cobertura minima inicial para use cases criticos.

### Fase 3 - Frontend React profissional

Entregas:

- Reorganizar por features: dashboard, policies e claims.
- Criar camada de API client.
- Criar design system basico compartilhado.
- Melhorar loading, error, empty states e validacoes.
- Melhorar acessibilidade e responsividade.

### Fase 4 - Testes frontend

Entregas:

- Vitest.
- React Testing Library.
- Testes de componentes.
- Testes de stores Zustand.
- Testes de fluxos principais.

### Fase 5 - Micro-frontends

Entregas:

- Definir estrategia: Module Federation, Single-SPA ou Web Components.
- Criar shell principal.
- Extrair modulo de sinistros ou atendimento como MFE.
- Criar pacote de tipos compartilhados.
- Criar contrato de comunicacao entre shell e MFEs.

### Fase 6 - DevOps e maturidade

Entregas:

- Docker Compose.
- Banco relacional local.
- OpenAPI/Swagger.
- CI com build e testes.
- Analise estatica.
- ADRs de decisoes tecnicas.

## Definicao de pronto

Uma demanda so deve ser considerada pronta quando:

- O codigo compila.
- Testes relevantes foram criados ou atualizados.
- A regra de negocio esta coberta por teste unitario.
- APIs alteradas estao documentadas.
- O PR tem descricao clara.
- O commit segue Conventional Commits.
- Nao ha refatoracoes fora do escopo sem justificativa.
