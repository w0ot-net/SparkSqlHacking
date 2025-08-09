package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class WindowsSecurityContextOptionsBuilder extends WindowsSecurityContextOptionsFluent implements VisitableBuilder {
   WindowsSecurityContextOptionsFluent fluent;

   public WindowsSecurityContextOptionsBuilder() {
      this(new WindowsSecurityContextOptions());
   }

   public WindowsSecurityContextOptionsBuilder(WindowsSecurityContextOptionsFluent fluent) {
      this(fluent, new WindowsSecurityContextOptions());
   }

   public WindowsSecurityContextOptionsBuilder(WindowsSecurityContextOptionsFluent fluent, WindowsSecurityContextOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public WindowsSecurityContextOptionsBuilder(WindowsSecurityContextOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public WindowsSecurityContextOptions build() {
      WindowsSecurityContextOptions buildable = new WindowsSecurityContextOptions(this.fluent.getGmsaCredentialSpec(), this.fluent.getGmsaCredentialSpecName(), this.fluent.getHostProcess(), this.fluent.getRunAsUserName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
