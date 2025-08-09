package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.V1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2beta1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2beta2AutoscalingAPIGroupDSL;

public interface AutoscalingAPIGroupDSL extends Client {
   V2AutoscalingAPIGroupDSL v2();

   V1AutoscalingAPIGroupDSL v1();

   V2beta1AutoscalingAPIGroupDSL v2beta1();

   V2beta2AutoscalingAPIGroupDSL v2beta2();
}
