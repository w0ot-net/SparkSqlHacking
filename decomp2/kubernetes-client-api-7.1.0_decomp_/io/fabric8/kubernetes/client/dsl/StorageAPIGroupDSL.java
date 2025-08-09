package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface StorageAPIGroupDSL extends Client {
   /** @deprecated */
   @Deprecated
   NonNamespaceOperation storageClasses();

   /** @deprecated */
   @Deprecated
   NonNamespaceOperation csiDrivers();

   /** @deprecated */
   @Deprecated
   NonNamespaceOperation csiNodes();

   /** @deprecated */
   @Deprecated
   MixedOperation csiStorageCapacities();

   /** @deprecated */
   @Deprecated
   NonNamespaceOperation volumeAttachments();

   V1StorageAPIGroupDSL v1();

   V1beta1StorageAPIGroupDSL v1beta1();
}
