package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.concurrent.Callable;

public class Streams {
   public static final int EOF = -1;

   public static byte[] bytes(InputStream in, String exmsg) {
      if (in instanceof BytesInputStream) {
         return ((BytesInputStream)in).getBytes();
      } else {
         ByteArrayOutputStream out = new ByteArrayOutputStream(8192);
         copy(in, out, new byte[8192], exmsg);
         return out.toByteArray();
      }
   }

   public static InputStream of(byte[] bytes) {
      return new BytesInputStream(bytes);
   }

   public static InputStream of(CharSequence seq) {
      return of(Strings.utf8(seq));
   }

   public static Reader reader(byte[] bytes) {
      return reader(of(bytes));
   }

   public static Reader reader(InputStream in) {
      return new InputStreamReader(in, Strings.UTF_8);
   }

   public static Reader reader(CharSequence seq) {
      return new CharSequenceReader(seq);
   }

   public static void flush(Flushable... flushables) {
      Objects.nullSafeFlush(flushables);
   }

   public static long copy(InputStream inputStream, OutputStream outputStream, byte[] buffer) throws IOException {
      Assert.notNull(inputStream, "inputStream cannot be null.");
      Assert.notNull(outputStream, "outputStream cannot be null.");
      Assert.notEmpty(buffer, "buffer cannot be null or empty.");
      long count = 0L;

      for(int n = 0; n != -1; count += (long)n) {
         n = inputStream.read(buffer);
         if (n > 0) {
            outputStream.write(buffer, 0, n);
         }
      }

      return count;
   }

   public static long copy(final InputStream in, final OutputStream out, final byte[] buffer, String exmsg) {
      return (Long)run(new Callable() {
         public Long call() throws IOException {
            Long var1;
            try {
               Streams.reset(in);
               var1 = Streams.copy(in, out, buffer);
            } finally {
               Objects.nullSafeFlush(new Flushable[]{out});
               Streams.reset(in);
            }

            return var1;
         }
      }, exmsg);
   }

   public static void reset(final InputStream in) {
      if (in != null) {
         Callable<Object> callable = new Callable() {
            public Object call() {
               try {
                  in.reset();
               } catch (Throwable var2) {
               }

               return null;
            }
         };

         try {
            callable.call();
         } catch (Throwable var3) {
         }

      }
   }

   public static void write(OutputStream out, byte[] bytes, String exMsg) {
      write(out, bytes, 0, Bytes.length(bytes), exMsg);
   }

   public static void write(final OutputStream out, final byte[] data, final int offset, final int len, String exMsg) {
      if (out != null && !Bytes.isEmpty(data) && len > 0) {
         run(new Callable() {
            public Object call() throws Exception {
               out.write(data, offset, len);
               return null;
            }
         }, exMsg);
      }
   }

   public static void writeAndClose(OutputStream out, byte[] data, String exMsg) {
      try {
         write(out, data, exMsg);
      } finally {
         Objects.nullSafeClose(new Closeable[]{out});
      }

   }

   public static Object run(Callable c, String ioExMsg) {
      Assert.hasText(ioExMsg, "IO Exception Message cannot be null or empty.");

      try {
         return c.call();
      } catch (Throwable t) {
         String msg = "IO failure: " + ioExMsg;
         if (!msg.endsWith(".")) {
            msg = msg + ".";
         }

         msg = msg + " Cause: " + t.getMessage();
         throw new io.jsonwebtoken.io.IOException(msg, t);
      }
   }
}
