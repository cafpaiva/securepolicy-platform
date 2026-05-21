import { create } from 'zustand';
import { api } from '../api';
import type { Claim, DashboardSummary, Policy } from '../types';

interface InsuranceState {
  summary?: DashboardSummary;
  policies: Policy[];
  claims: Claim[];
  selectedPolicyId?: number;
  loading: boolean;
  error?: string;
  load: () => Promise<void>;
  selectPolicy: (policyId: number) => void;
  openClaim: (payload: { policyId: number; description: string; amount: number }) => Promise<void>;
}

export const useInsuranceStore = create<InsuranceState>((set, get) => ({
  policies: [],
  claims: [],
  loading: false,
  async load() {
    set({ loading: true, error: undefined });

    try {
      const [summary, policies, claims] = await Promise.all([
        api.getSummary(),
        api.getPolicies(),
        api.getClaims()
      ]);

      set({
        summary,
        policies,
        claims,
        selectedPolicyId: get().selectedPolicyId ?? policies[0]?.id,
        loading: false
      });
    } catch (error) {
      set({ error: error instanceof Error ? error.message : 'Falha inesperada', loading: false });
    }
  },
  selectPolicy(policyId) {
    set({ selectedPolicyId: policyId });
  },
  async openClaim(payload) {
    await api.createClaim(payload);
    await get().load();
  }
}));
