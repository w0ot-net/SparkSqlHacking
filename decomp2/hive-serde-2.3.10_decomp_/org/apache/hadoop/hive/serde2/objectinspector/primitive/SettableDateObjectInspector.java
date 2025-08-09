package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.sql.Date;
import org.apache.hadoop.hive.serde2.io.DateWritable;

public interface SettableDateObjectInspector extends DateObjectInspector {
   Object set(Object var1, Date var2);

   Object set(Object var1, DateWritable var2);

   Object create(Date var1);
}
