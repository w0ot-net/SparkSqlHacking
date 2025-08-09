package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ShortId extends SingleFieldId {
   private short key;

   public ShortId(Class pcClass, short key) {
      super(pcClass);
      this.key = key;
      this.hashCode = this.targetClassName.hashCode() ^ key;
   }

   public ShortId(Class pcClass, Short key) {
      this(pcClass, key != null ? key : -1);
      this.assertKeyNotNull(key);
   }

   public ShortId(Class pcClass, String str) {
      this(pcClass, Short.parseShort(str));
      this.assertKeyNotNull(str);
   }

   public ShortId() {
   }

   public short getKey() {
      return this.key;
   }

   public Short getKeyAsObject() {
      return this.key;
   }

   public String toString() {
      return Short.toString(this.key);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         ShortId other = (ShortId)obj;
         return this.key == other.key;
      }
   }

   public int compareTo(Object o) {
      if (o instanceof ShortId) {
         ShortId other = (ShortId)o;
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
      out.writeShort(this.key);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      super.readExternal(in);
      this.key = in.readShort();
   }
}
