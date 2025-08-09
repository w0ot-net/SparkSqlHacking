package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;

public interface V1NetworkAPIGroupDSL extends Client {
   MixedOperation networkPolicies();

   MixedOperation ingresses();

   NonNamespaceOperation ingressClasses();
}
