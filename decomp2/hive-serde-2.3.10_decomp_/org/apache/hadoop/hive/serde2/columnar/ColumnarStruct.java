package org.apache.hadoop.hive.serde2.columnar;

import java.util.List;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyFactory;
import org.apache.hadoop.hive.serde2.lazy.LazyObjectBase;
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnarStruct extends ColumnarStructBase {
   private static final Logger LOG = LoggerFactory.getLogger(ColumnarStruct.class);
   Text nullSequence;
   int lengthNullSequence;

   public ColumnarStruct(ObjectInspector oi, List notSkippedColumnIDs, Text nullSequence) {
      super(oi, notSkippedColumnIDs);
      if (nullSequence != null) {
         this.nullSequence = nullSequence;
         this.lengthNullSequence = nullSequence.getLength();
      }

   }

   protected int getLength(ObjectInspector objectInspector, ByteArrayRef cachedByteArrayRef, int start, int fieldLen) {
      if (fieldLen == this.lengthNullSequence) {
         byte[] data = cachedByteArrayRef.getData();
         if (LazyUtils.compare(data, start, fieldLen, this.nullSequence.getBytes(), 0, this.lengthNullSequence) == 0) {
            return -1;
         }
      }

      return fieldLen;
   }

   protected LazyObjectBase createLazyObjectBase(ObjectInspector objectInspector) {
      return LazyFactory.createLazyObject(objectInspector);
   }
}
