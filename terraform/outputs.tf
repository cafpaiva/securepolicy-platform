output "resource_group_name" {
  description = "Resource group criado para a aplicacao."
  value       = azurerm_resource_group.main.name
}

output "aks_cluster_name" {
  description = "Nome do cluster AKS."
  value       = azurerm_kubernetes_cluster.main.name
}

output "acr_name" {
  description = "Nome do Azure Container Registry."
  value       = azurerm_container_registry.main.name
}

output "acr_login_server" {
  description = "Login server do Azure Container Registry."
  value       = azurerm_container_registry.main.login_server
}

output "log_analytics_workspace_name" {
  description = "Workspace usado pelo Azure Monitor para containers."
  value       = azurerm_log_analytics_workspace.main.name
}
