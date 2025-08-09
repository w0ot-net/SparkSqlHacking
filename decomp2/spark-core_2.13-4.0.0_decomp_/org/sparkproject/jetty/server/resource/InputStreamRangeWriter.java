package org.sparkproject.jetty.server.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.sparkproject.jetty.util.IO;

public class InputStreamRangeWriter implements RangeWriter {
   public static final int NO_PROGRESS_LIMIT = 3;
   private final InputStreamSupplier inputStreamSupplier;
   private boolean closed = false;
   private InputStream inputStream;
   private long pos;

   public InputStreamRangeWriter(InputStreamSupplier inputStreamSupplier) {
      this.inputStreamSupplier = inputStreamSupplier;
   }

   public void close() throws IOException {
      this.closed = true;
      if (this.inputStream != null) {
         this.inputStream.close();
      }

   }

   public void writeTo(OutputStream outputStream, long skipTo, long length) throws IOException {
      if (this.closed) {
         throw new IOException("RangeWriter is closed");
      } else {
         if (this.inputStream == null) {
            this.inputStream = this.inputStreamSupplier.newInputStream();
            this.pos = 0L;
         }

         if (skipTo < this.pos) {
            this.inputStream.close();
            this.inputStream = this.inputStreamSupplier.newInputStream();
            this.pos = 0L;
         }

         if (this.pos < skipTo) {
            long skipSoFar = this.pos;
            int noProgressLoopLimit = 3;

            while(noProgressLoopLimit > 0 && skipSoFar < skipTo) {
               long actualSkipped = this.inputStream.skip(skipTo - skipSoFar);
               if (actualSkipped == 0L) {
                  --noProgressLoopLimit;
               } else {
                  if (actualSkipped <= 0L) {
                     throw new IOException("EOF reached before InputStream skip destination");
                  }

                  skipSoFar += actualSkipped;
                  noProgressLoopLimit = 3;
               }
            }

            if (noProgressLoopLimit <= 0) {
               long var10002 = skipTo - this.pos;
               throw new IOException("No progress made to reach InputStream skip position " + var10002);
            }

            this.pos = skipTo;
         }

         IO.copy(this.inputStream, outputStream, length);
         this.pos += length;
      }
   }

   public interface InputStreamSupplier {
      InputStream newInputStream() throws IOException;
   }
}
