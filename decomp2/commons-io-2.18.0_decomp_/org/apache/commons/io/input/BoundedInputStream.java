package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.function.IOBiConsumer;

public class BoundedInputStream extends ProxyInputStream {
   private long count;
   private long mark;
   private final long maxCount;
   private final IOBiConsumer onMaxCount;
   private boolean propagateClose;

   public static Builder builder() {
      return new Builder();
   }

   BoundedInputStream(Builder builder) throws IOException {
      super((ProxyInputStream.AbstractBuilder)builder);
      this.propagateClose = true;
      this.count = builder.getCount();
      this.maxCount = builder.getMaxCount();
      this.propagateClose = builder.isPropagateClose();
      this.onMaxCount = builder.getOnMaxCount();
   }

   /** @deprecated */
   @Deprecated
   public BoundedInputStream(InputStream in) {
      this(in, -1L);
   }

   BoundedInputStream(InputStream inputStream, Builder builder) {
      super(inputStream, builder);
      this.propagateClose = true;
      this.count = builder.getCount();
      this.maxCount = builder.getMaxCount();
      this.propagateClose = builder.isPropagateClose();
      this.onMaxCount = builder.getOnMaxCount();
   }

   /** @deprecated */
   @Deprecated
   public BoundedInputStream(InputStream inputStream, long maxCount) {
      this(inputStream, (Builder)builder().setMaxCount(maxCount));
   }

   protected synchronized void afterRead(int n) throws IOException {
      if (n != -1) {
         this.count += (long)n;
      }

      super.afterRead(n);
   }

   public int available() throws IOException {
      if (this.isMaxCount()) {
         this.onMaxLength(this.maxCount, this.getCount());
         return 0;
      } else {
         return this.in.available();
      }
   }

   public void close() throws IOException {
      if (this.propagateClose) {
         super.close();
      }

   }

   public synchronized long getCount() {
      return this.count;
   }

   public long getMaxCount() {
      return this.maxCount;
   }

   /** @deprecated */
   @Deprecated
   public long getMaxLength() {
      return this.maxCount;
   }

   public long getRemaining() {
      return Math.max(0L, this.getMaxCount() - this.getCount());
   }

   private boolean isMaxCount() {
      return this.maxCount >= 0L && this.getCount() >= this.maxCount;
   }

   public boolean isPropagateClose() {
      return this.propagateClose;
   }

   public synchronized void mark(int readLimit) {
      this.in.mark(readLimit);
      this.mark = this.count;
   }

   public boolean markSupported() {
      return this.in.markSupported();
   }

   protected void onMaxLength(long max, long count) throws IOException {
      this.onMaxCount.accept(max, count);
   }

   public int read() throws IOException {
      if (this.isMaxCount()) {
         this.onMaxLength(this.maxCount, this.getCount());
         return -1;
      } else {
         return super.read();
      }
   }

   public int read(byte[] b) throws IOException {
      return this.read(b, 0, b.length);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (this.isMaxCount()) {
         this.onMaxLength(this.maxCount, this.getCount());
         return -1;
      } else {
         return super.read(b, off, (int)this.toReadLen((long)len));
      }
   }

   public synchronized void reset() throws IOException {
      this.in.reset();
      this.count = this.mark;
   }

   /** @deprecated */
   @Deprecated
   public void setPropagateClose(boolean propagateClose) {
      this.propagateClose = propagateClose;
   }

   public synchronized long skip(long n) throws IOException {
      long skip = super.skip(this.toReadLen(n));
      this.count += skip;
      return skip;
   }

   private long toReadLen(long len) {
      return this.maxCount >= 0L ? Math.min(len, this.maxCount - this.getCount()) : len;
   }

   public String toString() {
      return this.in.toString();
   }

   abstract static class AbstractBuilder extends ProxyInputStream.AbstractBuilder {
      private long count;
      private long maxCount = -1L;
      private IOBiConsumer onMaxCount = IOBiConsumer.noop();
      private boolean propagateClose = true;

      long getCount() {
         return this.count;
      }

      long getMaxCount() {
         return this.maxCount;
      }

      IOBiConsumer getOnMaxCount() {
         return this.onMaxCount;
      }

      boolean isPropagateClose() {
         return this.propagateClose;
      }

      public AbstractBuilder setCount(long count) {
         this.count = Math.max(0L, count);
         return (AbstractBuilder)this.asThis();
      }

      public AbstractBuilder setMaxCount(long maxCount) {
         this.maxCount = Math.max(-1L, maxCount);
         return (AbstractBuilder)this.asThis();
      }

      public AbstractBuilder setOnMaxCount(IOBiConsumer onMaxCount) {
         this.onMaxCount = onMaxCount != null ? onMaxCount : IOBiConsumer.noop();
         return (AbstractBuilder)this.asThis();
      }

      public AbstractBuilder setPropagateClose(boolean propagateClose) {
         this.propagateClose = propagateClose;
         return (AbstractBuilder)this.asThis();
      }
   }

   public static class Builder extends AbstractBuilder {
      public BoundedInputStream get() throws IOException {
         return new BoundedInputStream(this);
      }
   }
}
