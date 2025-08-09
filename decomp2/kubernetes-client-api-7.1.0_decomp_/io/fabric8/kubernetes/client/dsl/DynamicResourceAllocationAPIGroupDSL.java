package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.V1Alpha2DynamicResourceAllocationAPIGroupDSL;

public interface DynamicResourceAllocationAPIGroupDSL extends Client {
   V1Alpha2DynamicResourceAllocationAPIGroupDSL v1alpha2();
}
