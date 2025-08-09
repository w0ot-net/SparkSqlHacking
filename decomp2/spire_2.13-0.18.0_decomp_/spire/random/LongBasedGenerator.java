package spire.random;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2QAB\u0004\u0002\u00021AQ!\u0005\u0001\u0005\u0002IAQ\u0001\u0006\u0001\u0005\u0002UAQ\u0001\b\u0001\u0005BuAQA\n\u0001\u0005B\u001dBQ!\f\u0001\u0005B9\u0012!\u0003T8oO\n\u000b7/\u001a3HK:,'/\u0019;pe*\u0011\u0001\"C\u0001\u0007e\u0006tGm\\7\u000b\u0003)\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011abD\u0007\u0002\u000f%\u0011\u0001c\u0002\u0002\n\u000f\u0016tWM]1u_J\fa\u0001P5oSRtD#A\n\u0011\u00059\u0001\u0011a\u00028fqRLe\u000e\u001e\u000b\u0002-A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t\u0019\u0011J\u001c;\u0002\u0011\u0019LG\u000e\\%oiN$\"AH\u0011\u0011\u0005]y\u0012B\u0001\u0011\u0019\u0005\u0011)f.\u001b;\t\u000b\t\u001a\u0001\u0019A\u0012\u0002\u0007\u0005\u0014(\u000fE\u0002\u0018IYI!!\n\r\u0003\u000b\u0005\u0013(/Y=\u0002\u0015\u0019LG\u000e\\*i_J$8\u000f\u0006\u0002\u001fQ!)!\u0005\u0002a\u0001SA\u0019q\u0003\n\u0016\u0011\u0005]Y\u0013B\u0001\u0017\u0019\u0005\u0015\u0019\u0006n\u001c:u\u0003%1\u0017\u000e\u001c7CsR,7\u000f\u0006\u0002\u001f_!)!%\u0002a\u0001aA\u0019q\u0003J\u0019\u0011\u0005]\u0011\u0014BA\u001a\u0019\u0005\u0011\u0011\u0015\u0010^3"
)
public abstract class LongBasedGenerator extends Generator {
   public int nextInt() {
      return (int)(this.nextLong() >>> 32);
   }

   public void fillInts(final int[] arr) {
      int i = 0;
      int len = arr.length;

      int llen;
      for(llen = len & -2; i < llen; i += 2) {
         long n = this.nextLong();
         arr[i] = (int)(n & -1L);
         arr[i + 1] = (int)(n >>> 32 & -1L);
      }

      if (len != llen) {
         arr[i] = this.nextInt();
      }

   }

   public void fillShorts(final short[] arr) {
      int i = 0;
      int len = arr.length;

      for(int llen = len & -4; i < llen; i += 4) {
         long n = this.nextLong();
         arr[i] = (short)((int)(n & 65535L));
         arr[i + 1] = (short)((int)(n >>> 16 & 65535L));
         arr[i + 2] = (short)((int)(n >>> 32 & 65535L));
         arr[i + 3] = (short)((int)(n >>> 48 & 65535L));
      }

      if (i < len) {
         for(long n = this.nextLong(); i < len; ++i) {
            arr[i] = (short)((int)(n & 65535L));
            n >>>= 16;
         }
      }

   }

   public void fillBytes(final byte[] arr) {
      int i = 0;
      int len = arr.length;

      for(int llen = len & -8; i < llen; i += 8) {
         long n = this.nextLong();
         arr[i] = (byte)((int)(n & 255L));
         arr[i + 1] = (byte)((int)(n >>> 8 & 255L));
         arr[i + 2] = (byte)((int)(n >>> 16 & 255L));
         arr[i + 3] = (byte)((int)(n >>> 24 & 255L));
         arr[i + 4] = (byte)((int)(n >>> 32 & 255L));
         arr[i + 5] = (byte)((int)(n >>> 40 & 255L));
         arr[i + 6] = (byte)((int)(n >>> 48 & 255L));
         arr[i + 7] = (byte)((int)(n >>> 56 & 255L));
      }

      if (i < len) {
         for(long n = this.nextLong(); i < len; ++i) {
            arr[i] = (byte)((int)(n & 255L));
            n >>>= 8;
         }
      }

   }
}
