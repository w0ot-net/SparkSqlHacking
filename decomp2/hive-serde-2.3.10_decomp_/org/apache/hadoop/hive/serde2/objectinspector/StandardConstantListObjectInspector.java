package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.List;

public class StandardConstantListObjectInspector extends StandardListObjectInspector implements ConstantObjectInspector {
   private List value;

   protected StandardConstantListObjectInspector() {
   }

   protected StandardConstantListObjectInspector(ObjectInspector listElementObjectInspector, List value) {
      super(listElementObjectInspector);
      this.value = value;
   }

   public List getWritableConstantValue() {
      return this.value;
   }
}
