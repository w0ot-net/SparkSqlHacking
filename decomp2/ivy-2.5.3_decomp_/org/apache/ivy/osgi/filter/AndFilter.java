package org.apache.ivy.osgi.filter;

import java.util.Map;

public class AndFilter extends MultiOperatorFilter {
   public AndFilter() {
   }

   public AndFilter(OSGiFilter[] filters) {
      super(filters);
   }

   protected char operator() {
      return '&';
   }

   public boolean eval(Map properties) {
      for(OSGiFilter filter : this.getSubFilters()) {
         if (!filter.eval(properties)) {
            return false;
         }
      }

      return true;
   }
}
