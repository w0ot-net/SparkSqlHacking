package org.apache.ivy.core.search;

import org.apache.ivy.plugins.resolver.DependencyResolver;

public class ModuleEntry {
   private OrganisationEntry organisationEntry;
   private String module;

   public ModuleEntry(OrganisationEntry org, String name) {
      this.organisationEntry = org;
      this.module = name;
   }

   public String getOrganisation() {
      return this.organisationEntry.getOrganisation();
   }

   public DependencyResolver getResolver() {
      return this.organisationEntry.getResolver();
   }

   public String getModule() {
      return this.module;
   }

   public OrganisationEntry getOrganisationEntry() {
      return this.organisationEntry;
   }

   public String toString() {
      return this.organisationEntry + "#" + this.module;
   }
}
