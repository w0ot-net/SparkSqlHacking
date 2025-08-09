package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.V1Alpha2DynamicResourceAllocationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.DynamicResourceAllocationAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class DynamicResourceAllocationAPIGroupClient extends ClientAdapter implements DynamicResourceAllocationAPIGroupDSL {
   public DynamicResourceAllocationAPIGroupClient newInstance() {
      return new DynamicResourceAllocationAPIGroupClient();
   }

   public V1Alpha2DynamicResourceAllocationAPIGroupDSL v1alpha2() {
      return (V1Alpha2DynamicResourceAllocationAPIGroupDSL)this.adapt(V1Alpha2DynamicResourceAllocationAPIGroupClient.class);
   }
}
