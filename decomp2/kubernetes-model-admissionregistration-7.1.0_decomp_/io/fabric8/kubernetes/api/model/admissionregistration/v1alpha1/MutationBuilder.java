package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MutationBuilder extends MutationFluent implements VisitableBuilder {
   MutationFluent fluent;

   public MutationBuilder() {
      this(new Mutation());
   }

   public MutationBuilder(MutationFluent fluent) {
      this(fluent, new Mutation());
   }

   public MutationBuilder(MutationFluent fluent, Mutation instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MutationBuilder(Mutation instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Mutation build() {
      Mutation buildable = new Mutation(this.fluent.buildApplyConfiguration(), this.fluent.buildJsonPatch(), this.fluent.getPatchType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
