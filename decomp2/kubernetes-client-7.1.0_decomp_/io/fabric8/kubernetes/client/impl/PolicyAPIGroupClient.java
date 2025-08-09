package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.policy.v1beta1.PodDisruptionBudget;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodDisruptionBudgetList;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodSecurityPolicy;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodSecurityPolicyList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class PolicyAPIGroupClient extends ClientAdapter implements PolicyAPIGroupDSL {
   public MixedOperation podDisruptionBudget() {
      return this.resources(PodDisruptionBudget.class, PodDisruptionBudgetList.class);
   }

   public MixedOperation podSecurityPolicies() {
      return this.resources(PodSecurityPolicy.class, PodSecurityPolicyList.class);
   }

   public V1PolicyAPIGroupDSL v1() {
      return (V1PolicyAPIGroupDSL)this.adapt(V1PolicyAPIGroupClient.class);
   }

   public V1beta1PolicyAPIGroupDSL v1beta1() {
      return (V1beta1PolicyAPIGroupDSL)this.adapt(V1beta1PolicyAPIGroupClient.class);
   }

   public PolicyAPIGroupClient newInstance() {
      return new PolicyAPIGroupClient();
   }
}
