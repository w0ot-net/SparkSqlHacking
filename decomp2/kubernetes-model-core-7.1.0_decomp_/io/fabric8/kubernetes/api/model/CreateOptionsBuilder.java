package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CreateOptionsBuilder extends CreateOptionsFluent implements VisitableBuilder {
   CreateOptionsFluent fluent;

   public CreateOptionsBuilder() {
      this(new CreateOptions());
   }

   public CreateOptionsBuilder(CreateOptionsFluent fluent) {
      this(fluent, new CreateOptions());
   }

   public CreateOptionsBuilder(CreateOptionsFluent fluent, CreateOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CreateOptionsBuilder(CreateOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CreateOptions build() {
      CreateOptions buildable = new CreateOptions(this.fluent.getApiVersion(), this.fluent.getDryRun(), this.fluent.getFieldManager(), this.fluent.getFieldValidation(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
