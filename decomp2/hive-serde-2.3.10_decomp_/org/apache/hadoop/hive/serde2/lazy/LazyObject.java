package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public abstract class LazyObject implements LazyObjectBase {
   protected ObjectInspector oi;
   protected boolean isNull;

   protected LazyObject(ObjectInspector oi) {
      this.oi = oi;
   }

   public abstract int hashCode();

   protected ObjectInspector getInspector() {
      return this.oi;
   }

   protected void setInspector(ObjectInspector oi) {
      this.oi = oi;
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (bytes == null) {
         throw new RuntimeException("bytes cannot be null!");
      } else {
         this.isNull = false;
      }
   }

   public void setNull() {
      this.isNull = true;
   }

   public Object getObject() {
      return this.isNull ? null : this;
   }
}
