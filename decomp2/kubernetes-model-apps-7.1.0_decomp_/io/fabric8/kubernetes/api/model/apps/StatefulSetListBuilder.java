package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetListBuilder extends StatefulSetListFluent implements VisitableBuilder {
   StatefulSetListFluent fluent;

   public StatefulSetListBuilder() {
      this(new StatefulSetList());
   }

   public StatefulSetListBuilder(StatefulSetListFluent fluent) {
      this(fluent, new StatefulSetList());
   }

   public StatefulSetListBuilder(StatefulSetListFluent fluent, StatefulSetList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetListBuilder(StatefulSetList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSetList build() {
      StatefulSetList buildable = new StatefulSetList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
