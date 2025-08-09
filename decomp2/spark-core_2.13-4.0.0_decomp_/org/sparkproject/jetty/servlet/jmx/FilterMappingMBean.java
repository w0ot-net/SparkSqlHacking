package org.sparkproject.jetty.servlet.jmx;

import org.sparkproject.jetty.jmx.ObjectMBean;
import org.sparkproject.jetty.servlet.FilterMapping;

public class FilterMappingMBean extends ObjectMBean {
   public FilterMappingMBean(Object managedObject) {
      super(managedObject);
   }

   public String getObjectNameBasis() {
      if (this._managed != null && this._managed instanceof FilterMapping) {
         FilterMapping mapping = (FilterMapping)this._managed;
         String name = mapping.getFilterName();
         if (name != null) {
            return name;
         }
      }

      return super.getObjectNameBasis();
   }
}
