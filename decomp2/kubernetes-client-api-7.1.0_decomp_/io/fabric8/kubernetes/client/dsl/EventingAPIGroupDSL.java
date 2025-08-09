package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface EventingAPIGroupDSL extends Client {
   V1EventingAPIGroupDSL v1();

   V1beta1EventingAPIGroupDSL v1beta1();
}
