package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.IntWritable;

public class WritableIntObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableIntObjectInspector {
   WritableIntObjectInspector() {
      super(TypeInfoFactory.intTypeInfo);
   }

   public int get(Object o) {
      return ((IntWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new IntWritable(((IntWritable)o).get());
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((IntWritable)o).get();
   }

   public Object create(int value) {
      return new IntWritable(value);
   }

   public Object set(Object o, int value) {
      ((IntWritable)o).set(value);
      return o;
   }
}
