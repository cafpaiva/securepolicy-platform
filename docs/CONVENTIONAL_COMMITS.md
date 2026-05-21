# Conventional Commits

O projeto deve usar commits padronizados para facilitar historico, changelog e rastreabilidade com issues.

## Formato

```text
<tipo>(escopo): <descricao curta>
```

Com issue:

```text
<tipo>(escopo): <descricao curta> refs #<numero>
```

## Tipos

- `feat`: nova funcionalidade.
- `fix`: correcao de bug.
- `refactor`: refatoracao sem alterar comportamento.
- `test`: criacao ou ajuste de testes.
- `docs`: documentacao.
- `chore`: tarefas de manutencao.
- `build`: build, dependencias ou empacotamento.
- `ci`: pipeline de CI/CD.

## Exemplos

```text
docs(project): add roadmap and sprint plan
feat(claims): add open claim use case refs #12
test(backend): cover open claim business rules refs #13
refactor(backend): isolate persistence adapters refs #10
ci(project): add backend and frontend validation pipeline refs #31
```

## Regras

- Use imperativo em ingles ou portugues, mas mantenha consistencia.
- Prefira commits pequenos e coesos.
- Um commit deve representar uma intencao clara.
- Evite misturar refatoracao, feature e testes sem necessidade.
