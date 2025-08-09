package org.apache.hadoop.hive.serde2.columnar;

import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

public abstract class ColumnarSerDeBase extends AbstractSerDe {
   ColumnarStructBase cachedLazyStruct;
   protected ObjectInspector cachedObjectInspector;
   protected long serializedSize;
   protected SerDeStats stats;
   protected boolean lastOperationSerialize;
   protected boolean lastOperationDeserialize;
   BytesRefArrayWritable serializeCache = new BytesRefArrayWritable();
   BytesRefWritable[] field;
   ByteStream.Output serializeStream = new ByteStream.Output();

   public Object deserialize(Writable blob) throws SerDeException {
      if (!(blob instanceof BytesRefArrayWritable)) {
         throw new SerDeException(this.getClass().toString() + ": expects BytesRefArrayWritable!");
      } else {
         BytesRefArrayWritable cols = (BytesRefArrayWritable)blob;
         this.cachedLazyStruct.init(cols);
         this.lastOperationSerialize = false;
         this.lastOperationDeserialize = true;
         return this.cachedLazyStruct;
      }
   }

   public SerDeStats getSerDeStats() {
      assert this.lastOperationSerialize != this.lastOperationDeserialize;

      if (this.lastOperationSerialize) {
         this.stats.setRawDataSize(this.serializedSize);
      } else {
         this.stats.setRawDataSize(this.cachedLazyStruct.getRawDataSerializedSize());
      }

      return this.stats;
   }

   public Class getSerializedClass() {
      return BytesRefArrayWritable.class;
   }

   protected void initialize(int size) throws SerDeException {
      this.field = new BytesRefWritable[size];

      for(int i = 0; i < size; ++i) {
         this.field[i] = new BytesRefWritable();
         this.serializeCache.set(i, this.field[i]);
      }

      this.serializedSize = 0L;
      this.stats = new SerDeStats();
      this.lastOperationSerialize = false;
      this.lastOperationDeserialize = false;
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.cachedObjectInspector;
   }
}
