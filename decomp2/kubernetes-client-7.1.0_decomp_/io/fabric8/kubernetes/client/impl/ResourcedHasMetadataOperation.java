package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.extension.ExtensibleResourceAdapter;

class ResourcedHasMetadataOperation extends HasMetadataOperationsImpl {
   private ExtensibleResourceAdapter adapter;

   public ResourcedHasMetadataOperation(OperationContext ctx, ResourceDefinitionContext resourceDefinitionContext, Class type, Class listType, ExtensibleResourceAdapter adapter) {
      super(ctx, resourceDefinitionContext, type, listType);
      this.adapter = adapter;
   }

   protected ExtensibleResourceAdapter newResource(OperationContext context) {
      ExtensibleResourceAdapter<T> result = this.adapter.newInstance();
      result.init(super.newInstance(context), context.getClient());
      return result;
   }

   public HasMetadataOperationsImpl newInstance(OperationContext context) {
      return new ResourcedHasMetadataOperation(context, this.rdc, this.type, this.listType, this.adapter);
   }
}
