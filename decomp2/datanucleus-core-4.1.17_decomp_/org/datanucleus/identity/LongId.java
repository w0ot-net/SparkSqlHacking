package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class LongId extends SingleFieldId {
   private long key;

   public LongId(Class pcClass, long key) {
      super(pcClass);
      this.key = key;
      this.hashCode = this.targetClassName.hashCode() ^ (int)key;
   }

   public LongId(Class pcClass, Long key) {
      this(pcClass, key != null ? key : -1L);
      this.assertKeyNotNull(key);
   }

   public LongId(Class pcClass, String str) {
      this(pcClass, Long.parseLong(str));
      this.assertKeyNotNull(str);
   }

   public LongId() {
   }

   public long getKey() {
      return this.key;
   }

   public Long getKeyAsObject() {
      return this.key;
   }

   public String toString() {
      return Long.toString(this.key);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         LongId other = (LongId)obj;
         return this.key == other.key;
      }
   }

   public int compareTo(Object o) {
      if (o instanceof LongId) {
         LongId other = (LongId)o;
         int result = super.compare(other);
         if (result == 0) {
            long diff = this.key - other.key;
            if (diff == 0L) {
               return 0;
            } else {
               return diff < 0L ? -1 : 1;
            }
         } else {
            return result;
         }
      } else if (o == null) {
         throw new ClassCastException("object is null");
      } else {
         throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
      }
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      super.writeExternal(out);
      out.writeLong(this.key);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      super.readExternal(in);
      this.key = in.readLong();
   }
}
