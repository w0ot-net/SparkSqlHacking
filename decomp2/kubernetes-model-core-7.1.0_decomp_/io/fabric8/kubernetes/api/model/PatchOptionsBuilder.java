package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PatchOptionsBuilder extends PatchOptionsFluent implements VisitableBuilder {
   PatchOptionsFluent fluent;

   public PatchOptionsBuilder() {
      this(new PatchOptions());
   }

   public PatchOptionsBuilder(PatchOptionsFluent fluent) {
      this(fluent, new PatchOptions());
   }

   public PatchOptionsBuilder(PatchOptionsFluent fluent, PatchOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PatchOptionsBuilder(PatchOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PatchOptions build() {
      PatchOptions buildable = new PatchOptions(this.fluent.getApiVersion(), this.fluent.getDryRun(), this.fluent.getFieldManager(), this.fluent.getFieldValidation(), this.fluent.getForce(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
