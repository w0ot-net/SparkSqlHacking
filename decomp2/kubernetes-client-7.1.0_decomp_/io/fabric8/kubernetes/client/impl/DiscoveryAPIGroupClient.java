package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.dsl.DiscoveryAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1DiscoveryAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1DiscoveryAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class DiscoveryAPIGroupClient extends ClientAdapter implements DiscoveryAPIGroupDSL {
   public V1DiscoveryAPIGroupDSL v1() {
      return (V1DiscoveryAPIGroupDSL)this.adapt(V1DiscoveryAPIGroupClient.class);
   }

   public V1beta1DiscoveryAPIGroupDSL v1beta1() {
      return (V1beta1DiscoveryAPIGroupDSL)this.adapt(V1beta1DiscoveryAPIGroupClient.class);
   }

   public DiscoveryAPIGroupClient newInstance() {
      return new DiscoveryAPIGroupClient();
   }
}
