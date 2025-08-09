package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.autoscaling.v1.HorizontalPodAutoscaler;
import io.fabric8.kubernetes.api.model.autoscaling.v1.HorizontalPodAutoscalerList;
import io.fabric8.kubernetes.client.V1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1AutoscalingAPIGroupClient extends ClientAdapter implements V1AutoscalingAPIGroupDSL {
   public MixedOperation horizontalPodAutoscalers() {
      return this.resources(HorizontalPodAutoscaler.class, HorizontalPodAutoscalerList.class);
   }

   public V1AutoscalingAPIGroupClient newInstance() {
      return new V1AutoscalingAPIGroupClient();
   }
}
