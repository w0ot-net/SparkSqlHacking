package org.apache.ivy.osgi.filter;

import java.util.Map;

public abstract class OSGiFilter {
   public String toString() {
      StringBuffer builder = new StringBuffer();
      this.append(builder);
      return builder.toString();
   }

   public abstract void append(StringBuffer var1);

   public abstract boolean eval(Map var1);
}
