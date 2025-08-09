package org.apache.ivy.ant;

import org.apache.ivy.core.module.id.ModuleRevisionId;

public class PackageMapping {
   private String pkg;
   private String organisation;
   private String module;
   private String revision;

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

   public String getPackage() {
      return this.pkg;
   }

   public void setPackage(String packageName) {
      this.pkg = packageName;
   }

   public ModuleRevisionId getModuleRevisionId() {
      return ModuleRevisionId.newInstance(this.organisation, this.module, this.revision);
   }
}
