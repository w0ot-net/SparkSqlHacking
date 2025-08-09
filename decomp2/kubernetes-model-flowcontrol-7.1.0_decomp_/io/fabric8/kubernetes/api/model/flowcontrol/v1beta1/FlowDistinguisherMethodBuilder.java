package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class FlowDistinguisherMethodBuilder extends FlowDistinguisherMethodFluent implements VisitableBuilder {
   FlowDistinguisherMethodFluent fluent;

   public FlowDistinguisherMethodBuilder() {
      this(new FlowDistinguisherMethod());
   }

   public FlowDistinguisherMethodBuilder(FlowDistinguisherMethodFluent fluent) {
      this(fluent, new FlowDistinguisherMethod());
   }

   public FlowDistinguisherMethodBuilder(FlowDistinguisherMethodFluent fluent, FlowDistinguisherMethod instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public FlowDistinguisherMethodBuilder(FlowDistinguisherMethod instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public FlowDistinguisherMethod build() {
      FlowDistinguisherMethod buildable = new FlowDistinguisherMethod(this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
