package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceRequirementsBuilder extends ResourceRequirementsFluent implements VisitableBuilder {
   ResourceRequirementsFluent fluent;

   public ResourceRequirementsBuilder() {
      this(new ResourceRequirements());
   }

   public ResourceRequirementsBuilder(ResourceRequirementsFluent fluent) {
      this(fluent, new ResourceRequirements());
   }

   public ResourceRequirementsBuilder(ResourceRequirementsFluent fluent, ResourceRequirements instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceRequirementsBuilder(ResourceRequirements instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceRequirements build() {
      ResourceRequirements buildable = new ResourceRequirements(this.fluent.buildClaims(), this.fluent.getLimits(), this.fluent.getRequests());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
