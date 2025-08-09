package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SupportedFeatureBuilder extends SupportedFeatureFluent implements VisitableBuilder {
   SupportedFeatureFluent fluent;

   public SupportedFeatureBuilder() {
      this(new SupportedFeature());
   }

   public SupportedFeatureBuilder(SupportedFeatureFluent fluent) {
      this(fluent, new SupportedFeature());
   }

   public SupportedFeatureBuilder(SupportedFeatureFluent fluent, SupportedFeature instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SupportedFeatureBuilder(SupportedFeature instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SupportedFeature build() {
      SupportedFeature buildable = new SupportedFeature(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
