package io.fabric8.kubernetes.api.model.node.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RuntimeClassListBuilder extends RuntimeClassListFluent implements VisitableBuilder {
   RuntimeClassListFluent fluent;

   public RuntimeClassListBuilder() {
      this(new RuntimeClassList());
   }

   public RuntimeClassListBuilder(RuntimeClassListFluent fluent) {
      this(fluent, new RuntimeClassList());
   }

   public RuntimeClassListBuilder(RuntimeClassListFluent fluent, RuntimeClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RuntimeClassListBuilder(RuntimeClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RuntimeClassList build() {
      RuntimeClassList buildable = new RuntimeClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
