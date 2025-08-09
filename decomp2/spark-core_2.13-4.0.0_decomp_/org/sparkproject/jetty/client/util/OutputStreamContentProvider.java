package org.sparkproject.jetty.client.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.sparkproject.jetty.client.AsyncContentProvider;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.Invocable;

/** @deprecated */
@Deprecated
public class OutputStreamContentProvider implements AsyncContentProvider, Callback, Closeable {
   private final DeferredContentProvider deferred = new DeferredContentProvider(new ByteBuffer[0]);
   private final OutputStream output = new DeferredOutputStream();

   public Invocable.InvocationType getInvocationType() {
      return this.deferred.getInvocationType();
   }

   public long getLength() {
      return this.deferred.getLength();
   }

   public Iterator iterator() {
      return this.deferred.iterator();
   }

   public void setListener(AsyncContentProvider.Listener listener) {
      this.deferred.setListener(listener);
   }

   public OutputStream getOutputStream() {
      return this.output;
   }

   protected void write(ByteBuffer buffer) {
      this.deferred.offer(buffer);
   }

   public void close() {
      this.deferred.close();
   }

   public void succeeded() {
      this.deferred.succeeded();
   }

   public void failed(Throwable failure) {
      this.deferred.failed(failure);
   }

   private class DeferredOutputStream extends OutputStream {
      public void write(int b) throws IOException {
         this.write(new byte[]{(byte)b}, 0, 1);
      }

      public void write(byte[] b, int off, int len) throws IOException {
         OutputStreamContentProvider.this.write(ByteBuffer.wrap(b, off, len));
         this.flush();
      }

      public void flush() throws IOException {
         OutputStreamContentProvider.this.deferred.flush();
      }

      public void close() throws IOException {
         OutputStreamContentProvider.this.close();
      }
   }
}
