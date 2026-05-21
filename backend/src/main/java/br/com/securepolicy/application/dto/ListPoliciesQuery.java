package br.com.securepolicy.application.dto;

import br.com.securepolicy.domain.PolicyStatus;

import java.util.Optional;

public class ListPoliciesQuery {

    private final PolicyStatus status;
    private final String search;

    public ListPoliciesQuery(PolicyStatus status, String search) {
        this.status = status;
        this.search = normalize(search);
    }

    public static ListPoliciesQuery empty() {
        return new ListPoliciesQuery(null, null);
    }

    public Optional<PolicyStatus> getStatus() {
        return Optional.ofNullable(status);
    }

    public Optional<String> getSearch() {
        return Optional.ofNullable(search);
    }

    private String normalize(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        return value.trim().toLowerCase();
    }
}
