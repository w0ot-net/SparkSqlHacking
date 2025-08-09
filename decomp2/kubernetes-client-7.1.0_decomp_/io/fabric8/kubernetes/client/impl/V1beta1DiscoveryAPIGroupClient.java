package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.discovery.v1beta1.EndpointSlice;
import io.fabric8.kubernetes.api.model.discovery.v1beta1.EndpointSliceList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1DiscoveryAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1DiscoveryAPIGroupClient extends ClientAdapter implements V1beta1DiscoveryAPIGroupDSL {
   public MixedOperation endpointSlices() {
      return this.resources(EndpointSlice.class, EndpointSliceList.class);
   }

   public V1beta1DiscoveryAPIGroupClient newInstance() {
      return new V1beta1DiscoveryAPIGroupClient();
   }
}
