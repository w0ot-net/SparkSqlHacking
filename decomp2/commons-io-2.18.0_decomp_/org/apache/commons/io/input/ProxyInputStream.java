package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.Erase;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOIntConsumer;

public abstract class ProxyInputStream extends FilterInputStream {
   private boolean closed;
   private final IOConsumer exceptionHandler;
   private final IOIntConsumer afterRead;

   protected ProxyInputStream(AbstractBuilder builder) throws IOException {
      this(builder.getInputStream(), builder);
   }

   public ProxyInputStream(InputStream proxy) {
      super(proxy);
      this.exceptionHandler = Erase::rethrow;
      this.afterRead = IOIntConsumer.NOOP;
   }

   protected ProxyInputStream(InputStream proxy, AbstractBuilder builder) {
      super(proxy);
      this.exceptionHandler = Erase::rethrow;
      this.afterRead = builder.getAfterRead() != null ? builder.getAfterRead() : IOIntConsumer.NOOP;
   }

   protected void afterRead(int n) throws IOException {
      this.afterRead.accept(n);
   }

   public int available() throws IOException {
      if (this.in != null && !this.isClosed()) {
         try {
            return this.in.available();
         } catch (IOException e) {
            this.handleIOException(e);
         }
      }

      return 0;
   }

   protected void beforeRead(int n) throws IOException {
   }

   void checkOpen() throws IOException {
      Input.checkOpen(!this.isClosed());
   }

   public void close() throws IOException {
      IOUtils.close(this.in, this::handleIOException);
      this.closed = true;
   }

   protected void handleIOException(IOException e) throws IOException {
      this.exceptionHandler.accept(e);
   }

   boolean isClosed() {
      return this.closed;
   }

   public synchronized void mark(int readLimit) {
      if (this.in != null) {
         this.in.mark(readLimit);
      }

   }

   public boolean markSupported() {
      return this.in != null && this.in.markSupported();
   }

   public int read() throws IOException {
      try {
         this.beforeRead(1);
         int b = this.in.read();
         this.afterRead(b != -1 ? 1 : -1);
         return b;
      } catch (IOException e) {
         this.handleIOException(e);
         return -1;
      }
   }

   public int read(byte[] b) throws IOException {
      try {
         this.beforeRead(IOUtils.length(b));
         int n = this.in.read(b);
         this.afterRead(n);
         return n;
      } catch (IOException e) {
         this.handleIOException(e);
         return -1;
      }
   }

   public int read(byte[] b, int off, int len) throws IOException {
      try {
         this.beforeRead(len);
         int n = this.in.read(b, off, len);
         this.afterRead(n);
         return n;
      } catch (IOException e) {
         this.handleIOException(e);
         return -1;
      }
   }

   public synchronized void reset() throws IOException {
      try {
         this.in.reset();
      } catch (IOException e) {
         this.handleIOException(e);
      }

   }

   void setIn(InputStream in) {
      this.in = in;
   }

   public long skip(long n) throws IOException {
      try {
         return this.in.skip(n);
      } catch (IOException e) {
         this.handleIOException(e);
         return 0L;
      }
   }

   public InputStream unwrap() {
      return this.in;
   }

   protected abstract static class AbstractBuilder extends AbstractStreamBuilder {
      private IOIntConsumer afterRead;

      public IOIntConsumer getAfterRead() {
         return this.afterRead;
      }

      public AbstractStreamBuilder setAfterRead(IOIntConsumer afterRead) {
         this.afterRead = afterRead;
         return (AbstractStreamBuilder)this.asThis();
      }
   }
}
