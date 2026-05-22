# SecurePolicy Full Stack

![CI](https://github.com/cafpaiva/securepolicy-platform/actions/workflows/ci.yml/badge.svg)
![CodeQL](https://github.com/cafpaiva/securepolicy-platform/actions/workflows/codeql.yml/badge.svg)

Aplicacao demonstrativa para uma oportunidade senior Java / React em seguradora. O projeto simula um portal interno de apolices e sinistros com backend Spring Boot, persistencia JPA e frontends modernos consumindo APIs REST.

## Stack

- Java 11, Spring Boot 2.7, Spring Web, Spring Data JPA, Hibernate
- API RESTful com DTOs e camada de servico
- H2 em memoria para desenvolvimento local
- Perfis preparados para MySQL e Oracle
- React 18, TypeScript, Vite, Zustand e Context API
- Angular 17 como micro-frontend demonstrativo opcional
- CSS3 responsivo sem dependencias visuais pesadas

## Estrutura

```text
backend/              API de seguros em Spring Boot
frontend-react/       Dashboard principal em React + Zustand
frontend-angular-mfe/ Micro-frontend Angular opcional para atendimento
docs/                 Roadmap, sprints, GitFlow e padroes de engenharia
```

## Planejamento e engenharia

- [Roadmap](docs/ROADMAP.md)
- [Plano de sprints](docs/SPRINTS.md)
- [Backlog inicial](docs/BACKLOG.md)
- [GitFlow](docs/GITFLOW.md)
- [Conventional Commits](docs/CONVENTIONAL_COMMITS.md)
- [GitHub profissional](docs/GITHUB_PROFESSIONAL_SETUP.md)
- [Infraestrutura Azure com Terraform](docs/AZURE_TERRAFORM_INFRA.md)
- [Deploy Kubernetes no AKS](docs/AZURE_AKS_DEPLOY.md)

## Ferramentas portateis

Se Node/NPM/GitHub CLI nao estiverem no PATH da maquina, habilite as ferramentas portateis nesta sessao:

```powershell
.\scripts\use-dev-tools.ps1
```

## Como rodar o backend

```bash
cd backend
mvn spring-boot:run
```

A API sobe em `http://localhost:8080/api`.

Endpoints principais:

- `GET /api/policies`
- `GET /api/policies/{id}`
- `GET /api/claims`
- `POST /api/claims`
- `GET /api/dashboard/summary`

Console H2:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:securepolicy`
- User: `sa`
- Password: vazio

## Como rodar o React

```bash
cd frontend-react
npm install
npm run dev
```

O Vite abre em `http://localhost:5173` e faz proxy para `http://localhost:8080`.

## Como rodar o Angular MFE

```bash
cd frontend-angular-mfe
npm install
npm start
```

O micro-frontend abre em `http://localhost:4200`.

## Perfis de banco

O backend roda com H2 por padrao. Para bancos reais, configure variaveis de ambiente e ative o perfil:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
mvn spring-boot:run -Dspring-boot.run.profiles=oracle
```

As chaves esperadas estao em `backend/src/main/resources/application.yml`.
