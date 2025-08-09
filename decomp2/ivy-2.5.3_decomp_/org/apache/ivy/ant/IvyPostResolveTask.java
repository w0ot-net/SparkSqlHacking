package org.apache.ivy.ant;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.FilterHelper;
import org.apache.tools.ant.BuildException;

public abstract class IvyPostResolveTask extends IvyTask {
   private String conf;
   private boolean haltOnFailure = true;
   private boolean transitive = true;
   private boolean inline = false;
   private String organisation;
   private String branch = null;
   private String module;
   private String revision = "latest.integration";
   private String resolveId;
   private String type;
   private File file;
   private Filter artifactFilter = null;
   private boolean useOrigin = false;
   private Boolean keep = null;
   private boolean refresh = false;
   private String resolveMode = null;
   private String log = "default";
   private boolean changing = false;
   private IvyResolve resolve = new IvyResolve();

   public boolean isUseOrigin() {
      return this.useOrigin;
   }

   public void setUseOrigin(boolean useOrigin) {
      this.useOrigin = useOrigin;
   }

   public String getLog() {
      return this.log;
   }

   public void setLog(String log) {
      this.log = log;
   }

   public IvyDependency createDependency() {
      return this.resolve.createDependency();
   }

   public IvyExclude createExclude() {
      return this.resolve.createExclude();
   }

   public IvyConflict createConflict() {
      return this.resolve.createConflict();
   }

   protected void prepareAndCheck() {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();
      boolean orgAndModSetManually = this.organisation != null && this.module != null;
      this.organisation = this.getProperty(this.organisation, settings, "ivy.organisation");
      this.module = this.getProperty(this.module, settings, "ivy.module");
      if (this.file == null) {
         String fileName = this.getProperty(settings, "ivy.resolved.file", this.resolveId);
         if (fileName != null) {
            this.file = this.getProject().resolveFile(fileName);
         }
      }

      if (this.isInline()) {
         if (this.conf == null) {
            this.conf = "*";
         }

         if (this.organisation == null) {
            throw new BuildException("no organisation provided for ivy cache task in inline mode: It can either be set explicitly via the attribute 'organisation' or via 'ivy.organisation' property");
         }

         if (this.module == null) {
            throw new BuildException("no module name provided for ivy cache task in inline mode: It can either be set explicitly via the attribute 'module' or via 'ivy.module' property");
         }

         String[] toResolve = this.getConfsToResolve(this.getOrganisation(), this.getModule() + "-caller", this.conf, true);

         for(int i = 0; i < toResolve.length; ++i) {
            if ("*".equals(toResolve[i])) {
               toResolve[i] = "*(public)";
            }
         }

         if (toResolve.length > 0) {
            Message.verbose(String.format("using inline mode to resolve %s %s %s (%s)", this.getOrganisation(), this.getModule(), this.getRevision(), StringUtils.joinArray(toResolve, ", ")));
            IvyResolve resolve = this.setupResolve(this.isHaltonfailure(), this.isUseOrigin());
            resolve.setOrganisation(this.getOrganisation());
            resolve.setModule(this.getModule());
            resolve.setBranch(this.getBranch());
            resolve.setRevision(this.getRevision());
            resolve.setInline(true);
            resolve.setChanging(this.isChanging());
            resolve.setConf(this.conf);
            resolve.setResolveId(this.resolveId);
            resolve.setTransitive(this.isTransitive());
            resolve.execute();
         } else {
            Message.verbose(String.format("inline resolve already done for %s %s %s (%s)", this.getOrganisation(), this.getModule(), this.getRevision(), this.conf));
         }

         if ("*".equals(this.conf)) {
            this.conf = StringUtils.joinArray(this.getResolvedConfigurations(this.getOrganisation(), this.getModule() + "-caller", true), ", ");
         }
      } else {
         Message.debug("using standard ensure resolved");
         if (!orgAndModSetManually) {
            this.ensureResolved(settings);
         }

         this.conf = this.getProperty(this.conf, settings, "ivy.resolved.configurations");
         if ("*".equals(this.conf)) {
            this.conf = this.getProperty(settings, "ivy.resolved.configurations");
            if (this.conf == null) {
               throw new BuildException("bad conf provided for ivy cache task: '*' can only be used with a prior call to <resolve/>");
            }
         }
      }

      this.organisation = this.getProperty(this.organisation, settings, "ivy.organisation");
      this.module = this.getProperty(this.module, settings, "ivy.module");
      if (this.organisation == null) {
         throw new BuildException("no organisation provided for ivy cache task: It can either be set explicitly via the attribute 'organisation' or via 'ivy.organisation' property or a prior call to <resolve/>");
      } else if (this.module == null) {
         throw new BuildException("no module name provided for ivy cache task: It can either be set explicitly via the attribute 'module' or via 'ivy.module' property or a prior call to <resolve/>");
      } else if (this.conf == null) {
         throw new BuildException("no conf provided for ivy cache task: It can either be set explicitly via the attribute 'conf' or via 'ivy.resolved.configurations' property or a prior call to <resolve/>");
      } else {
         this.artifactFilter = FilterHelper.getArtifactTypeFilter(this.type);
      }
   }

   protected void ensureResolved(IvySettings settings) {
      String requestedConfigs = this.getProperty(this.getConf(), settings, "ivy.resolved.configurations");
      String[] confs = this.getResolveId() == null ? this.getConfsToResolve(this.getOrganisation(), this.getModule(), requestedConfigs, false) : this.getConfsToResolve(this.getResolveId(), requestedConfigs);
      if (confs.length > 0) {
         IvyResolve resolve = this.setupResolve(this.isHaltonfailure(), this.isUseOrigin());
         resolve.setFile(this.getFile());
         resolve.setTransitive(this.isTransitive());
         resolve.setConf(StringUtils.joinArray(confs, ", "));
         resolve.setResolveId(this.getResolveId());
         resolve.execute();
      }

   }

   protected String[] getConfsToResolve(String org, String module, String conf, boolean strict) {
      ModuleDescriptor reference = (ModuleDescriptor)this.getResolvedDescriptor(org, module, strict);
      String[] rconfs = this.getResolvedConfigurations(org, module, strict);
      return this.getConfsToResolve(reference, conf, rconfs);
   }

   protected String[] getConfsToResolve(String resolveId, String conf) {
      ModuleDescriptor reference = (ModuleDescriptor)this.getResolvedDescriptor(resolveId, false);
      if (reference == null) {
         return conf == null ? new String[]{"*"} : StringUtils.splitToArray(conf);
      } else {
         String[] rconfs = (String[])this.getProject().getReference("ivy.resolved.configurations.ref." + resolveId);
         return this.getConfsToResolve(reference, conf, rconfs);
      }
   }

   private String[] getConfsToResolve(ModuleDescriptor reference, String conf, String[] rconfs) {
      Message.debug("calculating configurations to resolve");
      if (reference == null) {
         Message.debug("module not yet resolved, all confs still need to be resolved");
         return conf == null ? new String[]{"*"} : StringUtils.splitToArray(conf);
      } else if (conf == null) {
         Message.debug("module already resolved, no configuration to resolve");
         return new String[0];
      } else {
         String[] confs;
         if ("*".equals(conf)) {
            confs = reference.getConfigurationsNames();
         } else {
            confs = StringUtils.splitToArray(conf);
         }

         Set<String> rconfsSet = new HashSet();
         ResolutionCacheManager cache = this.getSettings().getResolutionCacheManager();

         for(String resolvedConf : rconfs) {
            String resolveId = this.getResolveId();
            if (resolveId == null) {
               resolveId = ResolveOptions.getDefaultResolveId(reference);
            }

            File report = cache.getConfigurationResolveReportInCache(resolveId, resolvedConf);
            if (report.exists()) {
               rconfsSet.add(resolvedConf);
            }
         }

         Set<String> confsSet = new HashSet(Arrays.asList(confs));
         Message.debug("resolved configurations:   " + rconfsSet);
         Message.debug("asked configurations:      " + confsSet);
         confsSet.removeAll(rconfsSet);
         Message.debug("to resolve configurations: " + confsSet);
         return (String[])confsSet.toArray(new String[confsSet.size()]);
      }
   }

   protected IvyResolve setupResolve(boolean haltOnFailure, boolean useOrigin) {
      Message.verbose("no resolved descriptor found: launching default resolve");
      this.resolve.setTaskName(this.getTaskName());
      this.resolve.setProject(this.getProject());
      this.resolve.setHaltonfailure(haltOnFailure);
      this.resolve.setUseOrigin(useOrigin);
      this.resolve.setValidate(this.doValidate(this.getSettings()));
      this.resolve.setKeep(this.isKeep());
      this.resolve.setRefresh(this.isRefresh());
      this.resolve.setLog(this.getLog());
      this.resolve.setSettingsRef(this.getSettingsRef());
      this.resolve.setResolveMode(this.getResolveMode());
      return this.resolve;
   }

   protected ModuleRevisionId getResolvedMrid() {
      return new ModuleRevisionId(this.getResolvedModuleId(), this.getRevision() == null ? Ivy.getWorkingRevision() : this.getRevision());
   }

   protected ModuleId getResolvedModuleId() {
      return this.isInline() ? new ModuleId(this.getOrganisation(), this.getModule() + "-caller") : new ModuleId(this.getOrganisation(), this.getModule());
   }

   protected ResolveReport getResolvedReport() {
      return this.getResolvedReport(this.getOrganisation(), this.isInline() ? this.getModule() + "-caller" : this.getModule(), this.resolveId);
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getConf() {
      return this.conf;
   }

   public void setConf(String conf) {
      this.conf = conf;
   }

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String organisation) {
      this.organisation = organisation;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public boolean isHaltonfailure() {
      return this.haltOnFailure;
   }

   public void setHaltonfailure(boolean haltOnFailure) {
      this.haltOnFailure = haltOnFailure;
   }

   public void setCache(File cache) {
      this.cacheAttributeNotSupported();
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String rev) {
      this.revision = rev;
   }

   public Filter getArtifactFilter() {
      return this.artifactFilter;
   }

   public boolean isTransitive() {
      return this.transitive;
   }

   public void setTransitive(boolean transitive) {
      this.transitive = transitive;
   }

   public boolean isInline() {
      return this.inline;
   }

   public void setInline(boolean inline) {
      this.inline = inline;
   }

   public void setResolveId(String resolveId) {
      this.resolveId = resolveId;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   public void setFile(File file) {
      this.file = file;
   }

   public File getFile() {
      return this.file;
   }

   public void setKeep(boolean keep) {
      this.keep = keep;
   }

   public boolean isKeep() {
      return this.keep == null ? !this.isInline() : this.keep;
   }

   public void setChanging(boolean changing) {
      this.changing = changing;
   }

   public boolean isChanging() {
      return this.changing;
   }

   public void setRefresh(boolean refresh) {
      this.refresh = refresh;
   }

   public boolean isRefresh() {
      return this.refresh;
   }

   public String getResolveMode() {
      return this.resolveMode;
   }

   public void setResolveMode(String resolveMode) {
      this.resolveMode = resolveMode;
   }
}
