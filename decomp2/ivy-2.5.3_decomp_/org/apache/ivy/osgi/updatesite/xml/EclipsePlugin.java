package org.apache.ivy.osgi.updatesite.xml;

import org.apache.ivy.osgi.util.Version;

public class EclipsePlugin {
   private String id;
   private Version version;

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
   }

   public void setVersion(Version version) {
      this.version = version;
   }

   public Version getVersion() {
      return this.version;
   }

   public void setUnpack(boolean valueOf) {
   }

   public void setFragment(String value) {
   }

   public void setFilter(String value) {
   }
}
