package org.sparkproject.jetty.plus.annotation;

import java.util.Objects;
import org.sparkproject.jetty.servlet.ServletHolder;

/** @deprecated */
@Deprecated
public class RunAs {
   private String _className;
   private String _roleName;

   public RunAs(String className, String roleName) {
      this._className = (String)Objects.requireNonNull(className);
      this._roleName = (String)Objects.requireNonNull(roleName);
   }

   public String getTargetClassName() {
      return this._className;
   }

   public String getRoleName() {
      return this._roleName;
   }

   public void setRunAs(ServletHolder holder) {
      if (holder != null) {
         String className = holder.getClassName();
         if (className.equals(this._className) && holder.getRegistration().getRunAsRole() == null) {
            holder.getRegistration().setRunAsRole(this._roleName);
         }

      }
   }
}
