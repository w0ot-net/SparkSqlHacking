package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface ExtensionsAPIGroupDSL extends Client {
   /** @deprecated */
   @Deprecated
   MixedOperation daemonSets();

   /** @deprecated */
   @Deprecated
   MixedOperation deployments();

   /** @deprecated */
   @Deprecated
   MixedOperation ingress();

   /** @deprecated */
   MixedOperation ingresses();

   /** @deprecated */
   @Deprecated
   MixedOperation jobs();

   /** @deprecated */
   @Deprecated
   MixedOperation networkPolicies();

   /** @deprecated */
   @Deprecated
   MixedOperation replicaSets();

   /** @deprecated */
   @Deprecated
   MixedOperation podSecurityPolicies();
}
