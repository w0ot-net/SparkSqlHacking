package org.apache.ivy.osgi.core;

import java.util.HashSet;
import java.util.Set;
import org.apache.ivy.osgi.util.Version;

public class ExportPackage extends BundleCapability {
   private final Set uses = new HashSet();

   public ExportPackage(String name, Version version) {
      super("package", name, version);
   }

   public void addUse(String pkg) {
      this.uses.add(pkg);
   }

   public Version getVersion() {
      return super.getVersion() == null ? BundleInfo.DEFAULT_VERSION : super.getVersion();
   }

   public Set getUses() {
      return this.uses;
   }

   public int hashCode() {
      int prime = 31;
      int result = super.hashCode();
      result = 31 * result + (this.uses == null ? 0 : this.uses.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         ExportPackage other = (ExportPackage)obj;
         return this.uses == null ? other.uses == null : this.uses.equals(other.uses);
      }
   }
}
