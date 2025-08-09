package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Timestamp;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;

public interface SettableTimestampObjectInspector extends TimestampObjectInspector {
   Object set(Object var1, byte[] var2, int var3);

   Object set(Object var1, Timestamp var2);

   Object set(Object var1, TimestampWritable var2);

   Object create(byte[] var1, int var2);

   Object create(Timestamp var1);
}
