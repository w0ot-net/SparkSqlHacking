package org.apache.ivy.osgi.repo;

import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.util.Version;

public class BundleCapabilityAndLocation {
   private final String name;
   private final Version version;
   private final BundleInfo bundleInfo;
   private final String type;

   public BundleCapabilityAndLocation(String type, String name, Version version, BundleInfo bundleInfo) {
      this.type = type;
      this.name = name;
      this.version = version;
      this.bundleInfo = bundleInfo;
   }

   public BundleInfo getBundleInfo() {
      return this.bundleInfo;
   }

   public String getName() {
      return this.name;
   }

   public String getType() {
      return this.type;
   }

   public Version getVersion() {
      return this.version;
   }
}
