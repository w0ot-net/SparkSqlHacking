package org.sparkproject.jetty.servlet.jmx;

import org.sparkproject.jetty.jmx.ObjectMBean;
import org.sparkproject.jetty.servlet.Holder;

public class HolderMBean extends ObjectMBean {
   public HolderMBean(Object managedObject) {
      super(managedObject);
   }

   public String getObjectNameBasis() {
      if (this._managed != null && this._managed instanceof Holder) {
         Holder holder = (Holder)this._managed;
         String name = holder.getName();
         if (name != null) {
            return name;
         }
      }

      return super.getObjectNameBasis();
   }
}
