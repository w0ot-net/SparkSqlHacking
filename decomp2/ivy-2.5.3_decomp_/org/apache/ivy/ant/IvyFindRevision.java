package org.apache.ivy.ant;

import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.tools.ant.BuildException;

public class IvyFindRevision extends IvyTask {
   private String organisation;
   private String module;
   private String branch;
   private String revision;
   private String property = "ivy.revision";

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

   public String getProperty() {
      return this.property;
   }

   public void setProperty(String prefix) {
      this.property = prefix;
   }

   public void doExecute() throws BuildException {
      if (this.organisation == null) {
         throw new BuildException("no organisation provided for ivy findrevision task");
      } else if (this.module == null) {
         throw new BuildException("no module name provided for ivy findrevision task");
      } else if (this.revision == null) {
         throw new BuildException("no revision provided for ivy findrevision task");
      } else {
         Ivy ivy = this.getIvyInstance();
         IvySettings settings = ivy.getSettings();
         if (this.branch == null) {
            this.branch = settings.getDefaultBranch(new ModuleId(this.organisation, this.module));
         }

         ResolvedModuleRevision rmr = ivy.findModule(ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision));
         if (rmr != null) {
            this.getProject().setProperty(this.property, rmr.getId().getRevision());
         }

      }
   }
}
