# GitFlow

Este projeto usa GitFlow simplificado, adequado para portfolio profissional e projetos reais.

## Branches principais

- `main`: codigo estavel, pronto para release.
- `develop`: integracao das features aprovadas.

## Branches de trabalho

- `feature/<issue>-<descricao>`: novas funcionalidades.
- `bugfix/<issue>-<descricao>`: correcao de bugs durante desenvolvimento.
- `hotfix/<issue>-<descricao>`: correcao urgente a partir da `main`.
- `release/<versao>`: estabilizacao antes de publicar.

Exemplos:

```bash
git checkout -b develop
git checkout -b feature/001-clean-architecture-backend develop
git checkout -b feature/002-open-claim-usecase develop
```

## Fluxo recomendado

1. Criar issue no GitHub.
2. Criar branch a partir de `develop`.
3. Implementar em commits pequenos.
4. Rodar testes.
5. Abrir Pull Request para `develop`.
6. Fazer review.
7. Fazer merge.
8. Ao fechar uma versao, criar `release/x.y.z`.
9. Fazer merge em `main` e criar tag.

## Protecoes recomendadas no GitHub

- Bloquear push direto em `main`.
- Bloquear push direto em `develop`.
- Exigir PR.
- Exigir build verde.
- Exigir pelo menos 1 review.
- Exigir branch atualizada antes do merge.
