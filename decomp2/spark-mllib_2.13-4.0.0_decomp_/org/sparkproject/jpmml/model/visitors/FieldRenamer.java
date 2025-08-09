package org.sparkproject.jpmml.model.visitors;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class FieldRenamer extends FieldNameFilterer {
   private Map mappings;

   public FieldRenamer(String from, String to) {
      this(Collections.singletonMap(from, to));
   }

   public FieldRenamer(Map mappings) {
      this.mappings = null;
      this.setMappings(mappings);
   }

   public String filter(String name) {
      Map<String, String> mappings = this.getMappings();
      if (name != null) {
         String updatedName = (String)mappings.get(name);
         if (updatedName != null) {
            return updatedName;
         }
      }

      return name;
   }

   public Map getMappings() {
      return this.mappings;
   }

   private void setMappings(Map mappings) {
      this.mappings = (Map)Objects.requireNonNull(mappings);
   }
}
