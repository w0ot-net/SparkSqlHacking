package org.apache.ivy.core.search;

import org.apache.ivy.plugins.resolver.DependencyResolver;

public class OrganisationEntry {
   private DependencyResolver resolver;
   private String organisation;

   public OrganisationEntry(DependencyResolver resolver, String organisation) {
      this.resolver = resolver;
      this.organisation = organisation;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public DependencyResolver getResolver() {
      return this.resolver;
   }

   public String toString() {
      return this.organisation;
   }
}
