package org.apache.ivy.osgi.util;

import java.text.ParseException;

public class Version implements Comparable {
   private int major;
   private int minor;
   private int patch;
   private String qualifier;
   private String version;
   private String input;
   private volatile boolean split;
   private volatile boolean toString;

   public Version(String versionStr, String qualifier) {
      this(qualifier == null ? versionStr : versionStr + "." + qualifier);
   }

   public Version(String versionStr) {
      this.split = false;
      this.toString = false;
      this.input = versionStr;
      this.split = false;
      this.toString = false;
   }

   public Version(int major, int minor, int patch, String qualifier) {
      this.split = false;
      this.toString = false;
      this.major = major;
      this.minor = minor;
      this.patch = patch;
      this.qualifier = qualifier;
      this.split = true;
      this.toString = false;
   }

   public Version(Version baseVersion, String extraQualifier) {
      this.split = false;
      this.toString = false;
      this.major = baseVersion.major;
      this.minor = baseVersion.minor;
      this.patch = baseVersion.patch;
      this.qualifier = baseVersion.qualifier == null ? extraQualifier : baseVersion.qualifier + extraQualifier;
      this.split = true;
      this.toString = false;
   }

   private void ensureSplit() {
      if (!this.split) {
         synchronized(this) {
            if (this.split) {
               return;
            }

            String[] splits = this.input.split("\\.");
            if (splits == null || splits.length == 0 || splits.length > 4) {
               throw new RuntimeException(new ParseException("Ill-formed OSGi version", 0));
            }

            try {
               this.major = Integer.parseInt(splits[0]);
            } catch (NumberFormatException var7) {
               throw new RuntimeException(new ParseException("Major part of an OSGi version should be an integer", 0));
            }

            try {
               this.minor = splits.length >= 2 ? Integer.parseInt(splits[1]) : 0;
            } catch (NumberFormatException var6) {
               throw new RuntimeException(new ParseException("Minor part of an OSGi version should be an integer", 0));
            }

            try {
               this.patch = splits.length >= 3 ? Integer.parseInt(splits[2]) : 0;
            } catch (NumberFormatException var5) {
               throw new RuntimeException(new ParseException("Patch part of an OSGi version should be an integer", 0));
            }

            this.qualifier = splits.length == 4 ? splits[3] : null;
            this.split = true;
         }
      }

   }

   private void ensureToString() {
      if (!this.toString) {
         synchronized(this) {
            if (this.toString) {
               return;
            }

            this.ensureSplit();
            this.version = this.major + "." + this.minor + "." + this.patch + (this.qualifier == null ? "" : "." + this.qualifier);
            this.toString = true;
         }
      }

   }

   public String toString() {
      this.ensureToString();
      return this.version;
   }

   public int hashCode() {
      this.ensureSplit();
      int prime = 31;
      int result = 1;
      result = 31 * result + this.major;
      result = 31 * result + this.minor;
      result = 31 * result + this.patch;
      result = 31 * result + (this.qualifier == null ? 0 : this.qualifier.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && obj instanceof Version) {
         boolean var10000;
         label41: {
            Version other = (Version)obj;
            this.ensureSplit();
            other.ensureSplit();
            if (this.major == other.major && this.minor == other.minor && this.patch == other.patch) {
               if (this.qualifier == null) {
                  if (other.qualifier == null) {
                     break label41;
                  }
               } else if (this.qualifier.equals(other.qualifier)) {
                  break label41;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      } else {
         return false;
      }
   }

   public Version withNudgedPatch() {
      this.ensureSplit();
      return new Version(this.major, this.minor, this.patch + 1, (String)null);
   }

   public Version withoutQualifier() {
      this.ensureSplit();
      return new Version(this.major, this.minor, this.patch, (String)null);
   }

   public String qualifier() {
      this.ensureSplit();
      return this.qualifier == null ? "" : this.qualifier;
   }

   public int compareUnqualified(Version other) {
      this.ensureSplit();
      other.ensureSplit();
      int diff = this.major - other.major;
      if (diff != 0) {
         return diff;
      } else {
         diff = this.minor - other.minor;
         if (diff != 0) {
            return diff;
         } else {
            diff = this.patch - other.patch;
            return diff != 0 ? diff : 0;
         }
      }
   }

   public int compareTo(Version other) {
      this.ensureSplit();
      other.ensureSplit();
      int diff = this.compareUnqualified(other);
      if (diff != 0) {
         return diff;
      } else if (this.qualifier == null) {
         return other.qualifier != null ? -1 : 0;
      } else {
         return other.qualifier == null ? 1 : this.qualifier.compareTo(other.qualifier);
      }
   }
}
