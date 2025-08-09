package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerAdapter extends XmlAdapter {
   public Integer unmarshal(String value) {
      return NumberUtil.parseInteger(value);
   }

   public String marshal(Integer value) {
      return value == null ? null : NumberUtil.printInteger(value);
   }
}
