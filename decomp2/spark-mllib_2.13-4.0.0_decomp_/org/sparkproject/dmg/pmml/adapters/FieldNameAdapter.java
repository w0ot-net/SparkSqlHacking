package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class FieldNameAdapter extends XmlAdapter {
   public String unmarshal(String value) {
      if (value == null) {
         throw new NullPointerException();
      } else if ("".equals(value)) {
         throw new IllegalArgumentException("Field name cannot be empty");
      } else {
         return value.intern();
      }
   }

   public String marshal(String value) {
      return value;
   }
}
