package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.storage.v1beta1.CSIDriver;
import io.fabric8.kubernetes.api.model.storage.v1beta1.CSIDriverList;
import io.fabric8.kubernetes.api.model.storage.v1beta1.CSINode;
import io.fabric8.kubernetes.api.model.storage.v1beta1.CSINodeList;
import io.fabric8.kubernetes.api.model.storage.v1beta1.CSIStorageCapacity;
import io.fabric8.kubernetes.api.model.storage.v1beta1.CSIStorageCapacityList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1StorageAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1StorageAPIGroupClient extends ClientAdapter implements V1beta1StorageAPIGroupDSL {
   public NonNamespaceOperation csiDrivers() {
      return this.resources(CSIDriver.class, CSIDriverList.class);
   }

   public NonNamespaceOperation csiNodes() {
      return this.resources(CSINode.class, CSINodeList.class);
   }

   public MixedOperation csiStorageCapacities() {
      return this.resources(CSIStorageCapacity.class, CSIStorageCapacityList.class);
   }

   public V1beta1StorageAPIGroupClient newInstance() {
      return new V1beta1StorageAPIGroupClient();
   }
}
