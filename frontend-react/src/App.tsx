import { AlertTriangle, BadgeDollarSign, FilePlus2, ShieldCheck } from 'lucide-react';
import { FormEvent, useEffect, useMemo, useState } from 'react';
import { useInsuranceStore } from './store/useInsuranceStore';
import './styles.css';

const currency = new Intl.NumberFormat('pt-BR', {
  style: 'currency',
  currency: 'BRL'
});

const statusLabel = {
  ACTIVE: 'Ativa',
  PENDING_RENEWAL: 'Renovacao',
  SUSPENDED: 'Suspensa',
  CANCELLED: 'Cancelada',
  OPEN: 'Aberto',
  UNDER_REVIEW: 'Analise',
  APPROVED: 'Aprovado',
  DENIED: 'Negado',
  PAID: 'Pago'
};

function App() {
  const { summary, policies, claims, selectedPolicyId, loading, error, load, selectPolicy, openClaim } =
    useInsuranceStore();
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState('2500');

  useEffect(() => {
    void load();
  }, [load]);

  const selectedPolicy = useMemo(
    () => policies.find((policy) => policy.id === selectedPolicyId),
    [policies, selectedPolicyId]
  );

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();

    if (!selectedPolicy) {
      return;
    }

    await openClaim({
      policyId: selectedPolicy.id,
      description,
      amount: Number(amount)
    });
    setDescription('');
    setAmount('2500');
  }

  return (
    <main className="app-shell">
      <aside className="sidebar">
        <div className="brand">
          <ShieldCheck size={28} />
          <span>SecurePolicy</span>
        </div>
        <nav>
          <button className="nav-button active" type="button">
            Operacao
          </button>
          <button className="nav-button" type="button">
            Sinistros
          </button>
          <button className="nav-button" type="button">
            Modernizacao
          </button>
        </nav>
      </aside>

      <section className="content">
        <header className="topbar">
          <div>
            <p className="eyebrow">Seguradora | Microsservico de apolices</p>
            <h1>Monitor de apolices e sinistros</h1>
          </div>
          <span className="api-pill">{loading ? 'Sincronizando' : 'API Spring Boot online'}</span>
        </header>

        {error && <div className="alert">{error}</div>}

        <section className="metrics" aria-label="Indicadores operacionais">
          <Metric icon={<ShieldCheck />} label="Apolices" value={summary?.activePolicies ?? 0} />
          <Metric icon={<FilePlus2 />} label="Sinistros abertos" value={summary?.openClaims ?? 0} />
          <Metric icon={<AlertTriangle />} label="Alto risco" value={summary?.highRiskPolicies ?? 0} />
          <Metric
            icon={<BadgeDollarSign />}
            label="Premio mensal"
            value={currency.format(summary?.monthlyPremium ?? 0)}
          />
        </section>

        <section className="workspace">
          <div className="panel policy-panel">
            <div className="panel-heading">
              <h2>Apolices priorizadas</h2>
              <span>{policies.length} registros</span>
            </div>
            <div className="table">
              {policies.map((policy) => (
                <button
                  className={`policy-row ${policy.id === selectedPolicyId ? 'selected' : ''}`}
                  key={policy.id}
                  onClick={() => selectPolicy(policy.id)}
                  type="button"
                >
                  <span>
                    <strong>{policy.number}</strong>
                    <small>{policy.customerName}</small>
                  </span>
                  <span>{policy.product}</span>
                  <span className="risk">Risco {policy.riskScore}</span>
                  <span className="status">{statusLabel[policy.status]}</span>
                </button>
              ))}
            </div>
          </div>

          <div className="panel">
            <div className="panel-heading">
              <h2>Novo sinistro</h2>
              <span>{selectedPolicy?.number ?? 'Selecione'}</span>
            </div>
            <form className="claim-form" onSubmit={handleSubmit}>
              <label>
                Descricao
                <textarea
                  value={description}
                  onChange={(event) => setDescription(event.target.value)}
                  placeholder="Ex.: Vistoria digital apontou dano parcial"
                  required
                />
              </label>
              <label>
                Valor estimado
                <input
                  min="1"
                  step="0.01"
                  type="number"
                  value={amount}
                  onChange={(event) => setAmount(event.target.value)}
                  required
                />
              </label>
              <button className="primary-action" disabled={!selectedPolicy} type="submit">
                Abrir sinistro
              </button>
            </form>
          </div>
        </section>

        <section className="panel">
          <div className="panel-heading">
            <h2>Fila de sinistros</h2>
            <span>{currency.format(summary?.claimedAmount ?? 0)} em analise</span>
          </div>
          <div className="claims-grid">
            {claims.map((claim) => (
              <article className="claim-card" key={claim.id}>
                <div>
                  <strong>{claim.protocol}</strong>
                  <span>{statusLabel[claim.status]}</span>
                </div>
                <p>{claim.description}</p>
                <footer>
                  <span>{claim.customerName}</span>
                  <strong>{currency.format(claim.amount)}</strong>
                </footer>
              </article>
            ))}
          </div>
        </section>

        <section className="panel mfe-panel">
          <div className="panel-heading">
            <h2>Micro-frontend Angular</h2>
            <span>http://localhost:4200</span>
          </div>
          <iframe
            title="Atendimento Angular"
            src="http://localhost:4200"
            loading="lazy"
            sandbox="allow-scripts allow-same-origin allow-forms"
          />
        </section>
      </section>
    </main>
  );
}

function Metric({ icon, label, value }: { icon: JSX.Element; label: string; value: string | number }) {
  return (
    <article className="metric-card">
      <span className="metric-icon">{icon}</span>
      <small>{label}</small>
      <strong>{value}</strong>
    </article>
  );
}

export default App;
