import type { Claim, DashboardSummary, Policy } from './types';

const jsonHeaders = { 'Content-Type': 'application/json' };

async function request<T>(url: string, options?: RequestInit): Promise<T> {
  const response = await fetch(url, options);

  if (!response.ok) {
    throw new Error(`Erro na API: ${response.status}`);
  }

  return response.json() as Promise<T>;
}

export const api = {
  getSummary: () => request<DashboardSummary>('/api/dashboard/summary'),
  getPolicies: () => request<Policy[]>('/api/policies'),
  getClaims: () => request<Claim[]>('/api/claims'),
  createClaim: (payload: { policyId: number; description: string; amount: number }) =>
    request<Claim>('/api/claims', {
      method: 'POST',
      headers: jsonHeaders,
      body: JSON.stringify(payload)
    })
};
