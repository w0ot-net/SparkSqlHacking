package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DownwardAPIProjectionBuilder extends DownwardAPIProjectionFluent implements VisitableBuilder {
   DownwardAPIProjectionFluent fluent;

   public DownwardAPIProjectionBuilder() {
      this(new DownwardAPIProjection());
   }

   public DownwardAPIProjectionBuilder(DownwardAPIProjectionFluent fluent) {
      this(fluent, new DownwardAPIProjection());
   }

   public DownwardAPIProjectionBuilder(DownwardAPIProjectionFluent fluent, DownwardAPIProjection instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DownwardAPIProjectionBuilder(DownwardAPIProjection instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DownwardAPIProjection build() {
      DownwardAPIProjection buildable = new DownwardAPIProjection(this.fluent.buildItems());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
