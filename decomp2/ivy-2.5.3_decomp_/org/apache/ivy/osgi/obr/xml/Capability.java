package org.apache.ivy.osgi.obr.xml;

import java.util.ArrayList;
import java.util.List;

public class Capability {
   private List properties = new ArrayList();
   private String name;

   public Capability(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void addProperty(String n, String value, String type) {
      this.properties.add(new CapabilityProperty(n, value, type));
   }

   public List getProperties() {
      return this.properties;
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(this.name);

      for(CapabilityProperty p : this.properties) {
         buffer.append(" ");
         buffer.append(p);
      }

      return buffer.toString();
   }
}
