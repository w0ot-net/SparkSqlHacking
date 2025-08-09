package org.apache.ivy.core.install;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.FilterHelper;

public class InstallOptions {
   private boolean transitive = true;
   private boolean validate = true;
   private boolean overwrite = false;
   private boolean installOriginalMetadata = false;
   private String[] confs = new String[]{"*"};
   private Filter artifactFilter;
   private String matcherName;

   public InstallOptions() {
      this.artifactFilter = FilterHelper.NO_FILTER;
      this.matcherName = "exact";
   }

   public boolean isTransitive() {
      return this.transitive;
   }

   public InstallOptions setTransitive(boolean transitive) {
      this.transitive = transitive;
      return this;
   }

   public boolean isValidate() {
      return this.validate;
   }

   public InstallOptions setValidate(boolean validate) {
      this.validate = validate;
      return this;
   }

   public boolean isOverwrite() {
      return this.overwrite;
   }

   public InstallOptions setOverwrite(boolean overwrite) {
      this.overwrite = overwrite;
      return this;
   }

   public Filter getArtifactFilter() {
      return this.artifactFilter;
   }

   public InstallOptions setArtifactFilter(Filter artifactFilter) {
      this.artifactFilter = artifactFilter == null ? FilterHelper.NO_FILTER : artifactFilter;
      return this;
   }

   public String getMatcherName() {
      return this.matcherName;
   }

   public InstallOptions setMatcherName(String matcherName) {
      this.matcherName = matcherName;
      return this;
   }

   public String[] getConfs() {
      return this.confs;
   }

   public InstallOptions setConfs(String[] conf) {
      this.confs = conf;
      return this;
   }

   public boolean isInstallOriginalMetadata() {
      return this.installOriginalMetadata;
   }

   public InstallOptions setInstallOriginalMetadata(boolean installOriginalMetadata) {
      this.installOriginalMetadata = installOriginalMetadata;
      return this;
   }
}
