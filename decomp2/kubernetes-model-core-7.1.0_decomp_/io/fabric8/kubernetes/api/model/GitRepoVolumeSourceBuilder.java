package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GitRepoVolumeSourceBuilder extends GitRepoVolumeSourceFluent implements VisitableBuilder {
   GitRepoVolumeSourceFluent fluent;

   public GitRepoVolumeSourceBuilder() {
      this(new GitRepoVolumeSource());
   }

   public GitRepoVolumeSourceBuilder(GitRepoVolumeSourceFluent fluent) {
      this(fluent, new GitRepoVolumeSource());
   }

   public GitRepoVolumeSourceBuilder(GitRepoVolumeSourceFluent fluent, GitRepoVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GitRepoVolumeSourceBuilder(GitRepoVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GitRepoVolumeSource build() {
      GitRepoVolumeSource buildable = new GitRepoVolumeSource(this.fluent.getDirectory(), this.fluent.getRepository(), this.fluent.getRevision());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
