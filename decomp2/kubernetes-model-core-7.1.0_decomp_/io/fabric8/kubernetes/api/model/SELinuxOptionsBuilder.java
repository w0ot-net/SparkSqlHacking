package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SELinuxOptionsBuilder extends SELinuxOptionsFluent implements VisitableBuilder {
   SELinuxOptionsFluent fluent;

   public SELinuxOptionsBuilder() {
      this(new SELinuxOptions());
   }

   public SELinuxOptionsBuilder(SELinuxOptionsFluent fluent) {
      this(fluent, new SELinuxOptions());
   }

   public SELinuxOptionsBuilder(SELinuxOptionsFluent fluent, SELinuxOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SELinuxOptionsBuilder(SELinuxOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SELinuxOptions build() {
      SELinuxOptions buildable = new SELinuxOptions(this.fluent.getLevel(), this.fluent.getRole(), this.fluent.getType(), this.fluent.getUser());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
