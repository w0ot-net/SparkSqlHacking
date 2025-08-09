package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.storage.CSIDriver;
import io.fabric8.kubernetes.api.model.storage.CSIDriverList;
import io.fabric8.kubernetes.api.model.storage.CSINode;
import io.fabric8.kubernetes.api.model.storage.CSINodeList;
import io.fabric8.kubernetes.api.model.storage.CSIStorageCapacity;
import io.fabric8.kubernetes.api.model.storage.CSIStorageCapacityList;
import io.fabric8.kubernetes.api.model.storage.StorageClass;
import io.fabric8.kubernetes.api.model.storage.StorageClassList;
import io.fabric8.kubernetes.api.model.storage.VolumeAttachment;
import io.fabric8.kubernetes.api.model.storage.VolumeAttachmentList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1StorageAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1StorageAPIGroupClient extends ClientAdapter implements V1StorageAPIGroupDSL {
   public V1StorageAPIGroupClient newInstance() {
      return new V1StorageAPIGroupClient();
   }

   public NonNamespaceOperation storageClasses() {
      return this.resources(StorageClass.class, StorageClassList.class);
   }

   public NonNamespaceOperation csiDrivers() {
      return this.resources(CSIDriver.class, CSIDriverList.class);
   }

   public NonNamespaceOperation csiNodes() {
      return this.resources(CSINode.class, CSINodeList.class);
   }

   public MixedOperation csiStorageCapacities() {
      return this.resources(CSIStorageCapacity.class, CSIStorageCapacityList.class);
   }

   public NonNamespaceOperation volumeAttachments() {
      return this.resources(VolumeAttachment.class, VolumeAttachmentList.class);
   }
}
