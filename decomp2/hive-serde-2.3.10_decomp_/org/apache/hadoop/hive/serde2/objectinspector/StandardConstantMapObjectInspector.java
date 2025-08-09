package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.Map;

public class StandardConstantMapObjectInspector extends StandardMapObjectInspector implements ConstantObjectInspector {
   private Map value;

   protected StandardConstantMapObjectInspector() {
   }

   protected StandardConstantMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector, Map value) {
      super(mapKeyObjectInspector, mapValueObjectInspector);
      this.value = value;
   }

   public Map getWritableConstantValue() {
      return this.value;
   }
}
