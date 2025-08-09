package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.dsl.FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta2FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta3FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class FlowControlAPIGroupClient extends ClientAdapter implements FlowControlAPIGroupDSL {
   public V1FlowControlAPIGroupDSL v1() {
      return (V1FlowControlAPIGroupDSL)this.adapt(V1FlowControlAPIGroupClient.class);
   }

   public V1beta1FlowControlAPIGroupDSL v1beta1() {
      return (V1beta1FlowControlAPIGroupDSL)this.adapt(V1beta1FlowControlAPIGroupClient.class);
   }

   public V1beta2FlowControlAPIGroupDSL v1beta2() {
      return (V1beta2FlowControlAPIGroupDSL)this.adapt(V1beta2FlowControlAPIGroupClient.class);
   }

   public V1beta3FlowControlAPIGroupDSL v1beta3() {
      return (V1beta3FlowControlAPIGroupDSL)this.adapt(V1beta3FlowControlAPIGroupClient.class);
   }

   public FlowControlAPIGroupClient newInstance() {
      return new FlowControlAPIGroupClient();
   }
}
