package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.resource.v1alpha2.PodSchedulingContext;
import io.fabric8.kubernetes.api.model.resource.v1alpha2.PodSchedulingContextList;
import io.fabric8.kubernetes.api.model.resource.v1alpha2.ResourceClaim;
import io.fabric8.kubernetes.api.model.resource.v1alpha2.ResourceClaimList;
import io.fabric8.kubernetes.api.model.resource.v1alpha2.ResourceClaimTemplate;
import io.fabric8.kubernetes.api.model.resource.v1alpha2.ResourceClaimTemplateList;
import io.fabric8.kubernetes.api.model.resource.v1alpha2.ResourceClass;
import io.fabric8.kubernetes.api.model.resource.v1alpha2.ResourceClassList;
import io.fabric8.kubernetes.client.V1Alpha2DynamicResourceAllocationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1Alpha2DynamicResourceAllocationAPIGroupClient extends ClientAdapter implements V1Alpha2DynamicResourceAllocationAPIGroupDSL {
   public V1Alpha2DynamicResourceAllocationAPIGroupClient newInstance() {
      return new V1Alpha2DynamicResourceAllocationAPIGroupClient();
   }

   public NonNamespaceOperation resourceClasses() {
      return this.resources(ResourceClass.class, ResourceClassList.class);
   }

   public MixedOperation podSchedulings() {
      return this.resources(PodSchedulingContext.class, PodSchedulingContextList.class);
   }

   public MixedOperation resourceClaims() {
      return this.resources(ResourceClaim.class, ResourceClaimList.class);
   }

   public MixedOperation resourceClaimTemplates() {
      return this.resources(ResourceClaimTemplate.class, ResourceClaimTemplateList.class);
   }
}
