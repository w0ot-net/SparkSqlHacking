package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;

public interface V1Alpha2DynamicResourceAllocationAPIGroupDSL extends Client {
   NonNamespaceOperation resourceClasses();

   MixedOperation podSchedulings();

   MixedOperation resourceClaims();

   MixedOperation resourceClaimTemplates();
}
