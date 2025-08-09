package org.apache.ivy.ant;

import java.util.Map;
import org.apache.ivy.core.module.descriptor.DefaultIncludeRule;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.settings.IvySettings;

public class IvyDependencyInclude {
   private String name;
   private String type;
   private String ext;
   private String matcher;

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

   DefaultIncludeRule asRule(IvySettings settings) {
      String matcherName = this.matcher == null ? "exact" : this.matcher;
      String namePattern = this.name == null ? "*" : this.name;
      String typePattern = this.type == null ? "*" : this.type;
      String extPattern = this.ext == null ? typePattern : this.ext;
      ArtifactId aid = new ArtifactId(new ModuleId("*", "*"), namePattern, typePattern, extPattern);
      return new DefaultIncludeRule(aid, settings.getMatcher(matcherName), (Map)null);
   }
}
