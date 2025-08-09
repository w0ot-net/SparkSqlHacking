package org.apache.ivy.osgi.core;

import org.apache.ivy.osgi.util.VersionRange;

public class BundleRequirement {
   private final String name;
   private final String resolution;
   private final VersionRange version;
   private final String type;

   public BundleRequirement(String type, String name, VersionRange version, String resolution) {
      this.type = type;
      this.name = name;
      this.version = version;
      this.resolution = resolution;
   }

   public String getType() {
      return this.type;
   }

   public String getName() {
      return this.name;
   }

   public VersionRange getVersion() {
      return this.version;
   }

   public String getResolution() {
      return this.resolution;
   }

   public String toString() {
      return this.name + (this.version == null ? "" : ";" + this.version) + (this.resolution == null ? "" : " (" + this.resolution + ")");
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      result = 31 * result + (this.resolution == null ? 0 : this.resolution.hashCode());
      result = 31 * result + (this.version == null ? 0 : this.version.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof BundleRequirement)) {
         return false;
      } else {
         BundleRequirement other = (BundleRequirement)obj;
         if (this.type == null) {
            if (other.type != null) {
               return false;
            }
         } else if (!this.type.equals(other.type)) {
            return false;
         }

         if (this.name == null) {
            if (other.name != null) {
               return false;
            }
         } else if (!this.name.equals(other.name)) {
            return false;
         }

         if (this.resolution == null) {
            if (other.resolution != null) {
               return false;
            }
         } else if (!this.resolution.equals(other.resolution)) {
            return false;
         }

         return this.version == null ? other.version == null : this.version.equals(other.version);
      }
   }
}
