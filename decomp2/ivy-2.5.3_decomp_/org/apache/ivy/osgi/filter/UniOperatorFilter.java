package org.apache.ivy.osgi.filter;

public abstract class UniOperatorFilter extends OSGiFilter {
   private final OSGiFilter subFilter;

   public UniOperatorFilter(OSGiFilter subFilter) {
      this.subFilter = subFilter;
   }

   protected abstract char operator();

   public void append(StringBuffer builder) {
      builder.append("(");
      builder.append(this.operator());
      builder.append(this.subFilter.toString());
      builder.append(")");
   }

   public OSGiFilter getSubFilter() {
      return this.subFilter;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.subFilter == null ? 0 : this.subFilter.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof UniOperatorFilter)) {
         return false;
      } else {
         UniOperatorFilter other = (UniOperatorFilter)obj;
         return this.subFilter == null ? other.subFilter == null : this.subFilter.equals(other.subFilter);
      }
   }
}
