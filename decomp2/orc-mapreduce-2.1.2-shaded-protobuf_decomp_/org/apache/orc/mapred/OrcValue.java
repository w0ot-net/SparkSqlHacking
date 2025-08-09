package org.apache.orc.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.orc.OrcConf;
import org.apache.orc.TypeDescription;

public final class OrcValue implements Writable, JobConfigurable {
   public WritableComparable value;

   public OrcValue(WritableComparable value) {
      this.value = value;
   }

   public OrcValue() {
      this.value = null;
   }

   public void write(DataOutput dataOutput) throws IOException {
      this.value.write(dataOutput);
   }

   public void readFields(DataInput dataInput) throws IOException {
      this.value.readFields(dataInput);
   }

   public void configure(JobConf conf) {
      if (this.value == null) {
         TypeDescription schema = TypeDescription.fromString(OrcConf.MAPRED_SHUFFLE_VALUE_SCHEMA.getString(conf));
         this.value = OrcStruct.createValue(schema);
      }

   }
}
