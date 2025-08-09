package org.apache.ivy.core.module.descriptor;

import java.util.Map;

public class ConfigurationGroup extends Configuration {
   private final Map members;

   public ConfigurationGroup(String confName, Map members) {
      super(confName);
      this.members = members;
   }

   public String[] getMembersConfigurationNames() {
      return (String[])this.members.keySet().toArray(new String[this.members.size()]);
   }

   public Configuration getMemberConfiguration(String confName) {
      return (Configuration)this.members.get(confName);
   }

   public Configuration.Visibility getVisibility() {
      for(Configuration c : this.members.values()) {
         if (c != null && Configuration.Visibility.PRIVATE.equals(c.getVisibility())) {
            return Configuration.Visibility.PRIVATE;
         }
      }

      return Configuration.Visibility.PUBLIC;
   }
}
