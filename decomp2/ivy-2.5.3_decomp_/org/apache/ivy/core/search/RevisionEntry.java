package org.apache.ivy.core.search;

import org.apache.ivy.plugins.resolver.DependencyResolver;

public class RevisionEntry {
   private ModuleEntry moduleEntry;
   private String revision;

   public RevisionEntry(ModuleEntry mod, String name) {
      this.moduleEntry = mod;
      this.revision = name;
   }

   public ModuleEntry getModuleEntry() {
      return this.moduleEntry;
   }

   public String getRevision() {
      return this.revision;
   }

   public String getModule() {
      return this.moduleEntry.getModule();
   }

   public String getOrganisation() {
      return this.moduleEntry.getOrganisation();
   }

   public OrganisationEntry getOrganisationEntry() {
      return this.moduleEntry.getOrganisationEntry();
   }

   public DependencyResolver getResolver() {
      return this.moduleEntry.getResolver();
   }

   public String toString() {
      return this.moduleEntry + ";" + this.revision;
   }
}
