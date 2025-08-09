package org.sparkproject.jetty.client.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import org.sparkproject.jetty.util.FutureCallback;

public class OutputStreamRequestContent extends AsyncRequestContent {
   private final AsyncOutputStream output;

   public OutputStreamRequestContent() {
      this("application/octet-stream");
   }

   public OutputStreamRequestContent(String contentType) {
      super(contentType);
      this.output = new AsyncOutputStream();
   }

   public OutputStream getOutputStream() {
      return this.output;
   }

   private class AsyncOutputStream extends OutputStream {
      public void write(int b) throws IOException {
         this.write(new byte[]{(byte)b}, 0, 1);
      }

      public void write(byte[] b, int off, int len) throws IOException {
         try {
            FutureCallback callback = new FutureCallback();
            OutputStreamRequestContent.this.offer(ByteBuffer.wrap(b, off, len), callback);
            callback.get();
         } catch (InterruptedException var5) {
            throw new InterruptedIOException();
         } catch (ExecutionException x) {
            throw new IOException(x.getCause());
         }
      }

      public void flush() throws IOException {
         OutputStreamRequestContent.this.flush();
      }

      public void close() {
         OutputStreamRequestContent.this.close();
      }
   }
}
