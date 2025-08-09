package org.apache.ivy.ant;

import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.DefaultExcludeRule;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ExtendsDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolveProcessException;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.filter.FilterHelper;
import org.apache.tools.ant.BuildException;

public class IvyResolve extends IvyTask {
   private File file = null;
   private String conf = null;
   private String organisation = null;
   private String module = null;
   private String branch = null;
   private String revision = null;
   private String pubdate = null;
   private boolean inline = false;
   private boolean haltOnFailure = true;
   private boolean showProgress = true;
   private boolean useCacheOnly = false;
   private String type = null;
   private boolean transitive = true;
   private boolean refresh = false;
   private boolean changing = false;
   private Boolean keep = null;
   private String failureProperty = null;
   private boolean useOrigin = false;
   private String resolveMode = null;
   private String resolveId = null;
   private String log = "default";
   private boolean checkIfChanged = true;
   private List dependencies = new ArrayList();
   private List excludes = new ArrayList();
   private List conflicts = new ArrayList();

   public boolean isUseOrigin() {
      return this.useOrigin;
   }

   public void setUseOrigin(boolean useOrigin) {
      this.useOrigin = useOrigin;
   }

   public String getDate() {
      return this.pubdate;
   }

   public void setDate(String pubdate) {
      this.pubdate = pubdate;
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public void setCache(File cache) {
      this.cacheAttributeNotSupported();
   }

   public String getConf() {
      return this.conf;
   }

   public void setConf(String conf) {
      this.conf = conf;
   }

   public File getFile() {
      return this.file;
   }

   public void setFile(File file) {
      this.file = file;
   }

   public boolean isHaltonfailure() {
      return this.haltOnFailure;
   }

   public void setHaltonfailure(boolean haltOnFailure) {
      this.haltOnFailure = haltOnFailure;
   }

   public void setShowprogress(boolean show) {
      this.showProgress = show;
   }

   public boolean isUseCacheOnly() {
      return this.useCacheOnly;
   }

   public void setUseCacheOnly(boolean useCacheOnly) {
      this.useCacheOnly = useCacheOnly;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public boolean isRefresh() {
      return this.refresh;
   }

   public void setRefresh(boolean refresh) {
      this.refresh = refresh;
   }

   public String getLog() {
      return this.log;
   }

   public void setLog(String log) {
      this.log = log;
   }

   /** @deprecated */
   @Deprecated
   public void setFailurePropery(String failureProperty) {
      this.log("The 'failurepropery' attribute is deprecated. Please use the 'failureproperty' attribute instead", 1);
      this.setFailureProperty(failureProperty);
   }

   public void setFailureProperty(String failureProperty) {
      this.failureProperty = failureProperty;
   }

   public String getFailureProperty() {
      return this.failureProperty;
   }

   public IvyDependency createDependency() {
      IvyDependency dep = new IvyDependency();
      this.dependencies.add(dep);
      return dep;
   }

   public IvyExclude createExclude() {
      IvyExclude ex = new IvyExclude();
      this.excludes.add(ex);
      return ex;
   }

   public IvyConflict createConflict() {
      IvyConflict c = new IvyConflict();
      this.conflicts.add(c);
      return c;
   }

   protected void prepareTask() {
      super.prepareTask();
      Message.setShowProgress(this.showProgress);
   }

   public void doExecute() throws BuildException {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();

      try {
         this.conf = this.getProperty(this.conf, settings, "ivy.configurations");
         this.type = this.getProperty(this.type, settings, "ivy.resolve.default.type.filter");
         String[] confs = StringUtils.splitToArray(this.conf);
         boolean childs = !this.dependencies.isEmpty() || !this.excludes.isEmpty() || !this.conflicts.isEmpty();
         ResolveReport report;
         if (childs) {
            if (this.isInline()) {
               throw new BuildException("the inline mode is incompatible with child elements");
            }

            if (this.organisation != null) {
               throw new BuildException("'organisation' is not allowed with child elements");
            }

            if (this.module != null) {
               throw new BuildException("'module' is not allowed with child elements");
            }

            if (this.file != null) {
               throw new BuildException("'file' not allowed with child elements");
            }

            if (!this.getAllowedLogOptions().contains(this.log)) {
               throw new BuildException("invalid option for 'log': " + this.log + ". Available options are " + this.getAllowedLogOptions());
            }

            ModuleRevisionId mrid = ModuleRevisionId.newInstance("", "", Ivy.getWorkingRevision());
            DefaultModuleDescriptor md = DefaultModuleDescriptor.newBasicInstance(mrid, (Date)null);

            for(IvyDependency dep : this.dependencies) {
               DependencyDescriptor dd = dep.asDependencyDescriptor(md, "default", settings);
               md.addDependency(dd);
            }

            for(IvyExclude exclude : this.excludes) {
               DefaultExcludeRule rule = exclude.asRule(settings);
               rule.addConfiguration("default");
               md.addExcludeRule(rule);
            }

            for(IvyConflict conflict : this.conflicts) {
               conflict.addConflict(md, settings);
            }

            report = ivy.resolve((ModuleDescriptor)md, this.getResolveOptions(ivy, new String[]{"default"}, settings));
         } else if (this.isInline()) {
            if (this.organisation == null) {
               throw new BuildException("'organisation' is required when using inline mode");
            }

            if (this.module == null) {
               throw new BuildException("'module' is required when using inline mode");
            }

            if (this.file != null) {
               throw new BuildException("'file' not allowed when using inline mode");
            }

            if (!this.getAllowedLogOptions().contains(this.log)) {
               throw new BuildException("invalid option for 'log': " + this.log + ". Available options are " + this.getAllowedLogOptions());
            }

            for(int i = 0; i < confs.length; ++i) {
               if ("*".equals(confs[i])) {
                  confs[i] = "*(public)";
               }
            }

            if (this.revision == null) {
               this.revision = "latest.integration";
            }

            report = ivy.resolve(ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision), this.getResolveOptions(ivy, confs, settings), this.changing);
         } else {
            if (this.organisation != null) {
               throw new BuildException("'organisation' not allowed when not using 'org' attribute");
            }

            if (this.module != null) {
               throw new BuildException("'module' not allowed when not using 'org' attribute");
            }

            if (this.file == null) {
               this.file = this.getProject().resolveFile(this.getProperty(settings, "ivy.dep.file"));
            }

            report = ivy.resolve(this.file.toURI().toURL(), this.getResolveOptions(ivy, confs, settings));
         }

         if (report.hasError()) {
            if (this.failureProperty != null) {
               this.getProject().setProperty(this.failureProperty, "true");
            }

            if (this.isHaltonfailure()) {
               throw new BuildException("resolve failed - see output for details");
            }
         }

         this.setResolved(report, this.resolveId, this.isKeep());
         confs = report.getConfigurations();
         if (this.isKeep()) {
            ModuleDescriptor md = report.getModuleDescriptor();
            String mdOrg = md.getModuleRevisionId().getOrganisation();
            String mdName = md.getModuleRevisionId().getName();
            String mdRev = md.getResolvedModuleRevisionId().getRevision();
            this.getProject().setProperty("ivy.organisation", mdOrg);
            settings.setVariable("ivy.organisation", mdOrg);
            this.getProject().setProperty("ivy.module", mdName);
            settings.setVariable("ivy.module", mdName);
            this.getProject().setProperty("ivy.revision", mdRev);
            settings.setVariable("ivy.revision", mdRev);
            List<ExtendsDescriptor> parents = Arrays.asList(md.getInheritedDescriptors());

            for(ExtendsDescriptor parent : parents) {
               int i = parents.indexOf(parent);
               String parentOrg = parent.getResolvedParentRevisionId().getOrganisation();
               String parentModule = parent.getResolvedParentRevisionId().getName();
               String parentRevision = parent.getResolvedParentRevisionId().getRevision();
               String parentBranch = parent.getResolvedParentRevisionId().getBranch();
               this.getProject().setProperty("ivy.parent[" + i + "].organisation", parentOrg);
               settings.setVariable("ivy.parent[" + i + "].organisation", parentOrg);
               this.getProject().setProperty("ivy.parent[" + i + "].module", parentModule);
               settings.setVariable("ivy.parent[" + i + "].module", parentModule);
               this.getProject().setProperty("ivy.parent[" + i + "].revision", parentRevision);
               settings.setVariable("ivy.parent[" + i + "].revision", parentRevision);
               if (parentBranch != null) {
                  this.getProject().setProperty("ivy.parent[" + i + "].branch", parentBranch);
                  settings.setVariable("ivy.parent[" + i + "].branch", parentBranch);
               }
            }

            this.getProject().setProperty("ivy.parents.count", String.valueOf(md.getInheritedDescriptors().length));
            settings.setVariable("ivy.parents.count", String.valueOf(md.getInheritedDescriptors().length));
            Boolean hasChanged = null;
            if (this.getCheckIfChanged()) {
               hasChanged = report.hasChanged();
               this.getProject().setProperty("ivy.deps.changed", hasChanged.toString());
               settings.setVariable("ivy.deps.changed", hasChanged.toString());
            }

            this.getProject().setProperty("ivy.resolved.configurations", this.mergeConfs(confs));
            settings.setVariable("ivy.resolved.configurations", this.mergeConfs(confs));
            if (this.file != null) {
               this.getProject().setProperty("ivy.resolved.file", this.file.getAbsolutePath());
               settings.setVariable("ivy.resolved.file", this.file.getAbsolutePath());
            }

            if (this.resolveId != null) {
               this.getProject().setProperty("ivy.organisation." + this.resolveId, mdOrg);
               settings.setVariable("ivy.organisation." + this.resolveId, mdOrg);
               this.getProject().setProperty("ivy.module." + this.resolveId, mdName);
               settings.setVariable("ivy.module." + this.resolveId, mdName);
               this.getProject().setProperty("ivy.revision." + this.resolveId, mdRev);
               settings.setVariable("ivy.revision." + this.resolveId, mdRev);
               if (this.getCheckIfChanged()) {
                  this.getProject().setProperty("ivy.deps.changed." + this.resolveId, hasChanged.toString());
                  settings.setVariable("ivy.deps.changed." + this.resolveId, hasChanged.toString());
               }

               this.getProject().setProperty("ivy.resolved.configurations." + this.resolveId, this.mergeConfs(confs));
               settings.setVariable("ivy.resolved.configurations." + this.resolveId, this.mergeConfs(confs));
               if (this.file != null) {
                  this.getProject().setProperty("ivy.resolved.file." + this.resolveId, this.file.getAbsolutePath());
                  settings.setVariable("ivy.resolved.file." + this.resolveId, this.file.getAbsolutePath());
               }
            }
         }

      } catch (MalformedURLException e) {
         throw new BuildException("unable to convert given ivy file to url: " + this.file + ": " + e, e);
      } catch (ParseException e) {
         this.log(e.getMessage(), 0);
         throw new BuildException("syntax errors in ivy file: " + e, e);
      } catch (ResolveProcessException e) {
         throw new BuildException("impossible to resolve dependencies:\n\t" + e.getMessage(), e);
      } catch (Exception e) {
         throw new BuildException("impossible to resolve dependencies:\n\t" + e, e);
      }
   }

   protected Collection getAllowedLogOptions() {
      return Arrays.asList("default", "download-only", "quiet");
   }

   private ResolveOptions getResolveOptions(Ivy ivy, String[] confs, IvySettings settings) {
      if (this.useOrigin) {
         settings.useDeprecatedUseOrigin();
      }

      return ((ResolveOptions)(new ResolveOptions()).setLog(this.log)).setConfs(confs).setValidate(this.doValidate(settings)).setArtifactFilter(FilterHelper.getArtifactTypeFilter(this.type)).setRevision(this.revision).setDate(getPubDate(this.pubdate, (Date)null)).setUseCacheOnly(this.useCacheOnly).setRefresh(this.refresh).setTransitive(this.transitive).setResolveMode(this.resolveMode).setResolveId(this.resolveId).setCheckIfChanged(this.checkIfChanged);
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

   public boolean isTransitive() {
      return this.transitive;
   }

   public void setTransitive(boolean transitive) {
      this.transitive = transitive;
   }

   public boolean isChanging() {
      return this.changing;
   }

   public void setChanging(boolean changing) {
      this.changing = changing;
   }

   public boolean isKeep() {
      return this.keep == null ? this.organisation == null : this.keep;
   }

   public void setKeep(boolean keep) {
      this.keep = keep;
   }

   public boolean isInline() {
      return this.inline;
   }

   public void setInline(boolean inline) {
      this.inline = inline;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   public void setResolveId(String resolveId) {
      this.resolveId = resolveId;
   }

   public String getResolveMode() {
      return this.resolveMode;
   }

   public void setResolveMode(String resolveMode) {
      this.resolveMode = resolveMode;
   }

   public boolean getCheckIfChanged() {
      return this.checkIfChanged;
   }

   public void setCheckIfChanged(boolean checkIfChanged) {
      this.checkIfChanged = checkIfChanged;
   }
}
