package org.apache.ivy.osgi.updatesite.xml;

import org.apache.ivy.osgi.util.Version;

public class Require {
   private String plugin;
   private String feature;
   private Version version;
   private String match;
   private String filter;

   public void setFeature(String feature) {
      this.feature = feature;
   }

   public String getFeature() {
      return this.feature;
   }

   public void setPlugin(String plugin) {
      this.plugin = plugin;
   }

   public String getPlugin() {
      return this.plugin;
   }

   public void setVersion(Version version) {
      this.version = version;
   }

   public Version getVersion() {
      return this.version;
   }

   public void setMatch(String match) {
      this.match = match;
   }

   public String getMatch() {
      return this.match;
   }

   public void setFilter(String filter) {
      this.filter = filter;
   }

   public String getFilter() {
      return this.filter;
   }
}
