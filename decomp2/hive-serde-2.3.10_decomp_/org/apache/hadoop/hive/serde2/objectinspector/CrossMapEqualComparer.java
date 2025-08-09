package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.Map;

public class CrossMapEqualComparer implements MapEqualComparer {
   public int compare(Object o1, MapObjectInspector moi1, Object o2, MapObjectInspector moi2) {
      int mapsize1 = moi1.getMapSize(o1);
      int mapsize2 = moi2.getMapSize(o2);
      if (mapsize1 != mapsize2) {
         return mapsize1 - mapsize2;
      } else {
         ObjectInspector mkoi1 = moi1.getMapKeyObjectInspector();
         ObjectInspector mkoi2 = moi2.getMapKeyObjectInspector();
         ObjectInspector mvoi1 = moi1.getMapValueObjectInspector();
         ObjectInspector mvoi2 = moi2.getMapValueObjectInspector();
         Map<?, ?> map1 = moi1.getMap(o1);
         Map<?, ?> map2 = moi2.getMap(o2);

         for(Object mk1 : map1.keySet()) {
            boolean notFound = true;

            for(Object mk2 : map2.keySet()) {
               int rc = ObjectInspectorUtils.compare(mk1, mkoi1, mk2, mkoi2, this);
               if (rc == 0) {
                  notFound = false;
                  Object mv1 = map1.get(mk1);
                  Object mv2 = map2.get(mk2);
                  rc = ObjectInspectorUtils.compare(mv1, mvoi1, mv2, mvoi2, this);
                  if (rc != 0) {
                     return rc;
                  }
                  break;
               }
            }

            if (notFound) {
               return 1;
            }
         }

         return 0;
      }
   }
}
