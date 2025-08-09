package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetSpecBuilder extends StatefulSetSpecFluent implements VisitableBuilder {
   StatefulSetSpecFluent fluent;

   public StatefulSetSpecBuilder() {
      this(new StatefulSetSpec());
   }

   public StatefulSetSpecBuilder(StatefulSetSpecFluent fluent) {
      this(fluent, new StatefulSetSpec());
   }

   public StatefulSetSpecBuilder(StatefulSetSpecFluent fluent, StatefulSetSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetSpecBuilder(StatefulSetSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSetSpec build() {
      StatefulSetSpec buildable = new StatefulSetSpec(this.fluent.getMinReadySeconds(), this.fluent.buildOrdinals(), this.fluent.buildPersistentVolumeClaimRetentionPolicy(), this.fluent.getPodManagementPolicy(), this.fluent.getReplicas(), this.fluent.getRevisionHistoryLimit(), this.fluent.buildSelector(), this.fluent.getServiceName(), this.fluent.buildTemplate(), this.fluent.buildUpdateStrategy(), this.fluent.buildVolumeClaimTemplates());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
