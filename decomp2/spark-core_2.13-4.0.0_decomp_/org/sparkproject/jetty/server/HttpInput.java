package org.sparkproject.jetty.server;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.component.Destroyable;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Invocable;

public class HttpInput extends ServletInputStream implements Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(HttpInput.class);
   private final byte[] _oneByteBuffer = new byte[1];
   private final BlockingContentProducer _blockingContentProducer;
   private final AsyncContentProducer _asyncContentProducer;
   private final HttpChannelState _channelState;
   private final LongAdder _contentConsumed = new LongAdder();
   private volatile ContentProducer _contentProducer;
   private volatile boolean _consumedEof;
   private volatile ReadListener _readListener;

   public HttpInput(HttpChannelState state) {
      this._channelState = state;
      this._asyncContentProducer = new AsyncContentProducer(state.getHttpChannel());
      this._blockingContentProducer = new BlockingContentProducer(this._asyncContentProducer);
      this._contentProducer = this._blockingContentProducer;
   }

   public void recycle() {
      try (AutoLock lock = this._contentProducer.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("recycle {}", this);
         }

         this._blockingContentProducer.recycle();
      }

   }

   public void reopen() {
      try (AutoLock lock = this._contentProducer.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("reopen {}", this);
         }

         this._blockingContentProducer.reopen();
         this._contentProducer = this._blockingContentProducer;
         this._consumedEof = false;
         this._readListener = null;
         this._contentConsumed.reset();
      }

   }

   public Interceptor getInterceptor() {
      try (AutoLock lock = this._contentProducer.lock()) {
         return this._contentProducer.getInterceptor();
      }
   }

   public void setInterceptor(Interceptor interceptor) {
      try (AutoLock lock = this._contentProducer.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("setting interceptor to {} on {}", interceptor, this);
         }

         this._contentProducer.setInterceptor(interceptor);
      }

   }

   public void addInterceptor(Interceptor interceptor) {
      try (AutoLock lock = this._contentProducer.lock()) {
         Interceptor currentInterceptor = this._contentProducer.getInterceptor();
         if (currentInterceptor == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("adding single interceptor: {} on {}", interceptor, this);
            }

            this._contentProducer.setInterceptor(interceptor);
         } else {
            ChainedInterceptor chainedInterceptor = new ChainedInterceptor(currentInterceptor, interceptor);
            if (LOG.isDebugEnabled()) {
               LOG.debug("adding chained interceptor: {} on {}", chainedInterceptor, this);
            }

            this._contentProducer.setInterceptor(chainedInterceptor);
         }
      }

   }

   private int get(Content content, byte[] bytes, int offset, int length) {
      int consumed = content.get(bytes, offset, length);
      this._contentConsumed.add((long)consumed);
      return consumed;
   }

   private int get(Content content, ByteBuffer des) {
      int capacity = des.remaining();
      ByteBuffer src = content.getByteBuffer();
      if (src.remaining() > capacity) {
         int limit = src.limit();
         src.limit(src.position() + capacity);
         des.put(src);
         src.limit(limit);
      } else {
         des.put(src);
      }

      int consumed = capacity - des.remaining();
      this._contentConsumed.add((long)consumed);
      return consumed;
   }

   public long getContentConsumed() {
      return this._contentConsumed.sum();
   }

   public long getContentReceived() {
      try (AutoLock lock = this._contentProducer.lock()) {
         return this._contentProducer.getRawContentArrived();
      }
   }

   public boolean consumeAll() {
      try (AutoLock lock = this._contentProducer.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("consumeAll {}", this);
         }

         boolean atEof = this._contentProducer.consumeAll();
         if (atEof) {
            this._consumedEof = true;
         }

         if (this.isFinished()) {
            return !this.isError();
         } else {
            return false;
         }
      }
   }

   public boolean isError() {
      try (AutoLock lock = this._contentProducer.lock()) {
         boolean error = this._contentProducer.isError();
         if (LOG.isDebugEnabled()) {
            LOG.debug("isError={} {}", error, this);
         }

         return error;
      }
   }

   public boolean isAsync() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("isAsync read listener {} {}", this._readListener, this);
      }

      return this._readListener != null;
   }

   public boolean isFinished() {
      boolean finished = this._consumedEof;
      if (LOG.isDebugEnabled()) {
         LOG.debug("isFinished={} {}", finished, this);
      }

      return finished;
   }

   public boolean isReady() {
      try (AutoLock lock = this._contentProducer.lock()) {
         boolean ready = this._contentProducer.isReady();
         if (LOG.isDebugEnabled()) {
            LOG.debug("isReady={} {}", ready, this);
         }

         return ready;
      }
   }

   public void setReadListener(ReadListener readListener) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("setting read listener to {} {}", readListener, this);
      }

      if (this._readListener != null) {
         throw new IllegalStateException("ReadListener already set");
      } else if (!this._channelState.isAsyncStarted()) {
         throw new IllegalStateException("Async not started");
      } else {
         this._readListener = (ReadListener)Objects.requireNonNull(readListener);
         this._contentProducer = this._asyncContentProducer;
         if (this.isReady() && this._channelState.onReadEof()) {
            this.scheduleReadListenerNotification();
         }

      }
   }

   public boolean onContentProducible() {
      try (AutoLock lock = this._contentProducer.lock()) {
         return this._contentProducer.onContentProducible();
      }
   }

   public int read() throws IOException {
      try (AutoLock lock = this._contentProducer.lock()) {
         int read = this.read(this._oneByteBuffer, 0, 1);
         if (read == 0) {
            throw new IOException("unready read=0");
         } else {
            return read < 0 ? -1 : this._oneByteBuffer[0] & 255;
         }
      }
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.read((ByteBuffer)null, b, off, len);
   }

   public int read(ByteBuffer buffer) throws IOException {
      return this.read(buffer, (byte[])null, -1, -1);
   }

   private int read(ByteBuffer buffer, byte[] b, int off, int len) throws IOException {
      try (AutoLock lock = this._contentProducer.lock()) {
         this._contentProducer.checkMinDataRate();
         Content content = this._contentProducer.nextContent();
         if (content == null) {
            throw new IllegalStateException("read on unready input");
         } else if (!content.isSpecial()) {
            int read = buffer == null ? this.get(content, b, off, len) : this.get(content, buffer);
            if (LOG.isDebugEnabled()) {
               LOG.debug("read produced {} byte(s) {}", read, this);
            }

            if (content.isEmpty()) {
               this._contentProducer.reclaim(content);
            }

            return read;
         } else {
            Throwable error = content.getError();
            if (LOG.isDebugEnabled()) {
               LOG.debug("read error={} {}", error, this);
            }

            if (error != null) {
               if (error instanceof IOException) {
                  throw (IOException)error;
               } else {
                  throw new IOException(error);
               }
            } else if (!content.isEof()) {
               throw new AssertionError("no data, no error and not EOF");
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("read at EOF, setting consumed EOF to true {}", this);
               }

               this._consumedEof = true;
               if (this.onContentProducible()) {
                  this.scheduleReadListenerNotification();
               }

               return -1;
            }
         }
      }
   }

   private void scheduleReadListenerNotification() {
      HttpChannel channel = this._channelState.getHttpChannel();
      channel.execute(channel);
   }

   public boolean hasContent() {
      try (AutoLock lock = this._contentProducer.lock()) {
         boolean hasContent = this._contentProducer.hasContent();
         if (LOG.isDebugEnabled()) {
            LOG.debug("hasContent={} {}", hasContent, this);
         }

         return hasContent;
      }
   }

   public int available() {
      try (AutoLock lock = this._contentProducer.lock()) {
         int available = this._contentProducer.available();
         if (LOG.isDebugEnabled()) {
            LOG.debug("available={} {}", available, this);
         }

         return available;
      }
   }

   public void run() {
      Content content;
      try (AutoLock lock = this._contentProducer.lock()) {
         if (!this._contentProducer.isReady()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("running but not ready {}", this);
            }

            return;
         }

         content = this._contentProducer.nextContent();
         if (LOG.isDebugEnabled()) {
            LOG.debug("running on content {} {}", content, this);
         }
      }

      if (this._readListener == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("running without a read listener {}", this);
         }

         this.onContentProducible();
      } else {
         if (content.isSpecial()) {
            Throwable error = content.getError();
            if (error != null) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("running error={} {}", error, this);
               }

               this._channelState.getHttpChannel().getResponse().getHttpFields().add(HttpConnection.CONNECTION_CLOSE);
               this._readListener.onError(error);
            } else if (content.isEof()) {
               try {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("running at EOF {}", this);
                  }

                  this._readListener.onAllDataRead();
               } catch (Throwable var7) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("running failed onAllDataRead {}", this, var7);
                  }

                  this._readListener.onError(var7);
               }
            }
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("running has content {}", this);
            }

            try {
               this._readListener.onDataAvailable();
            } catch (Throwable var6) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("running failed onDataAvailable {}", this, var6);
               }

               this._readListener.onError(var6);
            }
         }

      }
   }

   public String toString() {
      String var10000 = this.getClass().getSimpleName();
      return var10000 + "@" + this.hashCode() + " cs=" + String.valueOf(this._channelState) + " cp=" + String.valueOf(this._contentProducer) + " eof=" + this._consumedEof;
   }

   private static class ChainedInterceptor implements Interceptor, Destroyable {
      private final Interceptor _prev;
      private final Interceptor _next;

      ChainedInterceptor(Interceptor prev, Interceptor next) {
         this._prev = prev;
         this._next = next;
      }

      Interceptor getPrev() {
         return this._prev;
      }

      Interceptor getNext() {
         return this._next;
      }

      public Content readFrom(Content content) {
         Content c = this.getPrev().readFrom(content);
         return c == null ? null : this.getNext().readFrom(c);
      }

      public void destroy() {
         if (this._prev instanceof Destroyable) {
            ((Destroyable)this._prev).destroy();
         }

         if (this._next instanceof Destroyable) {
            ((Destroyable)this._next).destroy();
         }

      }

      public String toString() {
         String var10000 = this.getClass().getSimpleName();
         return var10000 + "@" + this.hashCode() + " [p=" + String.valueOf(this._prev) + ",n=" + String.valueOf(this._next) + "]";
      }
   }

   public static class Content implements Callback {
      protected final ByteBuffer _content;

      public Content(ByteBuffer content) {
         this._content = content;
      }

      public ByteBuffer getByteBuffer() {
         return this._content;
      }

      public Invocable.InvocationType getInvocationType() {
         return Invocable.InvocationType.NON_BLOCKING;
      }

      public int get(byte[] buffer, int offset, int length) {
         length = Math.min(this._content.remaining(), length);
         this._content.get(buffer, offset, length);
         return length;
      }

      public int skip(int length) {
         length = Math.min(this._content.remaining(), length);
         this._content.position(this._content.position() + length);
         return length;
      }

      public boolean hasContent() {
         return this._content.hasRemaining();
      }

      public int remaining() {
         return this._content.remaining();
      }

      public boolean isEmpty() {
         return !this._content.hasRemaining();
      }

      public boolean isSpecial() {
         return false;
      }

      public boolean isEof() {
         return false;
      }

      public Throwable getError() {
         return null;
      }

      public String toString() {
         return String.format("%s@%x{%s,spc=%s,eof=%s,err=%s}", this.getClass().getSimpleName(), this.hashCode(), BufferUtil.toDetailString(this._content), this.isSpecial(), this.isEof(), this.getError());
      }
   }

   public static class WrappingContent extends Content {
      private final Content _delegate;
      private final boolean _eof;

      public WrappingContent(Content delegate, boolean eof) {
         super(delegate.getByteBuffer());
         this._delegate = delegate;
         this._eof = eof;
      }

      public boolean isEof() {
         return this._eof;
      }

      public void succeeded() {
         this._delegate.succeeded();
      }

      public void failed(Throwable x) {
         this._delegate.failed(x);
      }

      public Invocable.InvocationType getInvocationType() {
         return this._delegate.getInvocationType();
      }
   }

   public abstract static class SpecialContent extends Content {
      public SpecialContent() {
         super((ByteBuffer)null);
      }

      public final ByteBuffer getByteBuffer() {
         throw new IllegalStateException(String.valueOf(this) + " has no buffer");
      }

      public final int get(byte[] buffer, int offset, int length) {
         throw new IllegalStateException(String.valueOf(this) + " has no buffer");
      }

      public final int skip(int length) {
         return 0;
      }

      public final boolean hasContent() {
         return false;
      }

      public final int remaining() {
         return 0;
      }

      public final boolean isEmpty() {
         return true;
      }

      public final boolean isSpecial() {
         return true;
      }
   }

   public static final class EofContent extends SpecialContent {
      public boolean isEof() {
         return true;
      }

      public String toString() {
         return this.getClass().getSimpleName();
      }
   }

   public static final class ErrorContent extends SpecialContent {
      private final Throwable _error;

      public ErrorContent(Throwable error) {
         this._error = error;
      }

      public Throwable getError() {
         return this._error;
      }

      public String toString() {
         String var10000 = this.getClass().getSimpleName();
         return var10000 + " [" + String.valueOf(this._error) + "]";
      }
   }

   public interface Interceptor {
      Content readFrom(Content var1);
   }
}
