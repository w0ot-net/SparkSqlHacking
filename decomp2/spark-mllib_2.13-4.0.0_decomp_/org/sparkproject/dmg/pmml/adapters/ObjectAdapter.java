package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ObjectAdapter extends XmlAdapter {
   public Object unmarshal(String value) {
      return value;
   }

   public String marshal(Object value) {
      if (value == null) {
         return null;
      } else {
         value = ObjectUtil.toSimpleValue(value);
         return value.toString();
      }
   }
}
