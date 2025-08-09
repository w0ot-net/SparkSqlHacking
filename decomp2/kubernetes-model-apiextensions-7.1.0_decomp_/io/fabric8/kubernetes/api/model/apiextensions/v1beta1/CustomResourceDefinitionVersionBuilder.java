package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CustomResourceDefinitionVersionBuilder extends CustomResourceDefinitionVersionFluent implements VisitableBuilder {
   CustomResourceDefinitionVersionFluent fluent;

   public CustomResourceDefinitionVersionBuilder() {
      this(new CustomResourceDefinitionVersion());
   }

   public CustomResourceDefinitionVersionBuilder(CustomResourceDefinitionVersionFluent fluent) {
      this(fluent, new CustomResourceDefinitionVersion());
   }

   public CustomResourceDefinitionVersionBuilder(CustomResourceDefinitionVersionFluent fluent, CustomResourceDefinitionVersion instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CustomResourceDefinitionVersionBuilder(CustomResourceDefinitionVersion instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CustomResourceDefinitionVersion build() {
      CustomResourceDefinitionVersion buildable = new CustomResourceDefinitionVersion(this.fluent.buildAdditionalPrinterColumns(), this.fluent.getDeprecated(), this.fluent.getDeprecationWarning(), this.fluent.getName(), this.fluent.buildSchema(), this.fluent.getServed(), this.fluent.getStorage(), this.fluent.buildSubresources());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
