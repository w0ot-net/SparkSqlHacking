package org.apache.hadoop.hive.serde2.objectinspector;

public class InspectableObject {
   public Object o;
   public ObjectInspector oi;

   public InspectableObject() {
      this((Object)null, (ObjectInspector)null);
   }

   public InspectableObject(Object o, ObjectInspector oi) {
      this.o = o;
      this.oi = oi;
   }
}
