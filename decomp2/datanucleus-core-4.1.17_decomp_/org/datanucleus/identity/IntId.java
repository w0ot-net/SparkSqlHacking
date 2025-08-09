package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class IntId extends SingleFieldId {
   private int key;

   public IntId(Class pcClass, int key) {
      super(pcClass);
      this.key = key;
      this.hashCode = this.targetClassName.hashCode() ^ key;
   }

   public IntId(Class pcClass, Integer key) {
      this(pcClass, key != null ? key : -1);
      this.assertKeyNotNull(key);
   }

   public IntId(Class pcClass, String str) {
      this(pcClass, Integer.parseInt(str));
      this.assertKeyNotNull(str);
   }

   public IntId() {
   }

   public int getKey() {
      return this.key;
   }

   public Integer getKeyAsObject() {
      return this.key;
   }

   public String toString() {
      return Integer.toString(this.key);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         IntId other = (IntId)obj;
         return this.key == other.key;
      }
   }

   public int compareTo(Object o) {
      if (o instanceof IntId) {
         IntId other = (IntId)o;
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
      out.writeInt(this.key);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      super.readExternal(in);
      this.key = in.readInt();
   }
}
