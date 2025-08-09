package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.NullWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroKeyRecordReader extends AvroRecordReaderBase {
   private static final Logger LOG = LoggerFactory.getLogger(AvroKeyRecordReader.class);
   private final AvroKey mCurrentRecord = new AvroKey((Object)null);

   public AvroKeyRecordReader(Schema readerSchema) {
      super(readerSchema);
   }

   public boolean nextKeyValue() throws IOException, InterruptedException {
      boolean hasNext = super.nextKeyValue();
      this.mCurrentRecord.datum(this.getCurrentRecord());
      return hasNext;
   }

   public AvroKey getCurrentKey() throws IOException, InterruptedException {
      return this.mCurrentRecord;
   }

   public NullWritable getCurrentValue() throws IOException, InterruptedException {
      return NullWritable.get();
   }
}
