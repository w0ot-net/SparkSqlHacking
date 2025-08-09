package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class KeyToPathBuilder extends KeyToPathFluent implements VisitableBuilder {
   KeyToPathFluent fluent;

   public KeyToPathBuilder() {
      this(new KeyToPath());
   }

   public KeyToPathBuilder(KeyToPathFluent fluent) {
      this(fluent, new KeyToPath());
   }

   public KeyToPathBuilder(KeyToPathFluent fluent, KeyToPath instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public KeyToPathBuilder(KeyToPath instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public KeyToPath build() {
      KeyToPath buildable = new KeyToPath(this.fluent.getKey(), this.fluent.getMode(), this.fluent.getPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
