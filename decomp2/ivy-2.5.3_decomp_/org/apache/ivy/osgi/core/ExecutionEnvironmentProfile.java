package org.apache.ivy.osgi.core;

import java.util.Set;
import java.util.TreeSet;

public class ExecutionEnvironmentProfile {
   Set pkgNames = new TreeSet();
   private final String name;

   public ExecutionEnvironmentProfile(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public Set getPkgNames() {
      return this.pkgNames;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      result = 31 * result + (this.pkgNames == null ? 0 : this.pkgNames.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof ExecutionEnvironmentProfile)) {
         return false;
      } else {
         ExecutionEnvironmentProfile other = (ExecutionEnvironmentProfile)obj;
         if (this.name == null) {
            if (other.name != null) {
               return false;
            }
         } else if (!this.name.equals(other.name)) {
            return false;
         }

         return this.pkgNames == null ? other.pkgNames == null : this.pkgNames.equals(other.pkgNames);
      }
   }

   public String toString() {
      return this.name + ":" + this.pkgNames;
   }
}
