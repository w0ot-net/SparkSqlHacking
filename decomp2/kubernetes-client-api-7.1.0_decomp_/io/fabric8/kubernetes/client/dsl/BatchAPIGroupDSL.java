package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface BatchAPIGroupDSL extends Client {
   /** @deprecated */
   @Deprecated
   MixedOperation jobs();

   /** @deprecated */
   @Deprecated
   MixedOperation cronjobs();

   V1BatchAPIGroupDSL v1();

   V1beta1BatchAPIGroupDSL v1beta1();
}
