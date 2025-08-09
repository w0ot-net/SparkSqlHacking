package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class MigrationConditionBuilder extends MigrationConditionFluent implements VisitableBuilder {
   MigrationConditionFluent fluent;

   public MigrationConditionBuilder() {
      this(new MigrationCondition());
   }

   public MigrationConditionBuilder(MigrationConditionFluent fluent) {
      this(fluent, new MigrationCondition());
   }

   public MigrationConditionBuilder(MigrationConditionFluent fluent, MigrationCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public MigrationConditionBuilder(MigrationCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public MigrationCondition build() {
      MigrationCondition buildable = new MigrationCondition(this.fluent.getLastUpdateTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
