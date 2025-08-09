package io.fabric8.kubernetes.api.model.rbac;

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
      Subject buildable = new Subject(this.fluent.getApiGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
