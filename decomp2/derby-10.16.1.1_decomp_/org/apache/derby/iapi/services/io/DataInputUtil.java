package org.apache.derby.iapi.services.io;

import java.io.DataInput;
import java.io.IOException;

public final class DataInputUtil {
   public static void skipFully(DataInput var0, int var1) throws IOException {
      if (var0 == null) {
         throw new NullPointerException();
      } else {
         int var2;
         for(; var1 > 0; var1 -= var2) {
            var2 = var0.skipBytes(var1);
            if (var2 == 0) {
               var0.readByte();
               ++var2;
            }
         }

      }
   }
}
