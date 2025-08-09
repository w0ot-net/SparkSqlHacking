package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ApplyConfigurationBuilder extends ApplyConfigurationFluent implements VisitableBuilder {
   ApplyConfigurationFluent fluent;

   public ApplyConfigurationBuilder() {
      this(new ApplyConfiguration());
   }

   public ApplyConfigurationBuilder(ApplyConfigurationFluent fluent) {
      this(fluent, new ApplyConfiguration());
   }

   public ApplyConfigurationBuilder(ApplyConfigurationFluent fluent, ApplyConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ApplyConfigurationBuilder(ApplyConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ApplyConfiguration build() {
      ApplyConfiguration buildable = new ApplyConfiguration(this.fluent.getExpression());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
