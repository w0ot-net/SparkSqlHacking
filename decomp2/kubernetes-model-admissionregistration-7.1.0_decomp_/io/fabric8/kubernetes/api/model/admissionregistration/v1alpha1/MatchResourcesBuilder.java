package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MatchResourcesBuilder extends MatchResourcesFluent implements VisitableBuilder {
   MatchResourcesFluent fluent;

   public MatchResourcesBuilder() {
      this(new MatchResources());
   }

   public MatchResourcesBuilder(MatchResourcesFluent fluent) {
      this(fluent, new MatchResources());
   }

   public MatchResourcesBuilder(MatchResourcesFluent fluent, MatchResources instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MatchResourcesBuilder(MatchResources instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MatchResources build() {
      MatchResources buildable = new MatchResources(this.fluent.buildExcludeResourceRules(), this.fluent.getMatchPolicy(), this.fluent.buildNamespaceSelector(), this.fluent.buildObjectSelector(), this.fluent.buildResourceRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
