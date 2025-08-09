package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetBuilder extends StatefulSetFluent implements VisitableBuilder {
   StatefulSetFluent fluent;

   public StatefulSetBuilder() {
      this(new StatefulSet());
   }

   public StatefulSetBuilder(StatefulSetFluent fluent) {
      this(fluent, new StatefulSet());
   }

   public StatefulSetBuilder(StatefulSetFluent fluent, StatefulSet instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetBuilder(StatefulSet instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSet build() {
      StatefulSet buildable = new StatefulSet(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
