package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GroupVersionResourceBuilder extends GroupVersionResourceFluent implements VisitableBuilder {
   GroupVersionResourceFluent fluent;

   public GroupVersionResourceBuilder() {
      this(new GroupVersionResource());
   }

   public GroupVersionResourceBuilder(GroupVersionResourceFluent fluent) {
      this(fluent, new GroupVersionResource());
   }

   public GroupVersionResourceBuilder(GroupVersionResourceFluent fluent, GroupVersionResource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GroupVersionResourceBuilder(GroupVersionResource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GroupVersionResource build() {
      GroupVersionResource buildable = new GroupVersionResource(this.fluent.getGroup(), this.fluent.getResource(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
