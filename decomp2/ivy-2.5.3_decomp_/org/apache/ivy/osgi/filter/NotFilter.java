package org.apache.ivy.osgi.filter;

import java.util.Map;

public class NotFilter extends UniOperatorFilter {
   public NotFilter(OSGiFilter subFilter) {
      super(subFilter);
   }

   protected char operator() {
      return '!';
   }

   public boolean eval(Map properties) {
      return !this.getSubFilter().eval(properties);
   }
}
