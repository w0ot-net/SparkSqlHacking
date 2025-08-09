package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReferenceGrantListBuilder extends ReferenceGrantListFluent implements VisitableBuilder {
   ReferenceGrantListFluent fluent;

   public ReferenceGrantListBuilder() {
      this(new ReferenceGrantList());
   }

   public ReferenceGrantListBuilder(ReferenceGrantListFluent fluent) {
      this(fluent, new ReferenceGrantList());
   }

   public ReferenceGrantListBuilder(ReferenceGrantListFluent fluent, ReferenceGrantList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReferenceGrantListBuilder(ReferenceGrantList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReferenceGrantList build() {
      ReferenceGrantList buildable = new ReferenceGrantList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
