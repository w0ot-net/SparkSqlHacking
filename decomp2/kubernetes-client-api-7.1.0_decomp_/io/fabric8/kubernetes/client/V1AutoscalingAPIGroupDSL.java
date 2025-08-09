package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.MixedOperation;

public interface V1AutoscalingAPIGroupDSL extends Client {
   MixedOperation horizontalPodAutoscalers();
}
