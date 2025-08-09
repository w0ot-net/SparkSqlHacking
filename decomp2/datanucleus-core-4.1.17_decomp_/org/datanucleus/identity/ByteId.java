package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ByteId extends SingleFieldId {
   private byte key;

   public ByteId(Class pcClass, byte key) {
      super(pcClass);
      this.key = key;
      this.hashCode = this.targetClassName.hashCode() ^ key;
   }

   public ByteId(Class pcClass, Byte key) {
      this(pcClass, key != null ? key : 0);
      this.assertKeyNotNull(key);
   }

   public ByteId(Class pcClass, String str) {
      this(pcClass, Byte.parseByte(str));
      this.assertKeyNotNull(str);
   }

   public ByteId() {
   }

   public byte getKey() {
      return this.key;
   }

   public Byte getKeyAsObject() {
      return this.key;
   }

   public String toString() {
      return Byte.toString(this.key);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         ByteId other = (ByteId)obj;
         return this.key == other.key;
      }
   }

   public int compareTo(Object o) {
      if (o instanceof ByteId) {
         ByteId other = (ByteId)o;
         int result = super.compare(other);
         return result == 0 ? this.key - other.key : result;
      } else if (o == null) {
         throw new ClassCastException("object is null");
      } else {
         throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
      }
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      super.writeExternal(out);
      out.writeByte(this.key);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      super.readExternal(in);
      this.key = in.readByte();
   }
}
