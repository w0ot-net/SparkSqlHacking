package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HostIPBuilder extends HostIPFluent implements VisitableBuilder {
   HostIPFluent fluent;

   public HostIPBuilder() {
      this(new HostIP());
   }

   public HostIPBuilder(HostIPFluent fluent) {
      this(fluent, new HostIP());
   }

   public HostIPBuilder(HostIPFluent fluent, HostIP instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HostIPBuilder(HostIP instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HostIP build() {
      HostIP buildable = new HostIP(this.fluent.getIp());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
