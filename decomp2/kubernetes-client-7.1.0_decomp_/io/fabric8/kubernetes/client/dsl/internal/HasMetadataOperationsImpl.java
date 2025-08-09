package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.impl.BaseClient;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.Utils;

public class HasMetadataOperationsImpl extends HasMetadataOperation implements MixedOperation {
   protected final ResourceDefinitionContext rdc;

   public HasMetadataOperationsImpl(Client client, ResourceDefinitionContext rdc, Class type, Class listType) {
      this(defaultContext(client), rdc, type, listType);
   }

   public static OperationContext defaultContext(Client client) {
      return (OperationContext)Utils.getNonNullOrElse(((BaseClient)client.adapt(BaseClient.class)).getOperationContext(), (new OperationContext()).withClient(client).withPropagationPolicy(DEFAULT_PROPAGATION_POLICY));
   }

   public HasMetadataOperationsImpl(OperationContext context, ResourceDefinitionContext rdc, Class type, Class listType) {
      super(context.withApiGroupName(rdc.getGroup()).withApiGroupVersion(rdc.getVersion()).withPlural(rdc.getPlural()), type, listType != null ? listType : KubernetesResourceUtil.inferListType(type));
      this.rdc = rdc;
   }

   public HasMetadataOperationsImpl newInstance(OperationContext context) {
      return new HasMetadataOperationsImpl(context, this.rdc, this.type, this.listType);
   }

   public boolean isResourceNamespaced() {
      return this.rdc.isNamespaceScoped();
   }

   public OperationContext getOperationContext() {
      return this.context;
   }

   public String getKind() {
      return this.rdc.getKind();
   }
}
