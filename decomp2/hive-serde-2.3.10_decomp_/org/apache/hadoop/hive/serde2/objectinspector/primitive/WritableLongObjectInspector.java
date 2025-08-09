package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.LongWritable;

public class WritableLongObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableLongObjectInspector {
   WritableLongObjectInspector() {
      super(TypeInfoFactory.longTypeInfo);
   }

   public long get(Object o) {
      return ((LongWritable)o).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LongWritable(((LongWritable)o).get());
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((LongWritable)o).get();
   }

   public Object create(long value) {
      return new LongWritable(value);
   }

   public Object set(Object o, long value) {
      ((LongWritable)o).set(value);
      return o;
   }
}
