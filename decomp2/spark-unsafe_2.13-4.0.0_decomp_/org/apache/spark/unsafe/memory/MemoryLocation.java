package org.apache.spark.unsafe.memory;

import javax.annotation.Nullable;

public class MemoryLocation {
   @Nullable
   Object obj;
   long offset;

   public MemoryLocation(@Nullable Object obj, long offset) {
      this.obj = obj;
      this.offset = offset;
   }

   public MemoryLocation() {
      this((Object)null, 0L);
   }

   public void setObjAndOffset(Object newObj, long newOffset) {
      this.obj = newObj;
      this.offset = newOffset;
   }

   public final Object getBaseObject() {
      return this.obj;
   }

   public final long getBaseOffset() {
      return this.offset;
   }
}
