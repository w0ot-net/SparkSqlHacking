package org.apache.ivy.osgi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManifestHeaderElement {
   private List values = new ArrayList();
   private Map attributes = new HashMap();
   private Map directives = new HashMap();

   public List getValues() {
      return this.values;
   }

   public void addValue(String value) {
      this.values.add(value);
   }

   public Map getAttributes() {
      return this.attributes;
   }

   public void addAttribute(String name, String value) {
      this.attributes.put(name, value);
   }

   public Map getDirectives() {
      return this.directives;
   }

   public void addDirective(String name, String value) {
      this.directives.put(name, value);
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ManifestHeaderElement)) {
         return false;
      } else {
         ManifestHeaderElement other = (ManifestHeaderElement)obj;
         if (other.values.size() != this.values.size()) {
            return false;
         } else {
            for(String value : this.values) {
               if (!other.values.contains(value)) {
                  return false;
               }
            }

            if (other.directives.size() != this.directives.size()) {
               return false;
            } else {
               for(Map.Entry directive : this.directives.entrySet()) {
                  if (!((String)directive.getValue()).equals(other.directives.get(directive.getKey()))) {
                     return false;
                  }
               }

               if (other.attributes.size() != this.attributes.size()) {
                  return false;
               } else {
                  for(Map.Entry attribute : this.attributes.entrySet()) {
                     if (!((String)attribute.getValue()).equals(other.attributes.get(attribute.getKey()))) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(String value : this.values) {
         if (sb.length() > 0) {
            sb.append(";");
         }

         sb.append(value);
      }

      for(Map.Entry directive : this.directives.entrySet()) {
         sb.append(";").append((String)directive.getKey()).append(":=").append((String)directive.getValue());
      }

      for(Map.Entry attribute : this.attributes.entrySet()) {
         sb.append(";").append((String)attribute.getKey()).append("=").append((String)attribute.getValue());
      }

      return sb.toString();
   }
}
