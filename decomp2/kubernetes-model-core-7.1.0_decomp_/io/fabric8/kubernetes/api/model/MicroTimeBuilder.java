package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MicroTimeBuilder extends MicroTimeFluent implements VisitableBuilder {
   MicroTimeFluent fluent;

   public MicroTimeBuilder() {
      this(new MicroTime());
   }

   public MicroTimeBuilder(MicroTimeFluent fluent) {
      this(fluent, new MicroTime());
   }

   public MicroTimeBuilder(MicroTimeFluent fluent, MicroTime instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MicroTimeBuilder(MicroTime instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MicroTime build() {
      MicroTime buildable = new MicroTime(this.fluent.getTime());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
