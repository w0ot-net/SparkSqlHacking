package org.apache.hadoop.hive.serde2.objectinspector;

public interface MapEqualComparer {
   int compare(Object var1, MapObjectInspector var2, Object var3, MapObjectInspector var4);
}
