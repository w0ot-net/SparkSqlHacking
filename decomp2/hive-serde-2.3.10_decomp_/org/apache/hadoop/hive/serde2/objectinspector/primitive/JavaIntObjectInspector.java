package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.IntWritable;

public class JavaIntObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableIntObjectInspector {
   JavaIntObjectInspector() {
      super(TypeInfoFactory.intTypeInfo);
   }

   public Object getPrimitiveWritableObject(Object o) {
      return o == null ? null : new IntWritable((Integer)o);
   }

   public int get(Object o) {
      return (Integer)o;
   }

   public Object create(int value) {
      return value;
   }

   public Object set(Object o, int value) {
      return value;
   }
}
