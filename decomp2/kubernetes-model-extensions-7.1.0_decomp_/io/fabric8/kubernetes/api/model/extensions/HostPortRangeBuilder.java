package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HostPortRangeBuilder extends HostPortRangeFluent implements VisitableBuilder {
   HostPortRangeFluent fluent;

   public HostPortRangeBuilder() {
      this(new HostPortRange());
   }

   public HostPortRangeBuilder(HostPortRangeFluent fluent) {
      this(fluent, new HostPortRange());
   }

   public HostPortRangeBuilder(HostPortRangeFluent fluent, HostPortRange instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HostPortRangeBuilder(HostPortRange instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HostPortRange build() {
      HostPortRange buildable = new HostPortRange(this.fluent.getMax(), this.fluent.getMin());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
