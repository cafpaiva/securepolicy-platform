$tools = "C:\tmp\codex-tools"
$node = Join-Path $tools "node-v24.16.0-win-x64"
$gh = Join-Path $tools "bin"

$env:PATH = "$gh;$node;$env:PATH"

Write-Host "Portable tools enabled for this PowerShell session."
Write-Host "Node:" (node -v)
Write-Host "NPM:" (npm -v)
Write-Host "GitHub CLI:" (gh --version | Select-Object -First 1)
