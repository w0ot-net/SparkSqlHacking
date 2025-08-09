package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.V1NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1NetworkAPIGroupDSL;

public interface NetworkAPIGroupDSL extends Client {
   V1NetworkAPIGroupDSL v1();

   V1beta1NetworkAPIGroupDSL v1beta1();

   MixedOperation networkPolicies();

   MixedOperation ingress();

   MixedOperation ingresses();
}
