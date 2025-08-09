package org.apache.avro.hadoop.io;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.BinaryData;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.RawComparator;

public class AvroKeyComparator extends Configured implements RawComparator {
   private Schema mSchema;
   private GenericData mDataModel;

   public void setConf(Configuration conf) {
      super.setConf(conf);
      if (null != conf) {
         this.mSchema = AvroJob.getMapOutputKeySchema(conf);
         this.mDataModel = AvroSerialization.createDataModel(conf);
      }

   }

   public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      return BinaryData.compare(b1, s1, b2, s2, this.mSchema);
   }

   public int compare(AvroKey x, AvroKey y) {
      return this.mDataModel.compare(x.datum(), y.datum(), this.mSchema);
   }
}
