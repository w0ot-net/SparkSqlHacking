package org.apache.ivy.ant;

import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.OverrideDependencyDescriptorMediator;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.settings.IvySettings;

public class IvyOverride {
   private String org;
   private String module;
   private String branch;
   private String rev;
   private String matcher;

   public void setOrg(String org) {
      this.org = org;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public void setRev(String rev) {
      this.rev = rev;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
   }

   void addOverride(DefaultModuleDescriptor md, IvySettings settings) {
      String matcherName = this.matcher == null ? "exact" : this.matcher;
      String orgPattern = this.org == null ? "*" : this.org;
      String modulePattern = this.module == null ? "*" : this.module;
      md.addDependencyDescriptorMediator(new ModuleId(orgPattern, modulePattern), settings.getMatcher(matcherName), new OverrideDependencyDescriptorMediator(this.branch, this.rev));
   }
}
