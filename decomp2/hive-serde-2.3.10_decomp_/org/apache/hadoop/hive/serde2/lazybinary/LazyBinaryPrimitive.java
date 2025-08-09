package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

public abstract class LazyBinaryPrimitive extends LazyBinaryObject {
   Writable data;

   LazyBinaryPrimitive(ObjectInspector oi) {
      super(oi);
   }

   LazyBinaryPrimitive(LazyBinaryPrimitive copy) {
      super(copy.oi);
   }

   public Object getObject() {
      return this.data;
   }

   public Writable getWritableObject() {
      return this.data;
   }

   public String toString() {
      return this.data.toString();
   }

   public int hashCode() {
      return this.data == null ? 0 : this.data.hashCode();
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof LazyBinaryPrimitive)) {
         return false;
      } else if (this.data == obj) {
         return true;
      } else {
         return this.data != null && obj != null ? this.data.equals(((LazyBinaryPrimitive)obj).getWritableObject()) : false;
      }
   }
}
