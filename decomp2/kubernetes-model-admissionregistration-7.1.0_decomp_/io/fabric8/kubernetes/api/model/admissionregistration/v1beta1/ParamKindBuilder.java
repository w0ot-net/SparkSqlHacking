package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ParamKindBuilder extends ParamKindFluent implements VisitableBuilder {
   ParamKindFluent fluent;

   public ParamKindBuilder() {
      this(new ParamKind());
   }

   public ParamKindBuilder(ParamKindFluent fluent) {
      this(fluent, new ParamKind());
   }

   public ParamKindBuilder(ParamKindFluent fluent, ParamKind instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ParamKindBuilder(ParamKind instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ParamKind build() {
      ParamKind buildable = new ParamKind(this.fluent.getApiVersion(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
