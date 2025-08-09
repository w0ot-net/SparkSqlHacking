package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetOrdinalsBuilder extends StatefulSetOrdinalsFluent implements VisitableBuilder {
   StatefulSetOrdinalsFluent fluent;

   public StatefulSetOrdinalsBuilder() {
      this(new StatefulSetOrdinals());
   }

   public StatefulSetOrdinalsBuilder(StatefulSetOrdinalsFluent fluent) {
      this(fluent, new StatefulSetOrdinals());
   }

   public StatefulSetOrdinalsBuilder(StatefulSetOrdinalsFluent fluent, StatefulSetOrdinals instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetOrdinalsBuilder(StatefulSetOrdinals instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSetOrdinals build() {
      StatefulSetOrdinals buildable = new StatefulSetOrdinals(this.fluent.getStart());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
