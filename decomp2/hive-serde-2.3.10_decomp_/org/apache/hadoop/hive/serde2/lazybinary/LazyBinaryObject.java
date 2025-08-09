package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.LazyObjectBase;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public abstract class LazyBinaryObject implements LazyObjectBase {
   ObjectInspector oi;

   protected LazyBinaryObject(ObjectInspector oi) {
      this.oi = oi;
   }

   public void setNull() {
      throw new IllegalStateException("should not be called");
   }

   public abstract int hashCode();
}
