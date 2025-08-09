package org.apache.orc.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.orc.TypeDescription;

public final class OrcUnion implements WritableComparable {
   private byte tag;
   private WritableComparable object;
   private final TypeDescription schema;

   public OrcUnion(TypeDescription schema) {
      this.schema = schema;
   }

   public void set(int tag, WritableComparable object) {
      this.tag = (byte)tag;
      this.object = object;
   }

   public byte getTag() {
      return this.tag;
   }

   public Writable getObject() {
      return this.object;
   }

   public boolean equals(Object other) {
      if (other != null && other.getClass() == OrcUnion.class) {
         OrcUnion oth = (OrcUnion)other;
         if (this.tag != oth.tag) {
            return false;
         } else if (this.object == null) {
            return oth.object == null;
         } else {
            return this.object.equals(oth.object);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.tag;
      if (this.object != null) {
         result ^= this.object.hashCode();
      }

      return result;
   }

   public String toString() {
      String var10000 = Integer.toString(this.tag & 255);
      return "uniontype(" + var10000 + ", " + String.valueOf(this.object) + ")";
   }

   public void write(DataOutput output) throws IOException {
      output.writeByte(this.tag);
      output.writeBoolean(this.object != null);
      if (this.object != null) {
         this.object.write(output);
      }

   }

   public void readFields(DataInput input) throws IOException {
      byte oldTag = this.tag;
      this.tag = input.readByte();
      if (input.readBoolean()) {
         if (oldTag != this.tag || this.object == null) {
            this.object = OrcStruct.createValue((TypeDescription)this.schema.getChildren().get(this.tag));
         }

         this.object.readFields(input);
      } else {
         this.object = null;
      }

   }

   public int compareTo(OrcUnion other) {
      if (other == null) {
         return -1;
      } else {
         int result = this.schema.compareTo(other.schema);
         if (result != 0) {
            return result;
         } else if (this.tag != other.tag) {
            return this.tag - other.tag;
         } else if (this.object == null) {
            return other.object == null ? 0 : 1;
         } else {
            return this.object.compareTo(other.object);
         }
      }
   }
}
