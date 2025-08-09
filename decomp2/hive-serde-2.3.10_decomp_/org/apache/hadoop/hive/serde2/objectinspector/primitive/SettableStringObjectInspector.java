package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.io.Text;

public interface SettableStringObjectInspector extends StringObjectInspector {
   Object set(Object var1, Text var2);

   Object set(Object var1, String var2);

   Object create(Text var1);

   Object create(String var1);
}
