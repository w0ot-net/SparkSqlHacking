package org.apache.avro.mapred;

import org.apache.avro.Schema;
import org.apache.avro.io.BinaryData;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.RawComparator;

public class AvroKeyComparator extends Configured implements RawComparator {
   private Schema schema;

   public void setConf(Configuration conf) {
      super.setConf(conf);
      if (conf != null) {
         this.schema = Pair.getKeySchema(AvroJob.getMapOutputSchema(conf));
      }

   }

   public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      return BinaryData.compare(b1, s1, l1, b2, s2, l2, this.schema);
   }

   public int compare(AvroWrapper x, AvroWrapper y) {
      return ReflectData.get().compare(x.datum(), y.datum(), this.schema);
   }
}
