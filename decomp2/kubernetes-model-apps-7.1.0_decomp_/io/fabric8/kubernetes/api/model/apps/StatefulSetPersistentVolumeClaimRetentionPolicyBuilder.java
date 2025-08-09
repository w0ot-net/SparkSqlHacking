package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class StatefulSetPersistentVolumeClaimRetentionPolicyBuilder extends StatefulSetPersistentVolumeClaimRetentionPolicyFluent implements VisitableBuilder {
   StatefulSetPersistentVolumeClaimRetentionPolicyFluent fluent;

   public StatefulSetPersistentVolumeClaimRetentionPolicyBuilder() {
      this(new StatefulSetPersistentVolumeClaimRetentionPolicy());
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyBuilder(StatefulSetPersistentVolumeClaimRetentionPolicyFluent fluent) {
      this(fluent, new StatefulSetPersistentVolumeClaimRetentionPolicy());
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyBuilder(StatefulSetPersistentVolumeClaimRetentionPolicyFluent fluent, StatefulSetPersistentVolumeClaimRetentionPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyBuilder(StatefulSetPersistentVolumeClaimRetentionPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicy build() {
      StatefulSetPersistentVolumeClaimRetentionPolicy buildable = new StatefulSetPersistentVolumeClaimRetentionPolicy(this.fluent.getWhenDeleted(), this.fluent.getWhenScaled());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
