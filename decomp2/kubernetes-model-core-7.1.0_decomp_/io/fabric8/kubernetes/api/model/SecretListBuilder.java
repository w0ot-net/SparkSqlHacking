package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecretListBuilder extends SecretListFluent implements VisitableBuilder {
   SecretListFluent fluent;

   public SecretListBuilder() {
      this(new SecretList());
   }

   public SecretListBuilder(SecretListFluent fluent) {
      this(fluent, new SecretList());
   }

   public SecretListBuilder(SecretListFluent fluent, SecretList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecretListBuilder(SecretList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecretList build() {
      SecretList buildable = new SecretList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
