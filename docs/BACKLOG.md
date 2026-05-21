# Backlog Inicial

Este backlog pode ser convertido em issues no GitHub. Os codigos ajudam a planejar sprints antes de os numeros reais das issues existirem.

## Sprint 0

### SP-001 - Documentar roadmap do projeto

Labels: `type:docs`, `sprint:0`, `priority:high`

Descricao:

Criar documentacao com roadmap tecnico e de produto para evolucao da aplicacao SecurePolicy.

Criterios de aceite:

- Roadmap possui fases.
- Cada fase possui objetivo e entregas.
- Existe definicao de pronto.

### SP-002 - Documentar GitFlow e Conventional Commits

Labels: `type:docs`, `sprint:0`, `priority:high`

Descricao:

Documentar estrategia de branches, fluxo de PR e padrao de commits.

Criterios de aceite:

- Branches principais estao descritas.
- Branches de trabalho estao descritas.
- Exemplos de commits foram incluidos.

### SP-003 - Criar templates do GitHub

Labels: `type:chore`, `sprint:0`, `priority:medium`

Descricao:

Criar templates para issues e pull requests.

Criterios de aceite:

- Template de feature existe.
- Template de bug existe.
- Template de divida tecnica existe.
- Template de pull request existe.

## Sprint 1

### SP-101 - Refatorar backend para Clean Architecture

Labels: `type:refactor`, `backend`, `architecture`, `sprint:1`, `priority:high`

Descricao:

Reorganizar o backend em dominio, aplicacao e infraestrutura, preservando o comportamento atual da API.

Criterios de aceite:

- Controllers nao contem regra de negocio.
- Use cases representam fluxos de aplicacao.
- JPA fica isolado na infraestrutura.
- O comportamento atual dos endpoints e preservado.

### SP-102 - Criar use case de listagem de apolices

Labels: `type:feature`, `backend`, `sprint:1`, `priority:medium`

Descricao:

Criar `ListPoliciesUseCase` para centralizar a regra de listagem de apolices.

Criterios de aceite:

- Use case recebe uma porta de consulta.
- Use case retorna DTO de saida.
- Controller chama o use case.

### SP-103 - Criar use case de abertura de sinistro

Labels: `type:feature`, `backend`, `claims`, `sprint:1`, `priority:high`

Descricao:

Criar `OpenClaimUseCase` com validacoes de negocio para abertura de sinistro.

Criterios de aceite:

- Valida existencia da apolice.
- Valida valor positivo.
- Cria sinistro com status inicial.
- Retorna protocolo gerado.

## Sprint 2

### SP-201 - Testar use case de abertura de sinistro

Labels: `type:test`, `backend`, `claims`, `sprint:2`, `priority:high`

Descricao:

Adicionar testes unitarios para as regras do use case de abertura de sinistro.

Criterios de aceite:

- Testa abertura com sucesso.
- Testa apolice inexistente.
- Testa valor invalido.

### SP-202 - Criar testes REST com MockMvc

Labels: `type:test`, `backend`, `api`, `sprint:2`, `priority:medium`

Descricao:

Cobrir endpoints principais com testes de contrato REST usando MockMvc.

Criterios de aceite:

- `GET /api/policies` testado.
- `GET /api/dashboard/summary` testado.
- `POST /api/claims` testado.

### SP-203 - Criar BDD de API para sinistros

Labels: `type:test`, `bdd`, `api`, `sprint:2`, `priority:medium`

Descricao:

Adicionar cenario BDD para abertura de sinistro.

Criterios de aceite:

- Feature Gherkin criada.
- Step definitions implementadas.
- Cenario executa no pipeline local.

## Sprint 3

### SP-301 - Reorganizar React por features

Labels: `type:refactor`, `frontend`, `react`, `sprint:3`, `priority:high`

Descricao:

Reorganizar frontend React por features para melhorar escalabilidade.

Criterios de aceite:

- `features/dashboard` criado.
- `features/policies` criado.
- `features/claims` criado.
- Componentes compartilhados ficam em `shared`.

### SP-302 - Criar API client tipado

Labels: `type:feature`, `frontend`, `react`, `sprint:3`, `priority:medium`

Descricao:

Criar camada de client HTTP tipada e reutilizavel.

Criterios de aceite:

- Requests ficam centralizados.
- Erros sao normalizados.
- Tipos de resposta sao exportados.

## Sprint 4

### SP-401 - Configurar Vitest e React Testing Library

Labels: `type:test`, `frontend`, `react`, `sprint:4`, `priority:high`

Descricao:

Configurar infraestrutura de testes unitarios do React.

Criterios de aceite:

- Vitest configurado.
- React Testing Library configurado.
- Script `npm test` criado.

### SP-402 - Testar abertura de sinistro no frontend

Labels: `type:test`, `frontend`, `claims`, `sprint:4`, `priority:medium`

Descricao:

Criar teste de integracao para preenchimento e envio do formulario de sinistro.

Criterios de aceite:

- Renderiza formulario.
- Preenche descricao e valor.
- Chama API de criacao.
- Atualiza tela apos sucesso.

## Sprint 5

### SP-501 - Definir arquitetura de micro-frontends

Labels: `type:architecture`, `frontend`, `microfrontend`, `sprint:5`, `priority:high`

Descricao:

Criar ADR comparando Module Federation, Single-SPA e Web Components para este projeto.

Criterios de aceite:

- Alternativas avaliadas.
- Decisao registrada.
- Trade-offs documentados.

### SP-502 - Integrar MFE Angular ao shell React

Labels: `type:feature`, `frontend`, `microfrontend`, `sprint:5`, `priority:high`

Descricao:

Substituir integracao via iframe por uma abordagem profissional de micro-frontend.

Criterios de aceite:

- Shell React carrega MFE Angular.
- Contrato de dados documentado.
- Falha de carregamento possui fallback visual.
