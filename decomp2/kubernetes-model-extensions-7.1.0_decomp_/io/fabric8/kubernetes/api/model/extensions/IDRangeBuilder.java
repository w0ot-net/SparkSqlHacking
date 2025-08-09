package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IDRangeBuilder extends IDRangeFluent implements VisitableBuilder {
   IDRangeFluent fluent;

   public IDRangeBuilder() {
      this(new IDRange());
   }

   public IDRangeBuilder(IDRangeFluent fluent) {
      this(fluent, new IDRange());
   }

   public IDRangeBuilder(IDRangeFluent fluent, IDRange instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IDRangeBuilder(IDRange instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IDRange build() {
      IDRange buildable = new IDRange(this.fluent.getMax(), this.fluent.getMin());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
