package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ScaleSpecBuilder extends ScaleSpecFluent implements VisitableBuilder {
   ScaleSpecFluent fluent;

   public ScaleSpecBuilder() {
      this(new ScaleSpec());
   }

   public ScaleSpecBuilder(ScaleSpecFluent fluent) {
      this(fluent, new ScaleSpec());
   }

   public ScaleSpecBuilder(ScaleSpecFluent fluent, ScaleSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ScaleSpecBuilder(ScaleSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ScaleSpec build() {
      ScaleSpec buildable = new ScaleSpec(this.fluent.getReplicas());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
