package org.apache.avro.mapred.tether;

import java.nio.ByteBuffer;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryData;
import org.apache.avro.mapred.AvroJob;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

class TetherPartitioner implements Partitioner {
   private static final ThreadLocal CACHE = new ThreadLocal();
   private Schema schema;

   public void configure(JobConf job) {
      this.schema = AvroJob.getMapOutputSchema(job);
   }

   static void setNextPartition(int newValue) {
      CACHE.set(newValue);
   }

   public int getPartition(TetherData key, NullWritable value, int numPartitions) {
      Integer result = (Integer)CACHE.get();
      if (result != null) {
         return result;
      } else {
         ByteBuffer b = key.buffer();
         int p = b.position();
         int hashCode = BinaryData.hashCode(b.array(), p, b.limit() - p, this.schema);
         if (hashCode < 0) {
            hashCode = -hashCode;
         }

         return hashCode % numPartitions;
      }
   }
}
