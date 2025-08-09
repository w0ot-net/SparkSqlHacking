package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.autoscaling.v2beta2.HorizontalPodAutoscaler;
import io.fabric8.kubernetes.api.model.autoscaling.v2beta2.HorizontalPodAutoscalerList;
import io.fabric8.kubernetes.client.V2beta2AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V2beta2AutoscalingAPIGroupClient extends ClientAdapter implements V2beta2AutoscalingAPIGroupDSL {
   public MixedOperation horizontalPodAutoscalers() {
      return this.resources(HorizontalPodAutoscaler.class, HorizontalPodAutoscalerList.class);
   }

   public V2beta2AutoscalingAPIGroupClient newInstance() {
      return new V2beta2AutoscalingAPIGroupClient();
   }
}
