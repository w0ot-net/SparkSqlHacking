package org.apache.ivy.ant;

import java.io.File;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.install.InstallOptions;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.util.filter.FilterHelper;
import org.apache.tools.ant.BuildException;

public class IvyInstall extends IvyTask {
   private String organisation;
   private String module;
   private String revision;
   private String branch;
   private String conf = "*";
   private boolean overwrite = false;
   private String from;
   private String to;
   private boolean transitive;
   private String type;
   private String matcher = "exact";
   private boolean haltOnFailure = true;
   private boolean installOriginalMetadata = false;

   public void doExecute() throws BuildException {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();
      if (this.organisation == null) {
         throw new BuildException("no organisation provided for ivy publish task: It can either be set explicitly via the attribute 'organisation' or via 'ivy.organisation' property or a prior call to <resolve/>");
      } else {
         if (this.module == null) {
            if ("exact".equals(this.matcher)) {
               throw new BuildException("no module name provided for ivy publish task: It can either be set explicitly via the attribute 'module' or via 'ivy.module' property or a prior call to <resolve/>");
            }

            this.module = "*";
         }

         if (this.revision == null) {
            if ("exact".equals(this.matcher)) {
               throw new BuildException("no module revision provided for ivy publish task: It can either be set explicitly via the attribute 'revision' or via 'ivy.revision' property or a prior call to <resolve/>");
            }

            this.revision = "*";
         }

         if (this.branch == null) {
            if ("exact".equals(this.matcher)) {
               this.branch = settings.getDefaultBranch(ModuleId.newInstance(this.organisation, this.module));
            } else {
               this.branch = "*";
            }
         }

         if (this.from == null) {
            throw new BuildException("no from resolver name: please provide it through parameter 'from'");
         } else if (this.to == null) {
            throw new BuildException("no to resolver name: please provide it through parameter 'to'");
         } else {
            ModuleRevisionId mrid = ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision);

            ResolveReport report;
            try {
               report = ivy.install(mrid, this.from, this.to, (new InstallOptions()).setTransitive(this.transitive).setValidate(this.doValidate(settings)).setOverwrite(this.overwrite).setConfs(this.conf.split(",")).setArtifactFilter(FilterHelper.getArtifactTypeFilter(this.type)).setMatcherName(this.matcher).setInstallOriginalMetadata(this.installOriginalMetadata));
            } catch (Exception e) {
               throw new BuildException("impossible to install " + mrid + ": " + e, e);
            }

            if (report.hasError() && this.isHaltonfailure()) {
               throw new BuildException("Problem happened while installing modules - see output for details");
            }
         }
      }
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

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String organisation) {
      this.organisation = organisation;
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public boolean isOverwrite() {
      return this.overwrite;
   }

   public void setOverwrite(boolean overwrite) {
      this.overwrite = overwrite;
   }

   public String getFrom() {
      return this.from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getTo() {
      return this.to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   public boolean isTransitive() {
      return this.transitive;
   }

   public void setTransitive(boolean transitive) {
      this.transitive = transitive;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getMatcher() {
      return this.matcher;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
   }

   public String getConf() {
      return this.conf;
   }

   public void setConf(String conf) {
      this.conf = conf;
   }

   public boolean isInstallOriginalMetadata() {
      return this.installOriginalMetadata;
   }

   public void setInstallOriginalMetadata(boolean installOriginalMetadata) {
      this.installOriginalMetadata = installOriginalMetadata;
   }
}
