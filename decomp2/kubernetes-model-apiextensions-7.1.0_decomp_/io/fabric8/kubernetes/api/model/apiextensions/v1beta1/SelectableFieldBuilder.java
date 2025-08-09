package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SelectableFieldBuilder extends SelectableFieldFluent implements VisitableBuilder {
   SelectableFieldFluent fluent;

   public SelectableFieldBuilder() {
      this(new SelectableField());
   }

   public SelectableFieldBuilder(SelectableFieldFluent fluent) {
      this(fluent, new SelectableField());
   }

   public SelectableFieldBuilder(SelectableFieldFluent fluent, SelectableField instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SelectableFieldBuilder(SelectableField instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SelectableField build() {
      SelectableField buildable = new SelectableField(this.fluent.getJsonPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
