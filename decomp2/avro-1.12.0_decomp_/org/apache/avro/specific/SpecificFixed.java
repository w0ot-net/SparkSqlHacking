package org.apache.avro.specific;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericFixed;
import org.apache.avro.io.BinaryData;

public abstract class SpecificFixed implements GenericFixed, Comparable, Externalizable {
   private byte[] bytes;

   public SpecificFixed() {
      this.bytes(new byte[this.getSchema().getFixedSize()]);
   }

   public SpecificFixed(byte[] bytes) {
      this.bytes(bytes);
   }

   public void bytes(byte[] bytes) {
      this.bytes = bytes;
   }

   public byte[] bytes() {
      return this.bytes;
   }

   public abstract Schema getSchema();

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else {
         return o instanceof GenericFixed && Arrays.equals(this.bytes, ((GenericFixed)o).bytes());
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.bytes);
   }

   public String toString() {
      return Arrays.toString(this.bytes);
   }

   public int compareTo(SpecificFixed that) {
      return BinaryData.compareBytes(this.bytes, 0, this.bytes.length, that.bytes, 0, that.bytes.length);
   }

   public abstract void writeExternal(ObjectOutput out) throws IOException;

   public abstract void readExternal(ObjectInput in) throws IOException;
}
