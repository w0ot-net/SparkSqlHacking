package org.apache.ivy.core.module.descriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.util.extendable.UnmodifiableExtendableItem;

public abstract class AbstractIncludeExcludeRule extends UnmodifiableExtendableItem implements ConfigurationAware {
   private ArtifactId id;
   private final Collection confs = new ArrayList();
   private PatternMatcher patternMatcher;

   public AbstractIncludeExcludeRule(ArtifactId aid, PatternMatcher matcher, Map extraAttributes) {
      super((Map)null, extraAttributes);
      this.id = aid;
      this.patternMatcher = matcher;
      this.initStandardAttributes();
   }

   private void initStandardAttributes() {
      this.setStandardAttribute("organisation", this.id.getModuleId().getOrganisation());
      this.setStandardAttribute("module", this.id.getModuleId().getName());
      this.setStandardAttribute("artifact", this.id.getName());
      this.setStandardAttribute("type", this.id.getType());
      this.setStandardAttribute("ext", this.id.getExt());
      this.setStandardAttribute("matcher", this.patternMatcher.getName());
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof AbstractIncludeExcludeRule)) {
         return false;
      } else {
         AbstractIncludeExcludeRule rule = (AbstractIncludeExcludeRule)obj;
         return this.getId().equals(rule.getId());
      }
   }

   public int hashCode() {
      return this.getId().hashCode();
   }

   public void addConfiguration(String conf) {
      this.confs.add(conf);
   }

   public ArtifactId getId() {
      return this.id;
   }

   public String[] getConfigurations() {
      return (String[])this.confs.toArray(new String[this.confs.size()]);
   }

   public PatternMatcher getMatcher() {
      return this.patternMatcher;
   }

   public String toString() {
      return this.id + "(" + this.confs + ")";
   }
}
