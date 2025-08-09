package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ListMetaBuilder extends ListMetaFluent implements VisitableBuilder {
   ListMetaFluent fluent;

   public ListMetaBuilder() {
      this(new ListMeta());
   }

   public ListMetaBuilder(ListMetaFluent fluent) {
      this(fluent, new ListMeta());
   }

   public ListMetaBuilder(ListMetaFluent fluent, ListMeta instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ListMetaBuilder(ListMeta instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ListMeta build() {
      ListMeta buildable = new ListMeta(this.fluent.getContinue(), this.fluent.getRemainingItemCount(), this.fluent.getResourceVersion(), this.fluent.getSelfLink());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
