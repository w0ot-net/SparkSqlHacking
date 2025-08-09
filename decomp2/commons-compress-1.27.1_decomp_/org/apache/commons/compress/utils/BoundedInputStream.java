package org.apache.commons.compress.utils;

import java.io.InputStream;

/** @deprecated */
@Deprecated
public class BoundedInputStream extends org.apache.commons.io.input.BoundedInputStream {
   public BoundedInputStream(InputStream in, long size) {
      super(in, size);
      this.setPropagateClose(false);
   }

   public long getBytesRemaining() {
      return this.getMaxCount() - this.getCount();
   }
}
