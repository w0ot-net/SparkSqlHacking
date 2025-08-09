package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectOutput;

public final class CharStreamHeaderGenerator implements StreamHeaderGenerator {
   private static final byte[] DERBY_EOF_MARKER = new byte[]{-32, 0, 0};
   private static final int MAX_ENCODABLE_LENGTH = 65535;

   public boolean expectsCharCount() {
      return false;
   }

   public static int writeEOFMarker(byte[] var0, int var1) {
      System.arraycopy(DERBY_EOF_MARKER, 0, var0, var1, DERBY_EOF_MARKER.length);
      return DERBY_EOF_MARKER.length;
   }

   public static int writeEOFMarker(ObjectOutput var0) throws IOException {
      var0.write(DERBY_EOF_MARKER);
      return DERBY_EOF_MARKER.length;
   }

   public int generateInto(byte[] var1, int var2, long var3) {
      if (var3 > 0L && var3 <= 65535L) {
         var1[var2] = (byte)((int)(var3 >>> 8));
         var1[var2 + 1] = (byte)((int)(var3 >>> 0));
      } else {
         var1[var2] = 0;
         var1[var2 + 1] = 0;
      }

      return 2;
   }

   public int generateInto(ObjectOutput var1, long var2) throws IOException {
      if (var2 > 0L && var2 <= 65535L) {
         var1.writeByte((byte)((int)(var2 >>> 8)));
         var1.writeByte((byte)((int)(var2 >>> 0)));
      } else {
         var1.writeByte(0);
         var1.writeByte(0);
      }

      return 2;
   }

   public int writeEOF(byte[] var1, int var2, long var3) {
      if (var3 >= 0L && var3 <= 65535L) {
         return 0;
      } else {
         System.arraycopy(DERBY_EOF_MARKER, 0, var1, var2, DERBY_EOF_MARKER.length);
         return DERBY_EOF_MARKER.length;
      }
   }

   public int writeEOF(ObjectOutput var1, long var2) throws IOException {
      if (var2 >= 0L && var2 <= 65535L) {
         return 0;
      } else {
         var1.write(DERBY_EOF_MARKER);
         return DERBY_EOF_MARKER.length;
      }
   }

   public int getMaxHeaderLength() {
      return 2;
   }
}
