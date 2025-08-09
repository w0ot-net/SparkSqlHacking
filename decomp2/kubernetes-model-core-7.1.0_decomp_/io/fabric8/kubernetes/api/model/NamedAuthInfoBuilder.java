package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedAuthInfoBuilder extends NamedAuthInfoFluent implements VisitableBuilder {
   NamedAuthInfoFluent fluent;

   public NamedAuthInfoBuilder() {
      this(new NamedAuthInfo());
   }

   public NamedAuthInfoBuilder(NamedAuthInfoFluent fluent) {
      this(fluent, new NamedAuthInfo());
   }

   public NamedAuthInfoBuilder(NamedAuthInfoFluent fluent, NamedAuthInfo instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedAuthInfoBuilder(NamedAuthInfo instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedAuthInfo build() {
      NamedAuthInfo buildable = new NamedAuthInfo(this.fluent.getName(), this.fluent.buildUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
