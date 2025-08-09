package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TopologySpreadConstraintBuilder extends TopologySpreadConstraintFluent implements VisitableBuilder {
   TopologySpreadConstraintFluent fluent;

   public TopologySpreadConstraintBuilder() {
      this(new TopologySpreadConstraint());
   }

   public TopologySpreadConstraintBuilder(TopologySpreadConstraintFluent fluent) {
      this(fluent, new TopologySpreadConstraint());
   }

   public TopologySpreadConstraintBuilder(TopologySpreadConstraintFluent fluent, TopologySpreadConstraint instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TopologySpreadConstraintBuilder(TopologySpreadConstraint instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TopologySpreadConstraint build() {
      TopologySpreadConstraint buildable = new TopologySpreadConstraint(this.fluent.buildLabelSelector(), this.fluent.getMatchLabelKeys(), this.fluent.getMaxSkew(), this.fluent.getMinDomains(), this.fluent.getNodeAffinityPolicy(), this.fluent.getNodeTaintsPolicy(), this.fluent.getTopologyKey(), this.fluent.getWhenUnsatisfiable());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
