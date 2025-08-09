package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AppArmorProfileBuilder extends AppArmorProfileFluent implements VisitableBuilder {
   AppArmorProfileFluent fluent;

   public AppArmorProfileBuilder() {
      this(new AppArmorProfile());
   }

   public AppArmorProfileBuilder(AppArmorProfileFluent fluent) {
      this(fluent, new AppArmorProfile());
   }

   public AppArmorProfileBuilder(AppArmorProfileFluent fluent, AppArmorProfile instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AppArmorProfileBuilder(AppArmorProfile instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AppArmorProfile build() {
      AppArmorProfile buildable = new AppArmorProfile(this.fluent.getLocalhostProfile(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
