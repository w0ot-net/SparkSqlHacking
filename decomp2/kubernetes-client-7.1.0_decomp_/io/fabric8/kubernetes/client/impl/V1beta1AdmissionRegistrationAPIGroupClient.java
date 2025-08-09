package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.MutatingWebhookConfiguration;
import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.MutatingWebhookConfigurationList;
import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.ValidatingAdmissionPolicy;
import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.ValidatingAdmissionPolicyBinding;
import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.ValidatingAdmissionPolicyBindingList;
import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.ValidatingAdmissionPolicyList;
import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.ValidatingWebhookConfiguration;
import io.fabric8.kubernetes.api.model.admissionregistration.v1beta1.ValidatingWebhookConfigurationList;
import io.fabric8.kubernetes.client.V1beta1AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1AdmissionRegistrationAPIGroupClient extends ClientAdapter implements V1beta1AdmissionRegistrationAPIGroupDSL {
   public MixedOperation validatingWebhookConfigurations() {
      return this.resources(ValidatingWebhookConfiguration.class, ValidatingWebhookConfigurationList.class);
   }

   public NonNamespaceOperation mutatingWebhookConfigurations() {
      return this.resources(MutatingWebhookConfiguration.class, MutatingWebhookConfigurationList.class);
   }

   public NonNamespaceOperation validatingAdmissionPolicies() {
      return this.resources(ValidatingAdmissionPolicy.class, ValidatingAdmissionPolicyList.class);
   }

   public NonNamespaceOperation validatingAdmissionPolicyBindings() {
      return this.resources(ValidatingAdmissionPolicyBinding.class, ValidatingAdmissionPolicyBindingList.class);
   }

   public V1beta1AdmissionRegistrationAPIGroupClient newInstance() {
      return new V1beta1AdmissionRegistrationAPIGroupClient();
   }
}
