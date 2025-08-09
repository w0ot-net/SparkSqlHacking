package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TopologySelectorTermBuilder extends TopologySelectorTermFluent implements VisitableBuilder {
   TopologySelectorTermFluent fluent;

   public TopologySelectorTermBuilder() {
      this(new TopologySelectorTerm());
   }

   public TopologySelectorTermBuilder(TopologySelectorTermFluent fluent) {
      this(fluent, new TopologySelectorTerm());
   }

   public TopologySelectorTermBuilder(TopologySelectorTermFluent fluent, TopologySelectorTerm instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TopologySelectorTermBuilder(TopologySelectorTerm instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TopologySelectorTerm build() {
      TopologySelectorTerm buildable = new TopologySelectorTerm(this.fluent.buildMatchLabelExpressions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
