package org.tukaani.xz.check;

import java.security.NoSuchAlgorithmException;
import org.tukaani.xz.UnsupportedOptionsException;

public abstract class Check {
   int size;
   String name;

   public abstract void update(byte[] var1, int var2, int var3);

   public abstract byte[] finish();

   public void update(byte[] buf) {
      this.update(buf, 0, buf.length);
   }

   public int getSize() {
      return this.size;
   }

   public String getName() {
      return this.name;
   }

   public static Check getInstance(int checkType) throws UnsupportedOptionsException {
      switch (checkType) {
         case 0:
            return new None();
         case 1:
            return new CRC32();
         case 4:
            return new CRC64();
         case 10:
            try {
               return new SHA256();
            } catch (NoSuchAlgorithmException var2) {
            }
         default:
            throw new UnsupportedOptionsException("Unsupported Check ID " + checkType);
      }
   }
}
