package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ParamRefBuilder extends ParamRefFluent implements VisitableBuilder {
   ParamRefFluent fluent;

   public ParamRefBuilder() {
      this(new ParamRef());
   }

   public ParamRefBuilder(ParamRefFluent fluent) {
      this(fluent, new ParamRef());
   }

   public ParamRefBuilder(ParamRefFluent fluent, ParamRef instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ParamRefBuilder(ParamRef instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ParamRef build() {
      ParamRef buildable = new ParamRef(this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getParameterNotFoundAction(), this.fluent.buildSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
