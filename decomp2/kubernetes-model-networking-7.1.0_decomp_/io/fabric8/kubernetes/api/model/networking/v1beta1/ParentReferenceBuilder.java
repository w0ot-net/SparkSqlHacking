package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ParentReferenceBuilder extends ParentReferenceFluent implements VisitableBuilder {
   ParentReferenceFluent fluent;

   public ParentReferenceBuilder() {
      this(new ParentReference());
   }

   public ParentReferenceBuilder(ParentReferenceFluent fluent) {
      this(fluent, new ParentReference());
   }

   public ParentReferenceBuilder(ParentReferenceFluent fluent, ParentReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ParentReferenceBuilder(ParentReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ParentReference build() {
      ParentReference buildable = new ParentReference(this.fluent.getGroup(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getResource());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
