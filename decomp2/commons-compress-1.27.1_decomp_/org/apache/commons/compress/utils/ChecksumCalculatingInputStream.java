package org.apache.commons.compress.utils;

import java.io.InputStream;
import java.util.Objects;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

/** @deprecated */
@Deprecated
public class ChecksumCalculatingInputStream extends CheckedInputStream {
   /** @deprecated */
   @Deprecated
   public ChecksumCalculatingInputStream(Checksum checksum, InputStream inputStream) {
      super((InputStream)Objects.requireNonNull(inputStream, "inputStream"), (Checksum)Objects.requireNonNull(checksum, "checksum"));
   }

   /** @deprecated */
   @Deprecated
   public long getValue() {
      return this.getChecksum().getValue();
   }
}
