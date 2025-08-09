package org.apache.ivy.core.resolve;

import java.util.Date;
import org.apache.ivy.core.LogOptions;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.util.ConfigurationUtils;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.FilterHelper;

public class ResolveOptions extends LogOptions {
   public static final String RESOLVEMODE_DEFAULT = "default";
   public static final String RESOLVEMODE_DYNAMIC = "dynamic";
   public static final String[] RESOLVEMODES = new String[]{"default", "dynamic"};
   private String[] confs = new String[]{"*"};
   private String revision = null;
   private Date date = null;
   private boolean validate = true;
   private boolean useCacheOnly = false;
   private boolean transitive = true;
   private boolean download = true;
   private boolean outputReport = true;
   private Filter artifactFilter;
   private String resolveMode;
   private String resolveId;
   private boolean refresh;
   private boolean checkIfChanged;

   public ResolveOptions() {
      this.artifactFilter = FilterHelper.NO_FILTER;
      this.checkIfChanged = false;
   }

   public ResolveOptions(ResolveOptions options) {
      super(options);
      this.artifactFilter = FilterHelper.NO_FILTER;
      this.checkIfChanged = false;
      this.confs = options.confs;
      this.revision = options.revision;
      this.date = options.date;
      this.validate = options.validate;
      this.refresh = options.refresh;
      this.useCacheOnly = options.useCacheOnly;
      this.transitive = options.transitive;
      this.download = options.download;
      this.outputReport = options.outputReport;
      this.resolveMode = options.resolveMode;
      this.artifactFilter = options.artifactFilter;
      this.resolveId = options.resolveId;
      this.checkIfChanged = options.checkIfChanged;
   }

   public Filter getArtifactFilter() {
      return this.artifactFilter;
   }

   public ResolveOptions setArtifactFilter(Filter artifactFilter) {
      this.artifactFilter = artifactFilter;
      return this;
   }

   public String getResolveMode() {
      return this.resolveMode;
   }

   public ResolveOptions setResolveMode(String resolveMode) {
      this.resolveMode = resolveMode;
      return this;
   }

   public boolean useSpecialConfs() {
      return this.confs != null && this.confs[0].startsWith("*");
   }

   public String[] getConfs() {
      if (this.useSpecialConfs()) {
         throw new AssertionError("ResolveOptions.getConfs() can not be used for options used special confs.");
      } else {
         return this.confs;
      }
   }

   public String[] getConfs(ModuleDescriptor md) {
      return ConfigurationUtils.replaceWildcards(this.confs, md);
   }

   public ResolveOptions setConfs(String[] confs) {
      this.confs = confs;
      return this;
   }

   public Date getDate() {
      return this.date;
   }

   public ResolveOptions setDate(Date date) {
      this.date = date;
      return this;
   }

   public boolean isDownload() {
      return this.download;
   }

   public ResolveOptions setDownload(boolean download) {
      this.download = download;
      return this;
   }

   public boolean isOutputReport() {
      return this.outputReport;
   }

   public ResolveOptions setOutputReport(boolean outputReport) {
      this.outputReport = outputReport;
      return this;
   }

   public boolean isTransitive() {
      return this.transitive;
   }

   public ResolveOptions setTransitive(boolean transitive) {
      this.transitive = transitive;
      return this;
   }

   public boolean isUseCacheOnly() {
      return this.useCacheOnly;
   }

   public ResolveOptions setUseCacheOnly(boolean useCacheOnly) {
      this.useCacheOnly = useCacheOnly;
      return this;
   }

   public boolean isValidate() {
      return this.validate;
   }

   public ResolveOptions setValidate(boolean validate) {
      this.validate = validate;
      return this;
   }

   public String getRevision() {
      return this.revision;
   }

   public ResolveOptions setRevision(String revision) {
      this.revision = revision;
      return this;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   public ResolveOptions setResolveId(String resolveId) {
      this.resolveId = resolveId;
      return this;
   }

   public ResolveOptions setRefresh(boolean refresh) {
      this.refresh = refresh;
      return this;
   }

   public boolean isRefresh() {
      return this.refresh;
   }

   public ResolveOptions setCheckIfChanged(boolean checkIfChanged) {
      this.checkIfChanged = checkIfChanged;
      return this;
   }

   public boolean getCheckIfChanged() {
      return this.checkIfChanged;
   }

   public static String getDefaultResolveId(ModuleDescriptor md) {
      ModuleId module = md.getModuleRevisionId().getModuleId();
      return getDefaultResolveId(module);
   }

   public static String getDefaultResolveId(ModuleId moduleId) {
      return moduleId.getOrganisation() + "-" + moduleId.getName();
   }
}
