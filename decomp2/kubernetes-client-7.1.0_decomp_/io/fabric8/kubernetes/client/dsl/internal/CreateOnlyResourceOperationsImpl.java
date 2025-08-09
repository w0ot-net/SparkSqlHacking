package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.NamespacedInOutCreateable;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;

public class CreateOnlyResourceOperationsImpl extends CreateOnlyResourceOperation implements NamespacedInOutCreateable {
   private final ResourceDefinitionContext rdc;
   private Class inputType;

   public CreateOnlyResourceOperationsImpl(Client client, ResourceDefinitionContext rdc, Class inputType, Class outputType) {
      this(HasMetadataOperationsImpl.defaultContext(client), rdc, inputType, outputType);
   }

   public CreateOnlyResourceOperationsImpl(OperationContext context, ResourceDefinitionContext rdc, Class inputType, Class outputType) {
      super(context.withApiGroupName(rdc.getGroup()).withApiGroupVersion(rdc.getVersion()).withPlural(rdc.getPlural()));
      this.inputType = inputType;
      this.type = outputType;
      this.rdc = rdc;
      this.apiGroupName = rdc.getGroup();
      this.apiGroupVersion = rdc.getVersion();
   }

   public boolean isResourceNamespaced() {
      return this.rdc.isNamespaceScoped();
   }

   public CreateOnlyResourceOperationsImpl inNamespace(String name) {
      return new CreateOnlyResourceOperationsImpl(this.context.withNamespace(name), this.rdc, this.inputType, this.type);
   }
}
