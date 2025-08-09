package org.apache.hadoop.hive.serde2.objectinspector;

public abstract class SettableUnionObjectInspector implements UnionObjectInspector {
   public abstract Object create();

   public abstract Object addField(Object var1, Object var2);
}
