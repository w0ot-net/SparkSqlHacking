package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class RealNumberAdapter extends XmlAdapter {
   public Number unmarshal(String value) {
      return NumberUtil.parseNumber(value);
   }

   public String marshal(Number value) {
      return value == null ? null : NumberUtil.printNumber(value);
   }
}
