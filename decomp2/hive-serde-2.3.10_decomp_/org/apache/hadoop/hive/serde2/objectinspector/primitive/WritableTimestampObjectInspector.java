package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Timestamp;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class WritableTimestampObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableTimestampObjectInspector {
   public WritableTimestampObjectInspector() {
      super(TypeInfoFactory.timestampTypeInfo);
   }

   public TimestampWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : (TimestampWritable)o;
   }

   public Timestamp getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((TimestampWritable)o).getTimestamp();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new TimestampWritable((TimestampWritable)o);
   }

   public Object set(Object o, byte[] bytes, int offset) {
      ((TimestampWritable)o).set(bytes, offset);
      return o;
   }

   public Object set(Object o, Timestamp t) {
      if (t == null) {
         return null;
      } else {
         ((TimestampWritable)o).set(t);
         return o;
      }
   }

   public Object set(Object o, TimestampWritable t) {
      if (t == null) {
         return null;
      } else {
         ((TimestampWritable)o).set(t);
         return o;
      }
   }

   public Object create(byte[] bytes, int offset) {
      return new TimestampWritable(bytes, offset);
   }

   public Object create(Timestamp t) {
      return new TimestampWritable(t);
   }
}
