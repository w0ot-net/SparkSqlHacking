package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PreferencesBuilder extends PreferencesFluent implements VisitableBuilder {
   PreferencesFluent fluent;

   public PreferencesBuilder() {
      this(new Preferences());
   }

   public PreferencesBuilder(PreferencesFluent fluent) {
      this(fluent, new Preferences());
   }

   public PreferencesBuilder(PreferencesFluent fluent, Preferences instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PreferencesBuilder(Preferences instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Preferences build() {
      Preferences buildable = new Preferences(this.fluent.getColors(), this.fluent.buildExtensions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
