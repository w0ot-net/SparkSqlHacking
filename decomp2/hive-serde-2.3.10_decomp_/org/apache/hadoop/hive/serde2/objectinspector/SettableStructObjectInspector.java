package org.apache.hadoop.hive.serde2.objectinspector;

public abstract class SettableStructObjectInspector extends StructObjectInspector {
   public abstract Object create();

   public abstract Object setStructFieldData(Object var1, StructField var2, Object var3);

   public boolean isSettable() {
      return true;
   }
}
