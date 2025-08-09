package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.V1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2beta1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2beta2AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class AutoscalingAPIGroupClient extends ClientAdapter implements AutoscalingAPIGroupDSL {
   public V2AutoscalingAPIGroupDSL v2() {
      return (V2AutoscalingAPIGroupDSL)this.adapt(V2AutoscalingAPIGroupClient.class);
   }

   public V1AutoscalingAPIGroupDSL v1() {
      return (V1AutoscalingAPIGroupDSL)this.adapt(V1AutoscalingAPIGroupClient.class);
   }

   public V2beta1AutoscalingAPIGroupDSL v2beta1() {
      return (V2beta1AutoscalingAPIGroupDSL)this.adapt(V2beta1AutoscalingAPIGroupClient.class);
   }

   public V2beta2AutoscalingAPIGroupDSL v2beta2() {
      return (V2beta2AutoscalingAPIGroupDSL)this.adapt(V2beta2AutoscalingAPIGroupClient.class);
   }

   public AutoscalingAPIGroupClient newInstance() {
      return new AutoscalingAPIGroupClient();
   }
}
