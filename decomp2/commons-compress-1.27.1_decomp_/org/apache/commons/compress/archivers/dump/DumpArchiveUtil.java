package org.apache.commons.compress.archivers.dump;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ByteUtils;

final class DumpArchiveUtil {
   public static int calculateChecksum(byte[] buffer) {
      int calc = 0;

      for(int i = 0; i < 256; ++i) {
         calc += convert32(buffer, 4 * i);
      }

      return 84446 - (calc - convert32(buffer, 28));
   }

   public static int convert16(byte[] buffer, int offset) {
      return (int)ByteUtils.fromLittleEndian(buffer, offset, 2);
   }

   public static int convert32(byte[] buffer, int offset) {
      return (int)ByteUtils.fromLittleEndian(buffer, offset, 4);
   }

   public static long convert64(byte[] buffer, int offset) {
      return ByteUtils.fromLittleEndian(buffer, offset, 8);
   }

   static String decode(ZipEncoding encoding, byte[] b, int offset, int len) throws IOException {
      if (offset > offset + len) {
         throw new IOException("Invalid offset/length combination");
      } else {
         return encoding.decode(Arrays.copyOfRange(b, offset, offset + len));
      }
   }

   public static int getIno(byte[] buffer) {
      return convert32(buffer, 20);
   }

   public static boolean verify(byte[] buffer) {
      if (buffer == null) {
         return false;
      } else {
         int magic = convert32(buffer, 24);
         if (magic != 60012) {
            return false;
         } else {
            int checksum = convert32(buffer, 28);
            return checksum == calculateChecksum(buffer);
         }
      }
   }

   private DumpArchiveUtil() {
   }
}
