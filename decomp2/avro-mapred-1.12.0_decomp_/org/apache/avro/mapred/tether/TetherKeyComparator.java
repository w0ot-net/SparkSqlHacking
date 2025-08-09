package org.apache.avro.mapred.tether;

import java.nio.ByteBuffer;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryData;
import org.apache.avro.mapred.AvroJob;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.RawComparator;

class TetherKeyComparator extends Configured implements RawComparator {
   private Schema schema;

   public void setConf(Configuration conf) {
      super.setConf(conf);
      if (conf != null) {
         this.schema = AvroJob.getMapOutputSchema(conf);
      }

   }

   public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      int diff = BinaryData.compare(b1, BinaryData.skipLong(b1, s1), l1, b2, BinaryData.skipLong(b2, s2), l2, this.schema);
      return diff == 0 ? -1 : diff;
   }

   public int compare(TetherData x, TetherData y) {
      ByteBuffer b1 = x.buffer();
      ByteBuffer b2 = y.buffer();
      int diff = BinaryData.compare(b1.array(), b1.position(), b2.array(), b2.position(), this.schema);
      return diff == 0 ? -1 : diff;
   }
}
