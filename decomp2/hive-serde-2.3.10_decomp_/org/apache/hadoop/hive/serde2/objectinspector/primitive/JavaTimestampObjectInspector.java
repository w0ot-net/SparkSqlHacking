package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Timestamp;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class JavaTimestampObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableTimestampObjectInspector {
   protected JavaTimestampObjectInspector() {
      super(TypeInfoFactory.timestampTypeInfo);
   }

   public TimestampWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : new TimestampWritable((Timestamp)o);
   }

   public Timestamp getPrimitiveJavaObject(Object o) {
      return o == null ? null : (Timestamp)o;
   }

   public Object copyObject(Object o) {
      if (o == null) {
         return null;
      } else {
         Timestamp source = (Timestamp)o;
         Timestamp copy = new Timestamp(source.getTime());
         copy.setNanos(source.getNanos());
         return copy;
      }
   }

   public Timestamp get(Object o) {
      return (Timestamp)o;
   }

   public Object set(Object o, Timestamp value) {
      if (value == null) {
         return null;
      } else {
         ((Timestamp)o).setTime(value.getTime());
         return o;
      }
   }

   public Object set(Object o, byte[] bytes, int offset) {
      TimestampWritable.setTimestamp((Timestamp)o, bytes, offset);
      return o;
   }

   public Object set(Object o, TimestampWritable tw) {
      if (tw == null) {
         return null;
      } else {
         Timestamp t = (Timestamp)o;
         t.setTime(tw.getTimestamp().getTime());
         t.setNanos(tw.getTimestamp().getNanos());
         return t;
      }
   }

   public Object create(Timestamp value) {
      return new Timestamp(value.getTime());
   }

   public Object create(byte[] bytes, int offset) {
      return TimestampWritable.createTimestamp(bytes, offset);
   }
}
