package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GroupKindBuilder extends GroupKindFluent implements VisitableBuilder {
   GroupKindFluent fluent;

   public GroupKindBuilder() {
      this(new GroupKind());
   }

   public GroupKindBuilder(GroupKindFluent fluent) {
      this(fluent, new GroupKind());
   }

   public GroupKindBuilder(GroupKindFluent fluent, GroupKind instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GroupKindBuilder(GroupKind instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GroupKind build() {
      GroupKind buildable = new GroupKind(this.fluent.getGroup(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
