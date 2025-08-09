package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.List;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUnion;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardUnionObjectInspector;

public class LazyBinaryUnionObjectInspector extends StandardUnionObjectInspector {
   protected LazyBinaryUnionObjectInspector() {
   }

   protected LazyBinaryUnionObjectInspector(List unionFieldObjectInspectors) {
      super(unionFieldObjectInspectors);
   }

   public byte getTag(Object o) {
      if (o == null) {
         return -1;
      } else {
         LazyBinaryUnion lazyBinaryUnion = (LazyBinaryUnion)o;
         return lazyBinaryUnion.getTag();
      }
   }

   public Object getField(Object o) {
      if (o == null) {
         return null;
      } else {
         LazyBinaryUnion lazyBinaryUnion = (LazyBinaryUnion)o;
         return lazyBinaryUnion.getField();
      }
   }
}
