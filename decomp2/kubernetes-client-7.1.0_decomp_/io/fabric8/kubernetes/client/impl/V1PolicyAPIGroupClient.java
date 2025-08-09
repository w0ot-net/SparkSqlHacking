package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.policy.v1.PodDisruptionBudget;
import io.fabric8.kubernetes.api.model.policy.v1.PodDisruptionBudgetList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1PolicyAPIGroupClient extends ClientAdapter implements V1PolicyAPIGroupDSL {
   public MixedOperation podDisruptionBudget() {
      return this.resources(PodDisruptionBudget.class, PodDisruptionBudgetList.class);
   }

   public V1PolicyAPIGroupClient newInstance() {
      return new V1PolicyAPIGroupClient();
   }
}
