package org.apache.ivy.core.module.descriptor;

import java.util.Map;

public class ConfigurationIntersection extends Configuration {
   private final Map intersectedConfs;

   public ConfigurationIntersection(String confName, Map intersectedConfs) {
      super(confName);
      this.intersectedConfs = intersectedConfs;
   }

   public String[] getIntersectedConfigurationNames() {
      return (String[])this.intersectedConfs.keySet().toArray(new String[this.intersectedConfs.size()]);
   }

   public Configuration getIntersectedConfiguration(String confName) {
      return (Configuration)this.intersectedConfs.get(confName);
   }

   public Configuration.Visibility getVisibility() {
      for(Configuration c : this.intersectedConfs.values()) {
         if (c != null && Configuration.Visibility.PRIVATE.equals(c.getVisibility())) {
            return Configuration.Visibility.PRIVATE;
         }
      }

      return Configuration.Visibility.PUBLIC;
   }
}
