package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.List;

public class StandardConstantStructObjectInspector extends StandardStructObjectInspector implements ConstantObjectInspector {
   private List value;

   protected StandardConstantStructObjectInspector() {
   }

   protected StandardConstantStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List value) {
      super(structFieldNames, structFieldObjectInspectors);
      this.value = value;
   }

   public List getWritableConstantValue() {
      return this.value;
   }
}
