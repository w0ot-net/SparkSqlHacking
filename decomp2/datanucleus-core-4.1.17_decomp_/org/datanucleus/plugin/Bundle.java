package org.datanucleus.plugin;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bundle {
   private final String symbolicName;
   private final String vendorName;
   private final String name;
   private final String version;
   private final URL manifestLocation;
   private List requireBundle;

   public Bundle(String symbolicName, String name, String vendorName, String version, URL manifestLocation) {
      this.symbolicName = symbolicName;
      this.name = name;
      this.vendorName = vendorName;
      this.version = version;
      this.manifestLocation = manifestLocation;
   }

   public String getSymbolicName() {
      return this.symbolicName;
   }

   public String getVendorName() {
      return this.vendorName;
   }

   public String getVersion() {
      return this.version;
   }

   public URL getManifestLocation() {
      return this.manifestLocation;
   }

   public String getName() {
      return this.name;
   }

   public void setRequireBundle(List requireBundle) {
      this.requireBundle = requireBundle;
   }

   public List getRequireBundle() {
      return this.requireBundle;
   }

   public String toString() {
      return "Bundle [Symbolic Name]" + this.symbolicName + " [Version] " + this.version;
   }

   public static class BundleDescription {
      private String bundleSymbolicName;
      private Map parameters = new HashMap();

      public String getBundleSymbolicName() {
         return this.bundleSymbolicName;
      }

      public void setBundleSymbolicName(String bundleSymbolicName) {
         this.bundleSymbolicName = bundleSymbolicName;
      }

      public String getParameter(String name) {
         return (String)this.parameters.get(name);
      }

      public void setParameter(String name, String value) {
         this.parameters.put(name, value);
      }

      public void setParameters(Map parameters) {
         this.parameters.putAll(parameters);
      }

      public String toString() {
         return "BundleDescription [Symbolic Name] " + this.bundleSymbolicName + " [Parameters] " + this.parameters;
      }
   }

   public static class BundleVersion {
      public int major;
      public int minor;
      public int micro;
      public String qualifier = "";

      public int hashCode() {
         return this.major ^ this.minor ^ this.micro ^ this.qualifier.hashCode();
      }

      public boolean equals(Object object) {
         if (object == null) {
            return false;
         } else {
            return this.compareTo(object) == 0;
         }
      }

      public int compareTo(Object object) {
         if (object == this) {
            return 0;
         } else {
            BundleVersion other = (BundleVersion)object;
            int result = this.major - other.major;
            if (result != 0) {
               return result;
            } else {
               result = this.minor - other.minor;
               if (result != 0) {
                  return result;
               } else {
                  result = this.micro - other.micro;
                  return result != 0 ? result : this.qualifier.compareTo(other.qualifier);
               }
            }
         }
      }

      public String toString() {
         return "" + this.major + "." + this.minor + "." + this.micro + (this.qualifier.length() > 0 ? "." + this.qualifier : "");
      }
   }

   public static class BundleVersionRange {
      public BundleVersion floor;
      public BundleVersion ceiling;
      public boolean floor_inclusive = true;
      public boolean ceiling_inclusive = false;

      public String toString() {
         return "Bundle VersionRange [Floor] " + this.floor + " inclusive:" + this.floor_inclusive + " [Ceiling] " + this.ceiling + " inclusive:" + this.ceiling_inclusive;
      }
   }
}
