package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeResourceRequirementsBuilder extends VolumeResourceRequirementsFluent implements VisitableBuilder {
   VolumeResourceRequirementsFluent fluent;

   public VolumeResourceRequirementsBuilder() {
      this(new VolumeResourceRequirements());
   }

   public VolumeResourceRequirementsBuilder(VolumeResourceRequirementsFluent fluent) {
      this(fluent, new VolumeResourceRequirements());
   }

   public VolumeResourceRequirementsBuilder(VolumeResourceRequirementsFluent fluent, VolumeResourceRequirements instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeResourceRequirementsBuilder(VolumeResourceRequirements instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeResourceRequirements build() {
      VolumeResourceRequirements buildable = new VolumeResourceRequirements(this.fluent.getLimits(), this.fluent.getRequests());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
