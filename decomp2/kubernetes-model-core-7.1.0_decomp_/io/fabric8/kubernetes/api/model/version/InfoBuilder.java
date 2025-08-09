package io.fabric8.kubernetes.api.model.version;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class InfoBuilder extends InfoFluent implements VisitableBuilder {
   InfoFluent fluent;

   public InfoBuilder() {
      this(new Info());
   }

   public InfoBuilder(InfoFluent fluent) {
      this(fluent, new Info());
   }

   public InfoBuilder(InfoFluent fluent, Info instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public InfoBuilder(Info instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Info build() {
      Info buildable = new Info(this.fluent.getBuildDate(), this.fluent.getCompiler(), this.fluent.getGitCommit(), this.fluent.getGitTreeState(), this.fluent.getGitVersion(), this.fluent.getGoVersion(), this.fluent.getMajor(), this.fluent.getMinor(), this.fluent.getPlatform());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
