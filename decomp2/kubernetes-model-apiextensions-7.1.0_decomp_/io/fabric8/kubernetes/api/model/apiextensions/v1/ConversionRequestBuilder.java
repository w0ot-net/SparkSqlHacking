package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConversionRequestBuilder extends ConversionRequestFluent implements VisitableBuilder {
   ConversionRequestFluent fluent;

   public ConversionRequestBuilder() {
      this(new ConversionRequest());
   }

   public ConversionRequestBuilder(ConversionRequestFluent fluent) {
      this(fluent, new ConversionRequest());
   }

   public ConversionRequestBuilder(ConversionRequestFluent fluent, ConversionRequest instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConversionRequestBuilder(ConversionRequest instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConversionRequest build() {
      ConversionRequest buildable = new ConversionRequest(this.fluent.getDesiredAPIVersion(), this.fluent.getObjects(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
