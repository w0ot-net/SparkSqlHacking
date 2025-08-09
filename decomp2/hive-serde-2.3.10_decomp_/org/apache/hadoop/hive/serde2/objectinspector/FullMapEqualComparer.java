package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class FullMapEqualComparer implements MapEqualComparer {
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
         Object[] sortedMapKeys1 = map1.keySet().toArray();
         Arrays.sort(sortedMapKeys1, new MapKeyComparator(mkoi1));
         Object[] sortedMapKeys2 = map2.keySet().toArray();
         Arrays.sort(sortedMapKeys2, new MapKeyComparator(mkoi2));

         for(int i = 0; i < mapsize1; ++i) {
            Object mk1 = sortedMapKeys1[i];
            Object mk2 = sortedMapKeys2[i];
            int rc = ObjectInspectorUtils.compare(mk1, mkoi1, mk2, mkoi2, this);
            if (rc != 0) {
               return rc;
            }

            Object mv1 = map1.get(mk1);
            Object mv2 = map2.get(mk2);
            rc = ObjectInspectorUtils.compare(mv1, mvoi1, mv2, mvoi2, this);
            if (rc != 0) {
               return rc;
            }
         }

         return 0;
      }
   }

   private static class MapKeyComparator implements Comparator {
      private ObjectInspector oi;

      MapKeyComparator(ObjectInspector oi) {
         this.oi = oi;
      }

      public int compare(Object o1, Object o2) {
         return ObjectInspectorUtils.compare(o1, this.oi, o2, this.oi);
      }
   }
}
