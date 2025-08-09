package org.apache.derby.iapi.services.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class FormatIdUtil {
   private FormatIdUtil() {
   }

   public static int getFormatIdByteLength(int var0) {
      return 2;
   }

   public static void writeFormatIdInteger(DataOutput var0, int var1) throws IOException {
      var0.writeShort(var1);
   }

   public static int readFormatIdInteger(DataInput var0) throws IOException {
      return var0.readUnsignedShort();
   }

   public static int readFormatIdInteger(byte[] var0) {
      byte var1 = var0[0];
      byte var2 = var0[1];
      return (var1 & 255) << 8 | var2 & 255;
   }

   public static String formatIdToString(int var0) {
      return Integer.toString(var0);
   }
}
