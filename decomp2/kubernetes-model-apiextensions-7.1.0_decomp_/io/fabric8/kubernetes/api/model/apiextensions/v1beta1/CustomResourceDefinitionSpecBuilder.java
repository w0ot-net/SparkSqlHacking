package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceDefinitionSpecBuilder extends CustomResourceDefinitionSpecFluent implements VisitableBuilder {
   CustomResourceDefinitionSpecFluent fluent;

   public CustomResourceDefinitionSpecBuilder() {
      this(new CustomResourceDefinitionSpec());
   }

   public CustomResourceDefinitionSpecBuilder(CustomResourceDefinitionSpecFluent fluent) {
      this(fluent, new CustomResourceDefinitionSpec());
   }

   public CustomResourceDefinitionSpecBuilder(CustomResourceDefinitionSpecFluent fluent, CustomResourceDefinitionSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceDefinitionSpecBuilder(CustomResourceDefinitionSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceDefinitionSpec build() {
      CustomResourceDefinitionSpec buildable = new CustomResourceDefinitionSpec(this.fluent.buildAdditionalPrinterColumns(), this.fluent.buildConversion(), this.fluent.getGroup(), this.fluent.buildNames(), this.fluent.getPreserveUnknownFields(), this.fluent.getScope(), this.fluent.buildSubresources(), this.fluent.buildValidation(), this.fluent.getVersion(), this.fluent.buildVersions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
