package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.Map;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryMap;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardMapObjectInspector;

public class LazyBinaryMapObjectInspector extends StandardMapObjectInspector {
   protected LazyBinaryMapObjectInspector() {
   }

   protected LazyBinaryMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector) {
      super(mapKeyObjectInspector, mapValueObjectInspector);
   }

   public Map getMap(Object data) {
      return data == null ? null : ((LazyBinaryMap)data).getMap();
   }

   public int getMapSize(Object data) {
      return data == null ? -1 : ((LazyBinaryMap)data).getMapSize();
   }

   public Object getMapValueElement(Object data, Object key) {
      return data != null && key != null ? ((LazyBinaryMap)data).getMapValueElement(key) : null;
   }
}
