export type PolicyStatus = 'ACTIVE' | 'PENDING_RENEWAL' | 'SUSPENDED' | 'CANCELLED';
export type ClaimStatus = 'OPEN' | 'UNDER_REVIEW' | 'APPROVED' | 'DENIED' | 'PAID';

export interface Policy {
  id: number;
  number: string;
  customerName: string;
  product: string;
  status: PolicyStatus;
  premium: number;
  coverageAmount: number;
  startDate: string;
  endDate: string;
  riskScore: number;
  claimsCount: number;
}

export interface Claim {
  id: number;
  protocol: string;
  description: string;
  amount: number;
  status: ClaimStatus;
  openedAt: string;
  policyNumber: string;
  customerName: string;
}

export interface DashboardSummary {
  activePolicies: number;
  openClaims: number;
  highRiskPolicies: number;
  monthlyPremium: number;
  claimedAmount: number;
}
