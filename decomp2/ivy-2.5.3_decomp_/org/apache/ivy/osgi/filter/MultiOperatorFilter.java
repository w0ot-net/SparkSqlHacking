package org.apache.ivy.osgi.filter;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiOperatorFilter extends OSGiFilter {
   private List subFilters = new ArrayList();

   public MultiOperatorFilter() {
   }

   public MultiOperatorFilter(OSGiFilter[] filters) {
      for(OSGiFilter filter : filters) {
         this.add(filter);
      }

   }

   protected abstract char operator();

   public void append(StringBuffer builder) {
      builder.append('(');
      builder.append(this.operator());

      for(OSGiFilter filter : this.subFilters) {
         filter.append(builder);
      }

      builder.append(')');
   }

   public void add(OSGiFilter subFilter2) {
      this.subFilters.add(subFilter2);
   }

   public List getSubFilters() {
      return this.subFilters;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;

      for(OSGiFilter subFilter : this.subFilters) {
         result = 31 * result + (subFilter == null ? 0 : subFilter.hashCode());
      }

      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && obj instanceof MultiOperatorFilter) {
         MultiOperatorFilter other = (MultiOperatorFilter)obj;
         return this.subFilters == null ? other.subFilters == null : other.subFilters != null && this.subFilters.size() == other.subFilters.size() && this.subFilters.containsAll(other.subFilters);
      } else {
         return false;
      }
   }
}
