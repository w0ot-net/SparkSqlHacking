package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JSONPatchBuilder extends JSONPatchFluent implements VisitableBuilder {
   JSONPatchFluent fluent;

   public JSONPatchBuilder() {
      this(new JSONPatch());
   }

   public JSONPatchBuilder(JSONPatchFluent fluent) {
      this(fluent, new JSONPatch());
   }

   public JSONPatchBuilder(JSONPatchFluent fluent, JSONPatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JSONPatchBuilder(JSONPatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JSONPatch build() {
      JSONPatch buildable = new JSONPatch(this.fluent.getExpression());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
