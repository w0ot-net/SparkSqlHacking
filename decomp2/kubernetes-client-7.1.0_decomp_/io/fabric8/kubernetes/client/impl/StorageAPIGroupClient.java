package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.storage.CSIDriver;
import io.fabric8.kubernetes.api.model.storage.CSIDriverList;
import io.fabric8.kubernetes.api.model.storage.CSINode;
import io.fabric8.kubernetes.api.model.storage.CSINodeList;
import io.fabric8.kubernetes.api.model.storage.StorageClass;
import io.fabric8.kubernetes.api.model.storage.StorageClassList;
import io.fabric8.kubernetes.api.model.storage.VolumeAttachment;
import io.fabric8.kubernetes.api.model.storage.VolumeAttachmentList;
import io.fabric8.kubernetes.api.model.storage.v1beta1.CSIStorageCapacity;
import io.fabric8.kubernetes.api.model.storage.v1beta1.CSIStorageCapacityList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.StorageAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1StorageAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1StorageAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class StorageAPIGroupClient extends ClientAdapter implements StorageAPIGroupDSL {
   public MixedOperation storageClasses() {
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

   public V1StorageAPIGroupDSL v1() {
      return (V1StorageAPIGroupDSL)this.adapt(V1StorageAPIGroupClient.class);
   }

   public V1beta1StorageAPIGroupDSL v1beta1() {
      return (V1beta1StorageAPIGroupDSL)this.adapt(V1beta1StorageAPIGroupClient.class);
   }

   public StorageAPIGroupClient newInstance() {
      return new StorageAPIGroupClient();
   }
}
