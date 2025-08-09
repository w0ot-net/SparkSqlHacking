package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;

public interface V1beta1AdmissionRegistrationAPIGroupDSL extends Client {
   MixedOperation validatingWebhookConfigurations();

   NonNamespaceOperation mutatingWebhookConfigurations();

   NonNamespaceOperation validatingAdmissionPolicies();

   NonNamespaceOperation validatingAdmissionPolicyBindings();
}
