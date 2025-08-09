package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.Map;

public interface MapObjectInspector extends ObjectInspector {
   ObjectInspector getMapKeyObjectInspector();

   ObjectInspector getMapValueObjectInspector();

   Object getMapValueElement(Object var1, Object var2);

   Map getMap(Object var1);

   int getMapSize(Object var1);
}
