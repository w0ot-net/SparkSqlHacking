package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UpdateOptionsBuilder extends UpdateOptionsFluent implements VisitableBuilder {
   UpdateOptionsFluent fluent;

   public UpdateOptionsBuilder() {
      this(new UpdateOptions());
   }

   public UpdateOptionsBuilder(UpdateOptionsFluent fluent) {
      this(fluent, new UpdateOptions());
   }

   public UpdateOptionsBuilder(UpdateOptionsFluent fluent, UpdateOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UpdateOptionsBuilder(UpdateOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UpdateOptions build() {
      UpdateOptions buildable = new UpdateOptions(this.fluent.getApiVersion(), this.fluent.getDryRun(), this.fluent.getFieldManager(), this.fluent.getFieldValidation(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
