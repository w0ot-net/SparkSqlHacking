package org.apache.ivy.core.module.descriptor;

import org.apache.ivy.core.module.id.ModuleRevisionId;

public class OverrideDependencyDescriptorMediator implements DependencyDescriptorMediator {
   private String version;
   private String branch;

   public OverrideDependencyDescriptorMediator(String branch, String version) {
      this.branch = branch;
      this.version = version;
   }

   public String getVersion() {
      return this.version;
   }

   public String getBranch() {
      return this.branch;
   }

   public DependencyDescriptor mediate(DependencyDescriptor dd) {
      ModuleRevisionId mrid = dd.getDependencyRevisionId();
      if (this.version != null && !this.version.equals(mrid.getRevision()) || this.branch != null && !this.branch.equals(mrid.getBranch())) {
         String version = this.version == null ? mrid.getRevision() : this.version;
         String branch = this.branch == null ? mrid.getBranch() : this.branch;
         return version.equals(dd.getDependencyRevisionId().getRevision()) && branch.equals(dd.getDependencyRevisionId().getBranch()) ? dd : dd.clone(ModuleRevisionId.newInstance(mrid.getOrganisation(), mrid.getName(), branch, version, mrid.getQualifiedExtraAttributes()));
      } else {
         return dd;
      }
   }
}
