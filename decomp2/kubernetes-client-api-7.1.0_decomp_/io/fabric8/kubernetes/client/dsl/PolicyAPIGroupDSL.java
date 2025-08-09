package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface PolicyAPIGroupDSL extends Client {
   /** @deprecated */
   @Deprecated
   MixedOperation podDisruptionBudget();

   /** @deprecated */
   @Deprecated
   MixedOperation podSecurityPolicies();

   V1PolicyAPIGroupDSL v1();

   V1beta1PolicyAPIGroupDSL v1beta1();
}
