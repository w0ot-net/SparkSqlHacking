package org.apache.ivy.osgi.obr.xml;

public class CapabilityProperty {
   private String name;
   private String value;
   private String type;

   public CapabilityProperty(String name, String value, String type) {
      this.name = name;
      this.value = value;
      this.type = type;
   }

   public String getName() {
      return this.name;
   }

   public String getValue() {
      return this.value;
   }

   public String getType() {
      return this.type;
   }

   public String toString() {
      return (this.type == null ? "" : "[" + this.type + "]") + this.name + "=" + this.value;
   }
}
