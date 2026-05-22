# Deploy no Azure Kubernetes Service

Este projeto possui uma pipeline GitHub Actions para gerar imagens Docker, publicar no Azure Container Registry e fazer deploy no Azure Kubernetes Service.

## Secrets obrigatorios

Configure estes secrets no GitHub em `Settings > Secrets and variables > Actions`:

- `AZURE_CREDENTIALS`: JSON de um service principal com acesso ao resource group, ACR e AKS.
- `AZURE_RESOURCE_GROUP`: nome do resource group onde esta o AKS.
- `AKS_CLUSTER_NAME`: nome do cluster AKS.
- `ACR_NAME`: nome do Azure Container Registry, sem `.azurecr.io`.
- `ACR_LOGIN_SERVER`: login server do ACR, por exemplo `meuregistry.azurecr.io`.

Se voce criar a infraestrutura pela pipeline Terraform, use os outputs documentados em `docs/AZURE_TERRAFORM_INFRA.md` para preencher esses valores.

## Variavel opcional

Configure em `Settings > Secrets and variables > Actions > Variables`:

- `ANGULAR_MFE_URL`: URL publica do micro-frontend Angular usada no iframe do React.

Se ela nao for configurada, o build do React usara `/mfe/`. O Nginx do frontend React encaminha esse caminho para o servico Kubernetes `securepolicy-angular-mfe`.

## Como executar

A pipeline `.github/workflows/deploy-aks.yml` roda automaticamente em push para `main` e tambem pode ser acionada manualmente por `workflow_dispatch`.

Antes do primeiro deploy, garanta que o AKS tenha permissao de pull no ACR, por exemplo:

```bash
az aks update --resource-group <resource-group> --name <aks-name> --attach-acr <acr-name>
```

O deploy cria o namespace `securepolicy` e aplica os manifests de `k8s/`:

- `securepolicy-api`: backend Spring Boot exposto internamente via `ClusterIP`.
- `securepolicy-react`: frontend React exposto por `LoadBalancer`.
- `securepolicy-angular-mfe`: micro-frontend Angular exposto internamente via `ClusterIP` e acessado pelo React em `/mfe/`.

Depois do deploy, consulte os IPs publicos:

```bash
kubectl get services -n securepolicy
```
