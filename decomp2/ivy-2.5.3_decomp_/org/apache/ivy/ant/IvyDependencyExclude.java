package org.apache.ivy.ant;

import java.util.Map;
import org.apache.ivy.core.module.descriptor.DefaultExcludeRule;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.settings.IvySettings;

public class IvyDependencyExclude {
   private String org;
   private String module;
   private String name;
   private String type;
   private String ext;
   private String matcher;

   public void setOrg(String org) {
      this.org = org;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setExt(String ext) {
      this.ext = ext;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
   }

   DefaultExcludeRule asRule(IvySettings settings) {
      String matcherName = this.matcher == null ? "exact" : this.matcher;
      String orgPattern = this.org == null ? "*" : this.org;
      String modulePattern = this.module == null ? "*" : this.module;
      String namePattern = this.name == null ? "*" : this.name;
      String typePattern = this.type == null ? "*" : this.type;
      String extPattern = this.ext == null ? typePattern : this.ext;
      ArtifactId aid = new ArtifactId(new ModuleId(orgPattern, modulePattern), namePattern, typePattern, extPattern);
      return new DefaultExcludeRule(aid, settings.getMatcher(matcherName), (Map)null);
   }
}
