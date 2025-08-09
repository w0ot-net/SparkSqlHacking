package org.apache.ivy.osgi.obr.xml;

import org.apache.ivy.osgi.filter.OSGiFilter;

public class Requirement {
   private final String name;
   private boolean optional;
   private final OSGiFilter filter;
   private boolean multiple = false;

   public Requirement(String name, OSGiFilter filter) {
      this.name = name;
      this.filter = filter;
   }

   public String getName() {
      return this.name;
   }

   public OSGiFilter getFilter() {
      return this.filter;
   }

   public void setOptional(boolean optional) {
      this.optional = optional;
   }

   public boolean isOptional() {
      return this.optional;
   }

   public void setMultiple(boolean multiple) {
      this.multiple = multiple;
   }

   public boolean isMultiple() {
      return this.multiple;
   }
}
