package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface DiscoveryAPIGroupDSL extends Client {
   V1DiscoveryAPIGroupDSL v1();

   V1beta1DiscoveryAPIGroupDSL v1beta1();
}
