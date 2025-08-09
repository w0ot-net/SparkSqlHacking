package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FSGroupStrategyOptionsBuilder extends FSGroupStrategyOptionsFluent implements VisitableBuilder {
   FSGroupStrategyOptionsFluent fluent;

   public FSGroupStrategyOptionsBuilder() {
      this(new FSGroupStrategyOptions());
   }

   public FSGroupStrategyOptionsBuilder(FSGroupStrategyOptionsFluent fluent) {
      this(fluent, new FSGroupStrategyOptions());
   }

   public FSGroupStrategyOptionsBuilder(FSGroupStrategyOptionsFluent fluent, FSGroupStrategyOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FSGroupStrategyOptionsBuilder(FSGroupStrategyOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FSGroupStrategyOptions build() {
      FSGroupStrategyOptions buildable = new FSGroupStrategyOptions(this.fluent.buildRanges(), this.fluent.getRule());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
