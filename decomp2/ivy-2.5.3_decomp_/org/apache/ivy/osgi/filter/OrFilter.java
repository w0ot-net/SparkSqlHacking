package org.apache.ivy.osgi.filter;

import java.util.Map;

public class OrFilter extends MultiOperatorFilter {
   public OrFilter() {
   }

   public OrFilter(OSGiFilter[] filters) {
      super(filters);
   }

   protected char operator() {
      return '|';
   }

   public boolean eval(Map properties) {
      for(OSGiFilter filter : this.getSubFilters()) {
         if (filter.eval(properties)) {
            return true;
         }
      }

      return false;
   }
}
