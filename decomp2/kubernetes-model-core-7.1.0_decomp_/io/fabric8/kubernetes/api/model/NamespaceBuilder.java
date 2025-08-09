package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamespaceBuilder extends NamespaceFluent implements VisitableBuilder {
   NamespaceFluent fluent;

   public NamespaceBuilder() {
      this(new Namespace());
   }

   public NamespaceBuilder(NamespaceFluent fluent) {
      this(fluent, new Namespace());
   }

   public NamespaceBuilder(NamespaceFluent fluent, Namespace instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamespaceBuilder(Namespace instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Namespace build() {
      Namespace buildable = new Namespace(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
