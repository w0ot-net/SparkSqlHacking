package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.admissionregistration.v1.MutatingWebhookConfiguration;
import io.fabric8.kubernetes.api.model.admissionregistration.v1.MutatingWebhookConfigurationList;
import io.fabric8.kubernetes.api.model.admissionregistration.v1.ValidatingAdmissionPolicy;
import io.fabric8.kubernetes.api.model.admissionregistration.v1.ValidatingAdmissionPolicyBinding;
import io.fabric8.kubernetes.api.model.admissionregistration.v1.ValidatingAdmissionPolicyBindingList;
import io.fabric8.kubernetes.api.model.admissionregistration.v1.ValidatingAdmissionPolicyList;
import io.fabric8.kubernetes.api.model.admissionregistration.v1.ValidatingWebhookConfiguration;
import io.fabric8.kubernetes.api.model.admissionregistration.v1.ValidatingWebhookConfigurationList;
import io.fabric8.kubernetes.client.V1AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1AdmissionRegistrationAPIGroupClient extends ClientAdapter implements V1AdmissionRegistrationAPIGroupDSL {
   public NonNamespaceOperation validatingWebhookConfigurations() {
      return this.resources(ValidatingWebhookConfiguration.class, ValidatingWebhookConfigurationList.class);
   }

   public MixedOperation mutatingWebhookConfigurations() {
      return this.resources(MutatingWebhookConfiguration.class, MutatingWebhookConfigurationList.class);
   }

   public NonNamespaceOperation validatingAdmissionPolicyBindings() {
      return this.resources(ValidatingAdmissionPolicyBinding.class, ValidatingAdmissionPolicyBindingList.class);
   }

   public NonNamespaceOperation validatingAdmissionPolicies() {
      return this.resources(ValidatingAdmissionPolicy.class, ValidatingAdmissionPolicyList.class);
   }

   public V1AdmissionRegistrationAPIGroupClient newInstance() {
      return new V1AdmissionRegistrationAPIGroupClient();
   }
}
