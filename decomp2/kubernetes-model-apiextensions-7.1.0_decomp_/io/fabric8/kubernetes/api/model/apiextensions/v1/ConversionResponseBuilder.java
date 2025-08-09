package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ConversionResponseBuilder extends ConversionResponseFluent implements VisitableBuilder {
   ConversionResponseFluent fluent;

   public ConversionResponseBuilder() {
      this(new ConversionResponse());
   }

   public ConversionResponseBuilder(ConversionResponseFluent fluent) {
      this(fluent, new ConversionResponse());
   }

   public ConversionResponseBuilder(ConversionResponseFluent fluent, ConversionResponse instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ConversionResponseBuilder(ConversionResponse instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ConversionResponse build() {
      ConversionResponse buildable = new ConversionResponse(this.fluent.getConvertedObjects(), this.fluent.getResult(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
