package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.autoscaling.v2beta1.HorizontalPodAutoscaler;
import io.fabric8.kubernetes.api.model.autoscaling.v2beta1.HorizontalPodAutoscalerList;
import io.fabric8.kubernetes.client.V2beta1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V2beta1AutoscalingAPIGroupClient extends ClientAdapter implements V2beta1AutoscalingAPIGroupDSL {
   public MixedOperation horizontalPodAutoscalers() {
      return this.resources(HorizontalPodAutoscaler.class, HorizontalPodAutoscalerList.class);
   }

   public V2beta1AutoscalingAPIGroupClient newInstance() {
      return new V2beta1AutoscalingAPIGroupClient();
   }
}
