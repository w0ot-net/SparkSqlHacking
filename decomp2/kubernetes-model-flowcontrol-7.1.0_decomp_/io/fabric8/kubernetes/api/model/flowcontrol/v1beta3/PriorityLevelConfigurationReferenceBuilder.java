package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PriorityLevelConfigurationReferenceBuilder extends PriorityLevelConfigurationReferenceFluent implements VisitableBuilder {
   PriorityLevelConfigurationReferenceFluent fluent;

   public PriorityLevelConfigurationReferenceBuilder() {
      this(new PriorityLevelConfigurationReference());
   }

   public PriorityLevelConfigurationReferenceBuilder(PriorityLevelConfigurationReferenceFluent fluent) {
      this(fluent, new PriorityLevelConfigurationReference());
   }

   public PriorityLevelConfigurationReferenceBuilder(PriorityLevelConfigurationReferenceFluent fluent, PriorityLevelConfigurationReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PriorityLevelConfigurationReferenceBuilder(PriorityLevelConfigurationReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PriorityLevelConfigurationReference build() {
      PriorityLevelConfigurationReference buildable = new PriorityLevelConfigurationReference(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
