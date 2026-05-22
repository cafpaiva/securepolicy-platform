variable "project_name" {
  description = "Nome base usado nos recursos Azure."
  type        = string
  default     = "securepolicy"
}

variable "environment" {
  description = "Ambiente implantado."
  type        = string
  default     = "dev"
}

variable "location" {
  description = "Regiao Azure."
  type        = string
  default     = "brazilsouth"
}

variable "aks_node_count" {
  description = "Quantidade inicial de nos do AKS."
  type        = number
  default     = 2
}

variable "aks_min_count" {
  description = "Minimo de nos quando autoscaling estiver ativo."
  type        = number
  default     = 2
}

variable "aks_max_count" {
  description = "Maximo de nos quando autoscaling estiver ativo."
  type        = number
  default     = 4
}

variable "aks_vm_size" {
  description = "SKU das VMs do node pool principal do AKS."
  type        = string
  default     = "Standard_B2s"
}

variable "kubernetes_version" {
  description = "Versao do Kubernetes. Use null para deixar a Azure selecionar a versao padrao suportada."
  type        = string
  default     = null
}

variable "tags" {
  description = "Tags comuns aplicadas aos recursos."
  type        = map(string)
  default = {
    workload = "securepolicy"
    managed  = "terraform"
  }
}
