package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ObjectMetaBuilder extends ObjectMetaFluent implements VisitableBuilder {
   ObjectMetaFluent fluent;

   public ObjectMetaBuilder() {
      this(new ObjectMeta());
   }

   public ObjectMetaBuilder(ObjectMetaFluent fluent) {
      this(fluent, new ObjectMeta());
   }

   public ObjectMetaBuilder(ObjectMetaFluent fluent, ObjectMeta instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ObjectMetaBuilder(ObjectMeta instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ObjectMeta build() {
      ObjectMeta buildable = new ObjectMeta(this.fluent.getAnnotations(), this.fluent.getCreationTimestamp(), this.fluent.getDeletionGracePeriodSeconds(), this.fluent.getDeletionTimestamp(), this.fluent.getFinalizers(), this.fluent.getGenerateName(), this.fluent.getGeneration(), this.fluent.getLabels(), this.fluent.buildManagedFields(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.buildOwnerReferences(), this.fluent.getResourceVersion(), this.fluent.getSelfLink(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
