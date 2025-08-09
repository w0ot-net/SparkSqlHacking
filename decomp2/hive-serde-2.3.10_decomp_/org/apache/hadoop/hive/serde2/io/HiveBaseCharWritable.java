package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hive.common.util.HiveStringUtils;

public abstract class HiveBaseCharWritable {
   protected Text value = new Text();

   public int getCharacterLength() {
      return HiveStringUtils.getTextUtfLength(this.value);
   }

   public Text getTextValue() {
      return this.value;
   }

   public void readFields(DataInput in) throws IOException {
      this.value.readFields(in);
   }

   public void write(DataOutput out) throws IOException {
      this.value.write(out);
   }

   public boolean equals(Object obj) {
      return obj != null && obj.getClass() == this.getClass() ? this.value.equals(((HiveBaseCharWritable)obj).value) : false;
   }

   public int hashCode() {
      return this.value.hashCode();
   }
}
