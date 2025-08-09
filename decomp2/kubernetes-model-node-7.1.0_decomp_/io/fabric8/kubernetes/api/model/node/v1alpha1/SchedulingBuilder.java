package io.fabric8.kubernetes.api.model.node.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SchedulingBuilder extends SchedulingFluent implements VisitableBuilder {
   SchedulingFluent fluent;

   public SchedulingBuilder() {
      this(new Scheduling());
   }

   public SchedulingBuilder(SchedulingFluent fluent) {
      this(fluent, new Scheduling());
   }

   public SchedulingBuilder(SchedulingFluent fluent, Scheduling instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SchedulingBuilder(Scheduling instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Scheduling build() {
      Scheduling buildable = new Scheduling(this.fluent.getNodeSelector(), this.fluent.getTolerations());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
