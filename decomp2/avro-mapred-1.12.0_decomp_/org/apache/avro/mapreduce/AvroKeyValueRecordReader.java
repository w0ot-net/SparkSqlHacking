package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.hadoop.io.AvroKeyValue;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;

public class AvroKeyValueRecordReader extends AvroRecordReaderBase {
   private final AvroKey mCurrentKey = new AvroKey((Object)null);
   private final AvroValue mCurrentValue = new AvroValue((Object)null);

   public AvroKeyValueRecordReader(Schema keyReaderSchema, Schema valueReaderSchema) {
      super(AvroKeyValue.getSchema(keyReaderSchema, valueReaderSchema));
   }

   public boolean nextKeyValue() throws IOException, InterruptedException {
      boolean hasNext = super.nextKeyValue();
      if (hasNext) {
         AvroKeyValue<K, V> avroKeyValue = new AvroKeyValue((GenericRecord)this.getCurrentRecord());
         this.mCurrentKey.datum(avroKeyValue.getKey());
         this.mCurrentValue.datum(avroKeyValue.getValue());
      } else {
         this.mCurrentKey.datum((Object)null);
         this.mCurrentValue.datum((Object)null);
      }

      return hasNext;
   }

   public AvroKey getCurrentKey() throws IOException, InterruptedException {
      return this.mCurrentKey;
   }

   public AvroValue getCurrentValue() throws IOException, InterruptedException {
      return this.mCurrentValue;
   }
}
