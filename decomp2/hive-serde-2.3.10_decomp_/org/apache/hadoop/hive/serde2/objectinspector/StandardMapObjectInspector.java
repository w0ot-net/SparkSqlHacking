package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.HashMap;
import java.util.Map;

public class StandardMapObjectInspector implements SettableMapObjectInspector {
   private ObjectInspector mapKeyObjectInspector;
   private ObjectInspector mapValueObjectInspector;

   protected StandardMapObjectInspector() {
   }

   protected StandardMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector) {
      this.mapKeyObjectInspector = mapKeyObjectInspector;
      this.mapValueObjectInspector = mapValueObjectInspector;
   }

   public ObjectInspector getMapKeyObjectInspector() {
      return this.mapKeyObjectInspector;
   }

   public ObjectInspector getMapValueObjectInspector() {
      return this.mapValueObjectInspector;
   }

   public Object getMapValueElement(Object data, Object key) {
      if (data != null && key != null) {
         Map<?, ?> map = (Map)data;
         return map.get(key);
      } else {
         return null;
      }
   }

   public int getMapSize(Object data) {
      if (data == null) {
         return -1;
      } else {
         Map<?, ?> map = (Map)data;
         return map.size();
      }
   }

   public Map getMap(Object data) {
      if (data == null) {
         return null;
      } else {
         Map<?, ?> map = (Map)data;
         return map;
      }
   }

   public final ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.MAP;
   }

   public String getTypeName() {
      return "map<" + this.mapKeyObjectInspector.getTypeName() + "," + this.mapValueObjectInspector.getTypeName() + ">";
   }

   public Object create() {
      Map<Object, Object> m = new HashMap();
      return m;
   }

   public Object clear(Object map) {
      Map<Object, Object> m = (HashMap)map;
      m.clear();
      return m;
   }

   public Object put(Object map, Object key, Object value) {
      Map<Object, Object> m = (HashMap)map;
      m.put(key, value);
      return m;
   }

   public Object remove(Object map, Object key) {
      Map<Object, Object> m = (HashMap)map;
      m.remove(key);
      return m;
   }
}
