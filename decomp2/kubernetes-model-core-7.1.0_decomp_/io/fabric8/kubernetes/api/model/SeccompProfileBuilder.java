package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SeccompProfileBuilder extends SeccompProfileFluent implements VisitableBuilder {
   SeccompProfileFluent fluent;

   public SeccompProfileBuilder() {
      this(new SeccompProfile());
   }

   public SeccompProfileBuilder(SeccompProfileFluent fluent) {
      this(fluent, new SeccompProfile());
   }

   public SeccompProfileBuilder(SeccompProfileFluent fluent, SeccompProfile instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SeccompProfileBuilder(SeccompProfile instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SeccompProfile build() {
      SeccompProfile buildable = new SeccompProfile(this.fluent.getLocalhostProfile(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
