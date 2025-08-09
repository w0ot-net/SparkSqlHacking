package org.apache.derby.impl.tools.sysinfo;

import org.apache.derby.shared.common.info.ProductVersionHolder;

public class ZipInfoProperties {
   private final ProductVersionHolder version;
   private String location;

   ZipInfoProperties(ProductVersionHolder var1) {
      this.version = var1;
   }

   public String getVersionBuildInfo() {
      if (this.version == null) {
         return Main.getTextMessage("SIF04.C");
      } else if ("DRDA:jcc".equals(this.version.getProductTechnologyName())) {
         String var10000 = this.version.getSimpleVersionString();
         return var10000 + " - (" + this.version.getBuildNumber() + ")";
      } else {
         return this.version.getVersionBuildString(true);
      }
   }

   public String getLocation() {
      return this.location == null ? Main.getTextMessage("SIF01.H") : this.location;
   }

   void setLocation(String var1) {
      this.location = var1;
   }
}
