package org.apache.orc.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.orc.OrcConf;
import org.apache.orc.TypeDescription;

public final class OrcKey implements WritableComparable, JobConfigurable {
   public WritableComparable key;

   public OrcKey(WritableComparable key) {
      this.key = key;
   }

   public OrcKey() {
      this.key = null;
   }

   public void write(DataOutput dataOutput) throws IOException {
      this.key.write(dataOutput);
   }

   public void readFields(DataInput dataInput) throws IOException {
      this.key.readFields(dataInput);
   }

   public void configure(JobConf conf) {
      if (this.key == null) {
         TypeDescription schema = TypeDescription.fromString(OrcConf.MAPRED_SHUFFLE_KEY_SCHEMA.getString(conf));
         this.key = OrcStruct.createValue(schema);
      }

   }

   public int compareTo(OrcKey o) {
      return this.key.compareTo(o.key);
   }

   public boolean equals(Object o) {
      if (o != null && this.key != null) {
         return o.getClass() != this.getClass() ? false : this.key.equals(((OrcKey)o).key);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.key.hashCode();
   }
}
