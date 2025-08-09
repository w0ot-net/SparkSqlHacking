package io.fabric8.kubernetes.api.model.flowcontrol.v1beta2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GroupSubjectBuilder extends GroupSubjectFluent implements VisitableBuilder {
   GroupSubjectFluent fluent;

   public GroupSubjectBuilder() {
      this(new GroupSubject());
   }

   public GroupSubjectBuilder(GroupSubjectFluent fluent) {
      this(fluent, new GroupSubject());
   }

   public GroupSubjectBuilder(GroupSubjectFluent fluent, GroupSubject instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GroupSubjectBuilder(GroupSubject instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GroupSubject build() {
      GroupSubject buildable = new GroupSubject(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
