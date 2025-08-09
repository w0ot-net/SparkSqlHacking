package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.policy.v1beta1.PodDisruptionBudget;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodDisruptionBudgetList;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodSecurityPolicy;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodSecurityPolicyList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1PolicyAPIGroupClient extends ClientAdapter implements V1beta1PolicyAPIGroupDSL {
   public MixedOperation podSecurityPolicies() {
      return this.resources(PodSecurityPolicy.class, PodSecurityPolicyList.class);
   }

   public MixedOperation podDisruptionBudget() {
      return this.resources(PodDisruptionBudget.class, PodDisruptionBudgetList.class);
   }

   public V1beta1PolicyAPIGroupClient newInstance() {
      return new V1beta1PolicyAPIGroupClient();
   }
}
