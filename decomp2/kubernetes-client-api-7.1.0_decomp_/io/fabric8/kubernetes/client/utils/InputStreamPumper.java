package io.fabric8.kubernetes.client.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputStreamPumper {
   private static final int DEFAULT_BUFFER_SIZE = 8192;
   private static final Logger LOGGER = LoggerFactory.getLogger(InputStreamPumper.class);

   private InputStreamPumper() {
   }

   public static InputStream asInterruptible(final InputStream is) {
      return new InputStream() {
         public int read() {
            throw new UnsupportedOperationException();
         }

         public int read(byte[] b, int off, int len) throws IOException {
            while(!Thread.currentThread().isInterrupted()) {
               if (is.available() > 0) {
                  return is.read(b, off, len);
               }

               try {
                  Thread.sleep(50L);
               } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                  throw new IOException(e);
               }
            }

            throw new IOException();
         }
      };
   }

   public static void transferTo(InputStream in, Writable out) throws IOException {
      byte[] buffer = new byte[8192];

      int length;
      while((length = in.read(buffer, 0, buffer.length)) != -1) {
         out.write(buffer, 0, length);
      }

   }

   public static CompletableFuture pump(InputStream in, Writable out, Executor executor) {
      return CompletableFuture.runAsync(() -> {
         try {
            transferTo(in, out);
         } catch (InterruptedIOException e) {
            LOGGER.debug("Interrupted while pumping stream.", e);
         } catch (Exception e) {
            if (!Thread.currentThread().isInterrupted()) {
               LOGGER.error("Error while pumping stream.", e);
            } else {
               LOGGER.debug("Interrupted while pumping stream.");
            }
         }

      }, executor);
   }

   public static OutputStream writableOutputStream(Writable writer, Integer bufferSize) {
      return new BufferedOutputStream(new WritableOutputStream(writer), (Integer)Utils.getNonNullOrElse(bufferSize, 8192));
   }

   static class WritableOutputStream extends OutputStream {
      Writable writer;

      WritableOutputStream(Writable writer) {
         this.writer = writer;
      }

      public void write(byte[] b, int off, int len) throws IOException {
         this.writer.write(b, off, len);
      }

      public void write(int b) throws IOException {
         throw new UnsupportedOperationException();
      }
   }

   public interface Writable {
      void write(byte[] var1, int var2, int var3) throws IOException;
   }
}
