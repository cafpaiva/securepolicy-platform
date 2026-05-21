import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { AsyncPipe, CurrencyPipe, NgFor } from '@angular/common';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

interface Policy {
  id: number;
  number: string;
  customerName: string;
  product: string;
  premium: number;
  riskScore: number;
}

@Component({
  selector: 'sp-root',
  standalone: true,
  imports: [AsyncPipe, CurrencyPipe, NgFor],
  template: `
    <main class="mfe-shell">
      <header>
        <span>Angular Micro-frontend</span>
        <h1>Atendimento de apolices</h1>
      </header>

      <section class="policy-list">
        <article *ngFor="let policy of policies$ | async" class="policy-card">
          <strong>{{ policy.number }}</strong>
          <h2>{{ policy.customerName }}</h2>
          <p>{{ policy.product }}</p>
          <footer>
            <span>Risco {{ policy.riskScore }}</span>
            <b>{{ policy.premium | currency: 'BRL' }}</b>
          </footer>
        </article>
      </section>
    </main>
  `
})
class AppComponent {
  private readonly http = inject(HttpClient);
  readonly policies$: Observable<Policy[]> = this.http.get<Policy[]>('/api/policies');
}

bootstrapApplication(AppComponent, {
  providers: [provideHttpClient()]
}).catch((error) => console.error(error));
