package org.apache.ivy.ant;

import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.conflict.FixedConflictManager;
import org.apache.ivy.util.StringUtils;

public class IvyConflict {
   private String org;
   private String module;
   private String manager;
   private String rev;
   private String matcher;

   public void setOrg(String org) {
      this.org = org;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public void setManager(String manager) {
      this.manager = manager;
   }

   public void setRev(String rev) {
      this.rev = rev;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
   }

   void addConflict(DefaultModuleDescriptor md, IvySettings settings) {
      String matcherName = this.matcher == null ? "exact" : this.matcher;
      String orgPattern = this.org == null ? "*" : this.org;
      String modulePattern = this.module == null ? "*" : this.module;
      ConflictManager cm = null;
      if (this.rev != null) {
         cm = new FixedConflictManager(StringUtils.splitToArray(this.rev));
      } else if (this.manager != null) {
         cm = settings.getConflictManager(this.manager);
      }

      md.addConflictManager(new ModuleId(orgPattern, modulePattern), settings.getMatcher(matcherName), cm);
   }
}
