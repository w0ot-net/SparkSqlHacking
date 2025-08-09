package scala.reflect.internal.pickling;

public final class ByteCodecs$ {
   public static final ByteCodecs$ MODULE$ = new ByteCodecs$();

   public byte[] avoidZero(final byte[] src) {
      int i = 0;
      int srclen = src.length;

      int count;
      for(count = 0; i < srclen; ++i) {
         if (src[i] == 127) {
            ++count;
         }
      }

      byte[] dst = new byte[srclen + count];
      i = 0;

      for(int j = 0; i < srclen; ++i) {
         byte in = src[i];
         if (in == 127) {
            dst[j] = (byte)192;
            dst[j + 1] = (byte)128;
            j += 2;
         } else {
            dst[j] = (byte)(in + 1);
            ++j;
         }
      }

      return dst;
   }

   public int regenerateZero(final byte[] src) {
      int i = 0;
      int srclen = src.length;

      int j;
      for(j = 0; i < srclen; ++j) {
         int in = src[i] & 255;
         if (in == 192 && (src[i + 1] & 255) == 128) {
            src[j] = 127;
            i += 2;
         } else if (in == 0) {
            src[j] = 127;
            ++i;
         } else {
            src[j] = (byte)(in - 1);
            ++i;
         }
      }

      return j;
   }

   public byte[] encode8to7(final byte[] src) {
      int srclen = src.length;
      int dstlen = (srclen * 8 + 6) / 7;
      byte[] dst = new byte[dstlen];
      int i = 0;

      int j;
      for(j = 0; i + 6 < srclen; j += 8) {
         int in = src[i] & 255;
         dst[j] = (byte)(in & 127);
         int out = in >>> 7;
         in = src[i + 1] & 255;
         dst[j + 1] = (byte)(out | in << 1 & 127);
         out = in >>> 6;
         in = src[i + 2] & 255;
         dst[j + 2] = (byte)(out | in << 2 & 127);
         out = in >>> 5;
         in = src[i + 3] & 255;
         dst[j + 3] = (byte)(out | in << 3 & 127);
         out = in >>> 4;
         in = src[i + 4] & 255;
         dst[j + 4] = (byte)(out | in << 4 & 127);
         out = in >>> 3;
         in = src[i + 5] & 255;
         dst[j + 5] = (byte)(out | in << 5 & 127);
         out = in >>> 2;
         in = src[i + 6] & 255;
         dst[j + 6] = (byte)(out | in << 6 & 127);
         out = in >>> 1;
         dst[j + 7] = (byte)out;
         i += 7;
      }

      if (i < srclen) {
         int in = src[i] & 255;
         dst[j] = (byte)(in & 127);
         ++j;
         int out = in >>> 7;
         if (i + 1 < srclen) {
            in = src[i + 1] & 255;
            dst[j] = (byte)(out | in << 1 & 127);
            ++j;
            out = in >>> 6;
            if (i + 2 < srclen) {
               in = src[i + 2] & 255;
               dst[j] = (byte)(out | in << 2 & 127);
               ++j;
               out = in >>> 5;
               if (i + 3 < srclen) {
                  in = src[i + 3] & 255;
                  dst[j] = (byte)(out | in << 3 & 127);
                  ++j;
                  out = in >>> 4;
                  if (i + 4 < srclen) {
                     in = src[i + 4] & 255;
                     dst[j] = (byte)(out | in << 4 & 127);
                     ++j;
                     out = in >>> 3;
                     if (i + 5 < srclen) {
                        in = src[i + 5] & 255;
                        dst[j] = (byte)(out | in << 5 & 127);
                        ++j;
                        out = in >>> 2;
                     }
                  }
               }
            }
         }

         if (j < dstlen) {
            dst[j] = (byte)out;
         }
      }

      return dst;
   }

   public int decode7to8(final byte[] src, final int srclen) {
      int i = 0;
      int j = 0;

      int dstlen;
      for(dstlen = (srclen * 7 + 7) / 8; i + 7 < srclen; j += 7) {
         int out = src[i];
         byte in = src[i + 1];
         src[j] = (byte)(out | (in & 1) << 7);
         out = in >>> 1;
         in = src[i + 2];
         src[j + 1] = (byte)(out | (in & 3) << 6);
         out = in >>> 2;
         in = src[i + 3];
         src[j + 2] = (byte)(out | (in & 7) << 5);
         out = in >>> 3;
         in = src[i + 4];
         src[j + 3] = (byte)(out | (in & 15) << 4);
         out = in >>> 4;
         in = src[i + 5];
         src[j + 4] = (byte)(out | (in & 31) << 3);
         out = in >>> 5;
         in = src[i + 6];
         src[j + 5] = (byte)(out | (in & 63) << 2);
         out = in >>> 6;
         in = src[i + 7];
         src[j + 6] = (byte)(out | in << 1);
         i += 8;
      }

      if (i < srclen) {
         int out = src[i];
         if (i + 1 < srclen) {
            byte in = src[i + 1];
            src[j] = (byte)(out | (in & 1) << 7);
            ++j;
            out = in >>> 1;
            if (i + 2 < srclen) {
               in = src[i + 2];
               src[j] = (byte)(out | (in & 3) << 6);
               ++j;
               out = in >>> 2;
               if (i + 3 < srclen) {
                  in = src[i + 3];
                  src[j] = (byte)(out | (in & 7) << 5);
                  ++j;
                  out = in >>> 3;
                  if (i + 4 < srclen) {
                     in = src[i + 4];
                     src[j] = (byte)(out | (in & 15) << 4);
                     ++j;
                     out = in >>> 4;
                     if (i + 5 < srclen) {
                        in = src[i + 5];
                        src[j] = (byte)(out | (in & 31) << 3);
                        ++j;
                        out = in >>> 5;
                        if (i + 6 < srclen) {
                           in = src[i + 6];
                           src[j] = (byte)(out | (in & 63) << 2);
                           ++j;
                           out = in >>> 6;
                        }
                     }
                  }
               }
            }
         }

         if (j < dstlen) {
            src[j] = (byte)out;
         }
      }

      return dstlen;
   }

   public byte[] encode(final byte[] xs) {
      return this.avoidZero(this.encode8to7(xs));
   }

   public int decode(final byte[] xs) {
      int len = this.regenerateZero(xs);
      return this.decode7to8(xs, len);
   }

   private ByteCodecs$() {
   }
}
