package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ManagedFieldsEntryBuilder extends ManagedFieldsEntryFluent implements VisitableBuilder {
   ManagedFieldsEntryFluent fluent;

   public ManagedFieldsEntryBuilder() {
      this(new ManagedFieldsEntry());
   }

   public ManagedFieldsEntryBuilder(ManagedFieldsEntryFluent fluent) {
      this(fluent, new ManagedFieldsEntry());
   }

   public ManagedFieldsEntryBuilder(ManagedFieldsEntryFluent fluent, ManagedFieldsEntry instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ManagedFieldsEntryBuilder(ManagedFieldsEntry instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ManagedFieldsEntry build() {
      ManagedFieldsEntry buildable = new ManagedFieldsEntry(this.fluent.getApiVersion(), this.fluent.getFieldsType(), this.fluent.buildFieldsV1(), this.fluent.getManager(), this.fluent.getOperation(), this.fluent.getSubresource(), this.fluent.getTime());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
