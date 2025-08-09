package org.apache.hadoop.hive.serde2.columnar;

import java.util.List;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyObjectBase;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryFactory;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

public class LazyBinaryColumnarStruct extends ColumnarStructBase {
   public LazyBinaryColumnarStruct(ObjectInspector oi, List notSkippedColumnIDs) {
      super(oi, notSkippedColumnIDs);
   }

   protected int getLength(ObjectInspector objectInspector, ByteArrayRef cachedByteArrayRef, int start, int length) {
      if (length == 0) {
         return -1;
      } else {
         ObjectInspector.Category category = objectInspector.getCategory();
         if (category.equals(ObjectInspector.Category.PRIMITIVE)) {
            PrimitiveObjectInspector.PrimitiveCategory primitiveCategory = ((PrimitiveObjectInspector)objectInspector).getPrimitiveCategory();
            if (primitiveCategory.equals(PrimitiveObjectInspector.PrimitiveCategory.STRING) && length == 1 && cachedByteArrayRef.getData()[start] == LazyBinaryColumnarSerDe.INVALID_UTF__SINGLE_BYTE[0]) {
               return 0;
            }
         }

         return length;
      }
   }

   protected LazyObjectBase createLazyObjectBase(ObjectInspector objectInspector) {
      return LazyBinaryFactory.createLazyBinaryObject(objectInspector);
   }
}
