package org.apache.commons.compress.utils;

import java.io.InputStream;
import java.util.zip.CRC32;

/** @deprecated */
@Deprecated
public class CRC32VerifyingInputStream extends ChecksumVerifyingInputStream {
   /** @deprecated */
   @Deprecated
   public CRC32VerifyingInputStream(InputStream in, long size, int expectedCrc32) {
      this(in, size, (long)expectedCrc32 & 4294967295L);
   }

   public CRC32VerifyingInputStream(InputStream in, long size, long expectedCrc32) {
      super(new CRC32(), in, size, expectedCrc32);
   }
}
