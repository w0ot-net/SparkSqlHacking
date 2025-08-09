package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressClassListBuilder extends IngressClassListFluent implements VisitableBuilder {
   IngressClassListFluent fluent;

   public IngressClassListBuilder() {
      this(new IngressClassList());
   }

   public IngressClassListBuilder(IngressClassListFluent fluent) {
      this(fluent, new IngressClassList());
   }

   public IngressClassListBuilder(IngressClassListFluent fluent, IngressClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressClassListBuilder(IngressClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressClassList build() {
      IngressClassList buildable = new IngressClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
