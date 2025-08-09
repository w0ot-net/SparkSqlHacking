package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.Map;

public class SimpleMapEqualComparer implements MapEqualComparer {
   public int compare(Object o1, MapObjectInspector moi1, Object o2, MapObjectInspector moi2) {
      int mapsize1 = moi1.getMapSize(o1);
      int mapsize2 = moi2.getMapSize(o2);
      if (mapsize1 != mapsize2) {
         return mapsize1 - mapsize2;
      } else {
         ObjectInspector mvoi1 = moi1.getMapValueObjectInspector();
         ObjectInspector mvoi2 = moi2.getMapValueObjectInspector();
         Map<?, ?> map1 = moi1.getMap(o1);

         for(Object mk1 : map1.keySet()) {
            int rc = ObjectInspectorUtils.compare(moi1.getMapValueElement(o1, mk1), mvoi1, moi2.getMapValueElement(o2, mk1), mvoi2, this);
            if (rc != 0) {
               return rc;
            }
         }

         return 0;
      }
   }
}
