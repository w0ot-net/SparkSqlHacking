package org.sparkproject.dmg.pmml;

import java.util.List;

public interface HasDiscreteDomain {
   boolean hasValues();

   List getValues();

   default Field addValues(Value.Property property, Object... values) {
      List<Value> pmmlValues = this.getValues();

      for(Object value : values) {
         Value pmmlValue = (new Value(value)).setProperty(property);
         pmmlValues.add(pmmlValue);
      }

      return (Field)this;
   }

   Field addValues(Value... var1);
}
