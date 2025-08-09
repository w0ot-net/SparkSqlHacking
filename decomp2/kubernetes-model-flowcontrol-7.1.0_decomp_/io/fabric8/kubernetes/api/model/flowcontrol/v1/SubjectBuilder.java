package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SubjectBuilder extends SubjectFluent implements VisitableBuilder {
   SubjectFluent fluent;

   public SubjectBuilder() {
      this(new Subject());
   }

   public SubjectBuilder(SubjectFluent fluent) {
      this(fluent, new Subject());
   }

   public SubjectBuilder(SubjectFluent fluent, Subject instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SubjectBuilder(Subject instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Subject build() {
      Subject buildable = new Subject(this.fluent.buildGroup(), this.fluent.getKind(), this.fluent.buildServiceAccount(), this.fluent.buildUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
