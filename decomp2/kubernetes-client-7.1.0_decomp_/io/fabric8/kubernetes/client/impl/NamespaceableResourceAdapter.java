package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.dsl.NamespaceableResource;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.extension.ResourceAdapter;

public class NamespaceableResourceAdapter extends ResourceAdapter implements NamespaceableResource {
   protected final HasMetadata item;
   protected final HasMetadataOperation operation;

   public NamespaceableResourceAdapter(HasMetadata item, HasMetadataOperation op) {
      super(op.resource(item));
      this.operation = op;
      this.item = item;
   }

   public Resource inNamespace(String name) {
      return this.operation.inNamespace(name).resource(this.item);
   }
}
