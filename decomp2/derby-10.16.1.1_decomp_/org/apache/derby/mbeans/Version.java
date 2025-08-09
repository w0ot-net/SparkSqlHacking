package org.apache.derby.mbeans;

import org.apache.derby.shared.common.info.ProductVersionHolder;

public class Version implements VersionMBean {
   private final ProductVersionHolder versionInfo;
   private final String permissionName;

   public Version(ProductVersionHolder var1, String var2) {
      this.versionInfo = var1;
      this.permissionName = var2;
   }

   private void checkMonitor() {
   }

   public String getProductName() {
      this.checkMonitor();
      return this.versionInfo.getProductName();
   }

   public String getProductTechnologyName() {
      this.checkMonitor();
      return this.versionInfo.getProductTechnologyName();
   }

   public String getProductVendorName() {
      this.checkMonitor();
      return this.versionInfo.getProductVendorName();
   }

   public String getVersionString() {
      this.checkMonitor();
      return this.versionInfo.getVersionBuildString(true);
   }

   public int getMajorVersion() {
      this.checkMonitor();
      return this.versionInfo.getMajorVersion();
   }

   public int getMinorVersion() {
      this.checkMonitor();
      return this.versionInfo.getMinorVersion();
   }

   public int getMaintenanceVersion() {
      this.checkMonitor();
      return this.versionInfo.getMaintVersion();
   }

   public String getBuildNumber() {
      this.checkMonitor();
      return this.versionInfo.getBuildNumber();
   }

   public boolean isBeta() {
      this.checkMonitor();
      return this.versionInfo.isBeta();
   }

   public boolean isAlpha() {
      this.checkMonitor();
      return this.versionInfo.isAlpha();
   }
}
