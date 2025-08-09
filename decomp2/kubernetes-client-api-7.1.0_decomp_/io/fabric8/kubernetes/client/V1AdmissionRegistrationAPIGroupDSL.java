package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;

public interface V1AdmissionRegistrationAPIGroupDSL extends Client {
   NonNamespaceOperation validatingWebhookConfigurations();

   NonNamespaceOperation mutatingWebhookConfigurations();

   NonNamespaceOperation validatingAdmissionPolicyBindings();

   NonNamespaceOperation validatingAdmissionPolicies();
}
