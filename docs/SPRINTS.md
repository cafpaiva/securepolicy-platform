# Plano de Sprints

Cada sprint foi pensada para gerar um incremento demonstravel. A duracao sugerida e de 1 semana, mas podemos adaptar para estudo, portfolio ou simulacao de squad.

## Sprint 0 - Setup profissional do projeto

Objetivo: preparar o projeto para trabalho versionado e organizado.

Issues sugeridas:

- Criar roadmap tecnico e de produto.
- Criar templates de issue e pull request.
- Documentar GitFlow.
- Documentar Conventional Commits.
- Inicializar repositorio Git.

Entregavel:

- Projeto pronto para receber demandas rastreaveis.

## Sprint 1 - Clean Architecture backend

Objetivo: refatorar o backend para arquitetura limpa.

Issues sugeridas:

- Criar camada de dominio.
- Criar use case `ListPoliciesUseCase`.
- Criar use case `OpenClaimUseCase`.
- Criar portas de persistencia.
- Criar adapters JPA.
- Reorganizar controllers REST.

Entregavel:

- Backend com separacao clara entre dominio, aplicacao e infraestrutura.

## Sprint 2 - Testes backend

Objetivo: criar base de qualidade automatizada no backend.

Issues sugeridas:

- Adicionar testes unitarios para `OpenClaimUseCase`.
- Adicionar testes unitarios para `ListPoliciesUseCase`.
- Adicionar testes de controller com MockMvc.
- Adicionar testes de repository com H2.
- Adicionar testes BDD para abertura de sinistro.

Entregavel:

- Fluxos principais cobertos por testes automatizados.

## Sprint 3 - React por features

Objetivo: tornar o frontend escalavel e facil de manter.

Issues sugeridas:

- Reorganizar pastas por features.
- Criar `api-client` tipado.
- Criar componentes compartilhados.
- Criar formulario de sinistro com validacao.
- Melhorar estados de loading, erro e vazio.

Entregavel:

- Frontend com arquitetura profissional e componentes reutilizaveis.

## Sprint 4 - Testes frontend

Objetivo: garantir confiabilidade do frontend.

Issues sugeridas:

- Configurar Vitest.
- Configurar React Testing Library.
- Testar cards de metricas.
- Testar listagem de apolices.
- Testar abertura de sinistro.
- Testar store Zustand.

Entregavel:

- Frontend com testes unitarios e de integracao.

## Sprint 5 - Micro-frontends

Objetivo: evoluir o MFE demonstrativo para composicao real.

Issues sugeridas:

- Definir estrategia oficial de MFE.
- Criar shell React.
- Expor modulo Angular de atendimento.
- Criar pacote de tipos compartilhados.
- Criar contrato de eventos entre shell e MFE.

Entregavel:

- Micro-frontend integrado de forma controlada e documentada.

## Sprint 6 - CI, Docker e documentacao

Objetivo: preparar o projeto para ambiente profissional.

Issues sugeridas:

- Criar Docker Compose.
- Adicionar Swagger/OpenAPI.
- Criar pipeline de CI.
- Adicionar checkstyle/spotless.
- Criar ADRs.

Entregavel:

- Projeto com automacao minima de build, teste e documentacao.
