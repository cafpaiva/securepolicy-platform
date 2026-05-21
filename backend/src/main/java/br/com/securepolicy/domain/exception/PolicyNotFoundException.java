package br.com.securepolicy.domain.exception;

public class PolicyNotFoundException extends RuntimeException {

    public PolicyNotFoundException(Long policyId) {
        super("Apolice nao encontrada: " + policyId);
    }
}
