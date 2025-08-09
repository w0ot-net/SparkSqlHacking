package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface V1StorageAPIGroupDSL extends Client {
   NonNamespaceOperation storageClasses();

   NonNamespaceOperation csiDrivers();

   NonNamespaceOperation csiNodes();

   MixedOperation csiStorageCapacities();

   NonNamespaceOperation volumeAttachments();
}
