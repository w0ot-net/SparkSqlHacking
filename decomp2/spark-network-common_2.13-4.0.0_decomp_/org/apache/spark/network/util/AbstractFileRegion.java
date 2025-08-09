package org.apache.spark.network.util;

import io.netty.channel.FileRegion;
import io.netty.util.AbstractReferenceCounted;

public abstract class AbstractFileRegion extends AbstractReferenceCounted implements FileRegion {
   public final long transfered() {
      return this.transferred();
   }

   public AbstractFileRegion retain() {
      super.retain();
      return this;
   }

   public AbstractFileRegion retain(int increment) {
      super.retain(increment);
      return this;
   }

   public AbstractFileRegion touch() {
      super.touch();
      return this;
   }

   public AbstractFileRegion touch(Object o) {
      return this;
   }
}
