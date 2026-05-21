# GitHub profissional

Este documento lista os elementos que deixam o repositorio mais apresentavel para recrutadores, tech leads e avaliadores tecnicos.

## Ja preparado no projeto

- Roadmap tecnico.
- Plano de sprints.
- Backlog inicial.
- Templates de issue.
- Template de Pull Request.
- GitFlow documentado.
- Conventional Commits documentado.
- CODEOWNERS.
- Dependabot.
- CI com backend, React e Angular MFE.
- CodeQL para analise de seguranca.
- EditorConfig e Git attributes.

## Configuracoes recomendadas no GitHub

Depois de publicar o repositorio:

1. Definir descricao do repositorio:
   `Full stack insurance platform with Java, Spring Boot, React, Angular MFE, Clean Architecture and automated quality gates.`
2. Adicionar topicos:
   `java`, `spring-boot`, `react`, `typescript`, `angular`, `microfrontend`, `clean-architecture`, `solid`, `junit`, `bdd`, `github-actions`
3. Criar branch `develop`.
4. Proteger `main` e `develop`.
5. Exigir Pull Request.
6. Exigir status checks da CI.
7. Exigir pelo menos 1 review.
8. Ativar Dependabot alerts.
9. Ativar CodeQL.
10. Criar milestones por sprint.

## Branch protection sugerida

Para `main`:

- Require a pull request before merging.
- Require approvals: 1.
- Dismiss stale pull request approvals.
- Require status checks to pass.
- Require branches to be up to date.
- Include administrators, se quiser simular governanca forte.

Para `develop`:

- Require a pull request before merging.
- Require status checks to pass.
- Require conversation resolution.

## Issues iniciais

Use o backlog em `docs/BACKLOG.md` para criar as primeiras issues. A ordem sugerida:

1. SP-101 - Refatorar backend para Clean Architecture.
2. SP-102 - Criar use case de listagem de apolices.
3. SP-103 - Criar use case de abertura de sinistro.
4. SP-201 - Testar use case de abertura de sinistro.
5. SP-301 - Reorganizar React por features.
6. SP-501 - Definir arquitetura de micro-frontends.
