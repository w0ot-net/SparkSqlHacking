package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamespaceListBuilder extends NamespaceListFluent implements VisitableBuilder {
   NamespaceListFluent fluent;

   public NamespaceListBuilder() {
      this(new NamespaceList());
   }

   public NamespaceListBuilder(NamespaceListFluent fluent) {
      this(fluent, new NamespaceList());
   }

   public NamespaceListBuilder(NamespaceListFluent fluent, NamespaceList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamespaceListBuilder(NamespaceList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamespaceList build() {
      NamespaceList buildable = new NamespaceList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
