package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GroupVersionKindBuilder extends GroupVersionKindFluent implements VisitableBuilder {
   GroupVersionKindFluent fluent;

   public GroupVersionKindBuilder() {
      this(new GroupVersionKind());
   }

   public GroupVersionKindBuilder(GroupVersionKindFluent fluent) {
      this(fluent, new GroupVersionKind());
   }

   public GroupVersionKindBuilder(GroupVersionKindFluent fluent, GroupVersionKind instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GroupVersionKindBuilder(GroupVersionKind instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GroupVersionKind build() {
      GroupVersionKind buildable = new GroupVersionKind(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
