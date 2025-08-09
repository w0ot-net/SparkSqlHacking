package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface V1beta1DiscoveryAPIGroupDSL extends Client {
   MixedOperation endpointSlices();
}
