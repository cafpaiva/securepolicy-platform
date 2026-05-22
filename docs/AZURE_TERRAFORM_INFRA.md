# Infraestrutura Azure com Terraform

Este repositorio possui uma pipeline isolada para criar a infraestrutura base da aplicacao no Azure Kubernetes Service.

## O que o Terraform cria

- Resource Group da aplicacao.
- Azure Container Registry privado para as imagens Docker.
- Virtual Network e Subnet dedicada ao AKS.
- AKS com autoscaling no node pool principal.
- Log Analytics Workspace para observabilidade do AKS.
- Permissao `AcrPull` para o AKS baixar imagens do ACR.

## Pipeline

A pipeline fica em `.github/workflows/infra-terraform.yml` e roda apenas manualmente por `workflow_dispatch`.

Ela possui tres acoes:

- `plan`: mostra o que sera criado, alterado ou removido.
- `apply`: cria ou atualiza a infraestrutura.
- `destroy`: remove a infraestrutura do ambiente selecionado.

## Secrets obrigatorios

Configure estes secrets em `Settings > Secrets and variables > Actions > Secrets`:

- `AZURE_CLIENT_ID`: client id do app/service principal usado pelo GitHub OIDC.
- `AZURE_TENANT_ID`: tenant id da Azure.
- `AZURE_SUBSCRIPTION_ID`: subscription id da Azure.

O app/service principal precisa ter permissao para criar recursos na subscription ou no resource group alvo. Para o primeiro provisionamento, normalmente use `Contributor` e `User Access Administrator`, porque o Terraform cria um role assignment `AcrPull` para o AKS.

## Variables obrigatorias

Configure estas variables em `Settings > Secrets and variables > Actions > Variables`:

- `TF_STATE_RESOURCE_GROUP`: resource group do backend remoto do Terraform, por exemplo `rg-securepolicy-tfstate`.
- `TF_STATE_STORAGE_ACCOUNT`: storage account globalmente unico para o state, por exemplo `stsecurepolicytfstate001`.
- `TF_STATE_CONTAINER`: container do state, por exemplo `tfstate`.

Variavel opcional:

- `AZURE_LOCATION`: regiao Azure. Default da pipeline: `brazilsouth`.

## Backend remoto do Terraform

A propria pipeline faz o bootstrap do backend remoto antes do `terraform init`:

- cria o resource group do state se nao existir;
- cria o storage account se nao existir;
- cria o container do state se nao existir.

Cada ambiente usa um arquivo de state separado:

```text
securepolicy-dev.tfstate
securepolicy-hml.tfstate
securepolicy-prd.tfstate
```

## Depois do apply

Ao final do `apply`, copie os outputs para os secrets da pipeline de deploy da aplicacao:

- `resource_group_name` -> `AZURE_RESOURCE_GROUP`
- `aks_cluster_name` -> `AKS_CLUSTER_NAME`
- `acr_name` -> `ACR_NAME`
- `acr_login_server` -> `ACR_LOGIN_SERVER`

O secret `AZURE_CREDENTIALS` usado na pipeline de deploy continua sendo necessario para autenticar no Azure durante o deploy da aplicacao.

## Diagrama

O desenho da arquitetura esta em:

- `docs/diagrams/azure-infra.drawio`

Abra esse arquivo no diagrams.net/draw.io para visualizar ou editar.
