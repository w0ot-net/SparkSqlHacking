package org.apache.ivy.osgi.core;

import org.apache.ivy.osgi.util.Version;

public class BundleCapability {
   private final String name;
   private final Version version;
   private final String type;

   public BundleCapability(String type, String name, Version version) {
      this.type = type;
      this.name = name;
      this.version = version;
   }

   public String getType() {
      return this.type;
   }

   public String getName() {
      return this.name;
   }

   public Version getVersion() {
      return this.version;
   }

   public Version getRawVersion() {
      return this.version;
   }

   public String toString() {
      return this.name + (this.version == null ? "" : ";" + this.version);
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      result = 31 * result + (this.version == null ? 0 : this.version.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof BundleCapability)) {
         return false;
      } else {
         BundleCapability other = (BundleCapability)obj;
         if (this.name == null) {
            if (other.name != null) {
               return false;
            }
         } else if (!this.name.equals(other.name)) {
            return false;
         }

         return this.version == null ? other.version == null : this.version.equals(other.version);
      }
   }
}
