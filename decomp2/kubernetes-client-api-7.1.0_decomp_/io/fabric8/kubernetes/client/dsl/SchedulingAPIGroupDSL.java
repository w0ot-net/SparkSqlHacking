package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface SchedulingAPIGroupDSL extends Client {
   /** @deprecated */
   @Deprecated
   MixedOperation priorityClass();

   V1SchedulingAPIGroupDSL v1();

   V1beta1SchedulingAPIGroupDSL v1beta1();
}
