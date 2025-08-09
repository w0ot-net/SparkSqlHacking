package org.sparkproject.jetty.server;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritePendingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpContent;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.EofException;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.IteratingCallback;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.SharedBlockingCallback;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Invocable;

public class HttpOutput extends ServletOutputStream implements Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(HttpOutput.class);
   private static final ThreadLocal _encoder = new ThreadLocal();
   private final HttpChannel _channel;
   private final HttpChannelState _channelState;
   private final SharedBlockingCallback _writeBlocker;
   private ApiState _apiState;
   private State _state;
   private boolean _softClose;
   private Interceptor _interceptor;
   private long _written;
   private long _flushed;
   private long _firstByteNanoTime;
   private ByteBuffer _aggregate;
   private int _bufferSize;
   private int _commitSize;
   private WriteListener _writeListener;
   private volatile Throwable _onError;
   private Callback _closedCallback;

   public HttpOutput(HttpChannel channel) {
      this._apiState = HttpOutput.ApiState.BLOCKING;
      this._state = HttpOutput.State.OPEN;
      this._softClose = false;
      this._firstByteNanoTime = -1L;
      this._channel = channel;
      this._channelState = channel.getState();
      this._interceptor = channel;
      this._writeBlocker = new WriteBlocker(channel);
      HttpConfiguration config = channel.getHttpConfiguration();
      this._bufferSize = config.getOutputBufferSize();
      this._commitSize = config.getOutputAggregationSize();
      if (this._commitSize > this._bufferSize) {
         LOG.warn("OutputAggregationSize {} exceeds bufferSize {}", this._commitSize, this._bufferSize);
         this._commitSize = this._bufferSize;
      }

   }

   public HttpChannel getHttpChannel() {
      return this._channel;
   }

   public Interceptor getInterceptor() {
      return this._interceptor;
   }

   public void setInterceptor(Interceptor interceptor) {
      this._interceptor = interceptor;
   }

   public boolean isWritten() {
      return this._written > 0L;
   }

   public long getWritten() {
      return this._written;
   }

   public void reopen() {
      try (AutoLock l = this._channelState.lock()) {
         this._softClose = false;
      }

   }

   protected SharedBlockingCallback.Blocker acquireWriteBlockingCallback() throws IOException {
      return this._writeBlocker.acquire();
   }

   private void channelWrite(ByteBuffer content, boolean complete) throws IOException {
      SharedBlockingCallback.Blocker blocker = this._writeBlocker.acquire();

      try {
         this.channelWrite(content, complete, blocker);
         blocker.block();
      } catch (Throwable var7) {
         if (blocker != null) {
            try {
               blocker.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (blocker != null) {
         blocker.close();
      }

   }

   private void channelWrite(ByteBuffer content, boolean last, Callback callback) {
      if (this._firstByteNanoTime == -1L) {
         long minDataRate = this.getHttpChannel().getHttpConfiguration().getMinResponseDataRate();
         if (minDataRate > 0L) {
            this._firstByteNanoTime = NanoTime.now();
         } else {
            this._firstByteNanoTime = Long.MAX_VALUE;
         }
      }

      this._interceptor.write(content, last, callback);
   }

   private void onWriteComplete(boolean last, Throwable failure) {
      String state = null;
      boolean wake = false;
      Callback closedCallback = null;
      ByteBuffer closeContent = null;

      try (AutoLock l = this._channelState.lock()) {
         if (LOG.isDebugEnabled()) {
            state = this.stateString();
         }

         if (!last && failure == null) {
            if (this._state == HttpOutput.State.CLOSE) {
               this._state = HttpOutput.State.CLOSING;
               closeContent = BufferUtil.hasContent(this._aggregate) ? this._aggregate : BufferUtil.EMPTY_BUFFER;
            } else {
               wake = this.updateApiState((Throwable)null);
            }
         } else {
            this._state = HttpOutput.State.CLOSED;
            closedCallback = this._closedCallback;
            this._closedCallback = null;
            this.releaseBuffer();
            wake = this.updateApiState(failure);
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("onWriteComplete({},{}) {}->{} c={} cb={} w={}", new Object[]{last, failure, state, this.stateString(), BufferUtil.toDetailString(closeContent), closedCallback, wake});
      }

      try {
         if (failure != null) {
            this._channel.abort(failure);
         }

         if (closedCallback != null) {
            if (failure == null) {
               closedCallback.succeeded();
            } else {
               closedCallback.failed(failure);
            }
         } else if (closeContent != null) {
            this.channelWrite(closeContent, true, new WriteCompleteCB());
         }
      } finally {
         if (wake) {
            this._channel.execute(this._channel);
         }

      }

   }

   private boolean updateApiState(Throwable failure) {
      boolean wake = false;
      switch (this._apiState.ordinal()) {
         case 1:
            this._apiState = HttpOutput.ApiState.BLOCKING;
            break;
         case 2:
         case 3:
         default:
            if (this._state != HttpOutput.State.CLOSED) {
               throw new IllegalStateException(this.stateString());
            }
            break;
         case 4:
            this._apiState = HttpOutput.ApiState.ASYNC;
            if (failure != null) {
               this._onError = failure;
               wake = this._channelState.onWritePossible();
            }
            break;
         case 5:
            this._apiState = HttpOutput.ApiState.READY;
            if (failure != null) {
               this._onError = failure;
            }

            wake = this._channelState.onWritePossible();
      }

      return wake;
   }

   private int maximizeAggregateSpace() {
      if (this._aggregate == null) {
         return this.getBufferSize();
      } else {
         BufferUtil.compact(this._aggregate);
         return BufferUtil.space(this._aggregate);
      }
   }

   public void softClose() {
      try (AutoLock l = this._channelState.lock()) {
         this._softClose = true;
      }

   }

   public void complete(Callback callback) {
      boolean succeeded = false;
      Throwable error = null;
      ByteBuffer content = null;

      try (AutoLock l = this._channelState.lock()) {
         switch (this._apiState.ordinal()) {
            case 1:
               error = new CancellationException("Completed whilst write blocked");
            case 2:
            case 3:
            default:
               break;
            case 4:
               if (!this._channel.getResponse().isContentComplete(this._written)) {
                  error = new CancellationException("Completed whilst write pending");
               }
               break;
            case 5:
               error = new CancellationException("Completed whilst write unready");
         }

         if (error != null) {
            this._channel.abort(error);
            this._writeBlocker.fail(error);
            this._state = HttpOutput.State.CLOSED;
         } else {
            label64:
            switch (this._state.ordinal()) {
               case 0:
                  if (this._onError != null) {
                     error = this._onError;
                  } else {
                     this._closedCallback = Callback.combine(this._closedCallback, callback);
                     switch (this._apiState.ordinal()) {
                        case 0:
                           this._apiState = HttpOutput.ApiState.BLOCKED;
                           this._state = HttpOutput.State.CLOSING;
                           content = BufferUtil.hasContent(this._aggregate) ? this._aggregate : BufferUtil.EMPTY_BUFFER;
                           break label64;
                        case 1:
                        default:
                           throw new IllegalStateException();
                        case 2:
                        case 3:
                           this._apiState = HttpOutput.ApiState.PENDING;
                           this._state = HttpOutput.State.CLOSING;
                           content = BufferUtil.hasContent(this._aggregate) ? this._aggregate : BufferUtil.EMPTY_BUFFER;
                           break label64;
                        case 4:
                        case 5:
                           this._softClose = true;
                           this._state = HttpOutput.State.CLOSE;
                     }
                  }
                  break;
               case 1:
               case 2:
                  this._closedCallback = Callback.combine(this._closedCallback, callback);
                  break;
               case 3:
                  succeeded = true;
            }
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("complete({}) {} s={} e={}, c={}", new Object[]{callback, this.stateString(), succeeded, error, BufferUtil.toDetailString(content)});
      }

      if (succeeded) {
         callback.succeeded();
      } else if (error != null) {
         callback.failed(error);
      } else {
         if (content != null) {
            this.channelWrite(content, true, new WriteCompleteCB());
         }

      }
   }

   public void completed(Throwable failure) {
      try (AutoLock l = this._channelState.lock()) {
         this._state = HttpOutput.State.CLOSED;
         this.releaseBuffer();
      }

   }

   public void close() throws IOException {
      ByteBuffer content = null;
      SharedBlockingCallback.Blocker blocker = null;

      try (AutoLock l = this._channelState.lock()) {
         if (this._onError != null) {
            if (this._onError instanceof IOException) {
               throw (IOException)this._onError;
            }

            if (this._onError instanceof RuntimeException) {
               throw (RuntimeException)this._onError;
            }

            if (this._onError instanceof Error) {
               throw (Error)this._onError;
            }

            throw new IOException(this._onError);
         }

         label95:
         switch (this._state.ordinal()) {
            case 0:
               switch (this._apiState.ordinal()) {
                  case 0:
                     this._apiState = HttpOutput.ApiState.BLOCKED;
                     this._state = HttpOutput.State.CLOSING;
                     blocker = this._writeBlocker.acquire();
                     content = BufferUtil.hasContent(this._aggregate) ? this._aggregate : BufferUtil.EMPTY_BUFFER;
                     break label95;
                  case 1:
                     this._softClose = true;
                     this._state = HttpOutput.State.CLOSE;
                     blocker = this._writeBlocker.acquire();
                     this._closedCallback = Callback.combine(this._closedCallback, blocker);
                     break label95;
                  case 2:
                  case 3:
                     this._apiState = HttpOutput.ApiState.PENDING;
                     this._state = HttpOutput.State.CLOSING;
                     content = BufferUtil.hasContent(this._aggregate) ? this._aggregate : BufferUtil.EMPTY_BUFFER;
                     break label95;
                  case 4:
                  case 5:
                     this._softClose = true;
                     this._state = HttpOutput.State.CLOSE;
                  default:
                     break label95;
               }
            case 1:
            case 2:
               switch (this._apiState.ordinal()) {
                  case 0:
                  case 1:
                     blocker = this._writeBlocker.acquire();
                     this._closedCallback = Callback.combine(this._closedCallback, blocker);
               }
            case 3:
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("close() {} c={} b={}", new Object[]{this.stateString(), BufferUtil.toDetailString(content), blocker});
      }

      if (content == null) {
         if (blocker == null) {
            return;
         }

         SharedBlockingCallback.Blocker b = blocker;

         try {
            b.block();
         } catch (Throwable var11) {
            if (blocker != null) {
               try {
                  b.close();
               } catch (Throwable var7) {
                  var11.addSuppressed(var7);
               }
            }

            throw var11;
         }

         if (blocker != null) {
            blocker.close();
         }
      } else if (blocker == null) {
         this.channelWrite(content, true, new WriteCompleteCB());
      } else {
         try {
            SharedBlockingCallback.Blocker b = blocker;

            try {
               this.channelWrite(content, true, blocker);
               b.block();
               this.onWriteComplete(true, (Throwable)null);
            } catch (Throwable var9) {
               if (blocker != null) {
                  try {
                     b.close();
                  } catch (Throwable var6) {
                     var9.addSuppressed(var6);
                  }
               }

               throw var9;
            }

            if (blocker != null) {
               blocker.close();
            }
         } catch (Throwable t) {
            this.onWriteComplete(true, t);
            throw t;
         }
      }

   }

   public ByteBuffer getBuffer() {
      try (AutoLock l = this._channelState.lock()) {
         return this.acquireBuffer();
      }
   }

   private ByteBuffer acquireBuffer() {
      if (this._aggregate == null) {
         this._aggregate = this._channel.getByteBufferPool().acquire(this.getBufferSize(), this._channel.isUseOutputDirectByteBuffers());
      }

      return this._aggregate;
   }

   private void releaseBuffer() {
      if (this._aggregate != null) {
         ByteBufferPool bufferPool = this._channel.getConnector().getByteBufferPool();
         bufferPool.release(this._aggregate);
         this._aggregate = null;
      }

   }

   public boolean isClosed() {
      boolean var2;
      try (AutoLock l = this._channelState.lock()) {
         var2 = this._softClose || this._state != HttpOutput.State.OPEN;
      }

      return var2;
   }

   public boolean isAsync() {
      try (AutoLock l = this._channelState.lock()) {
         switch (this._apiState.ordinal()) {
            case 2:
            case 3:
            case 4:
            case 5:
               return true;
            default:
               return false;
         }
      }
   }

   public void flush() throws IOException {
      ByteBuffer content = null;

      try (AutoLock l = this._channelState.lock()) {
         switch (this._state.ordinal()) {
            case 2:
            case 3:
               return;
            default:
               switch (this._apiState.ordinal()) {
                  case 0:
                     this._apiState = HttpOutput.ApiState.BLOCKED;
                     content = BufferUtil.hasContent(this._aggregate) ? this._aggregate : BufferUtil.EMPTY_BUFFER;
                     break;
                  case 1:
                  default:
                     throw new IllegalStateException(this.stateString());
                  case 2:
                  case 4:
                     throw new IllegalStateException("isReady() not called: " + this.stateString());
                  case 3:
                     this._apiState = HttpOutput.ApiState.PENDING;
                     break;
                  case 5:
                     throw new WritePendingException();
               }
         }
      }

      if (content == null) {
         (new AsyncFlush(false)).iterate();
      } else {
         try {
            this.channelWrite(content, false);
            this.onWriteComplete(false, (Throwable)null);
         } catch (Throwable t) {
            this.onWriteComplete(false, t);
            throw t;
         }
      }

   }

   private void checkWritable() throws EofException {
      if (this._softClose) {
         throw new EofException("Closed");
      } else {
         switch (this._state.ordinal()) {
            case 2:
            case 3:
               throw new EofException("Closed");
            default:
               if (this._onError != null) {
                  throw new EofException(this._onError);
               }
         }
      }
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("write(array {})", BufferUtil.toDetailString(ByteBuffer.wrap(b, off, len)));
      }

      boolean last;
      boolean aggregate;
      boolean async;
      try (AutoLock l = this._channelState.lock()) {
         this.checkWritable();
         long written = this._written + (long)len;
         int space = this.maximizeAggregateSpace();
         last = this._channel.getResponse().isAllContentWritten(written);
         aggregate = len <= this._commitSize && (!last || BufferUtil.hasContent(this._aggregate) && len <= space);
         boolean flush = last || !aggregate || len >= space;
         if (last && this._state == HttpOutput.State.OPEN) {
            this._state = HttpOutput.State.CLOSING;
         }

         switch (this._apiState.ordinal()) {
            case 0:
               this._apiState = flush ? HttpOutput.ApiState.BLOCKED : HttpOutput.ApiState.BLOCKING;
               async = false;
               break;
            case 1:
            default:
               throw new IllegalStateException(this.stateString());
            case 2:
               throw new IllegalStateException("isReady() not called: " + this.stateString());
            case 3:
               async = true;
               this._apiState = flush ? HttpOutput.ApiState.PENDING : HttpOutput.ApiState.ASYNC;
               break;
            case 4:
            case 5:
               throw new WritePendingException();
         }

         this._written = written;
         if (aggregate) {
            this.acquireBuffer();
            int filled = BufferUtil.fill(this._aggregate, b, off, len);
            if (!flush) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("write(array) {} aggregated !flush {}", this.stateString(), BufferUtil.toDetailString(this._aggregate));
               }

               return;
            }

            off += filled;
            len -= filled;
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("write(array) {} last={} agg={} flush=true async={}, len={} {}", new Object[]{this.stateString(), last, aggregate, async, len, BufferUtil.toDetailString(this._aggregate)});
      }

      if (async) {
         (new AsyncWrite(b, off, len, last)).iterate();
      } else {
         try {
            boolean complete = false;
            if (BufferUtil.hasContent(this._aggregate)) {
               complete = last && len == 0;
               this.channelWrite(this._aggregate, complete);
               if (len > 0 && !last && len <= this._commitSize && len <= this.maximizeAggregateSpace()) {
                  BufferUtil.append(this._aggregate, b, off, len);
                  this.onWriteComplete(false, (Throwable)null);
                  return;
               }
            }

            if (len > 0) {
               ByteBuffer view;
               for(view = ByteBuffer.wrap(b, off, len); len > this.getBufferSize(); len -= this.getBufferSize()) {
                  int p = view.position();
                  int l = p + this.getBufferSize();
                  view.limit(l);
                  this.channelWrite(view, false);
                  view.limit(p + len);
                  view.position(l);
               }

               this.channelWrite(view, last);
            } else if (last && !complete) {
               this.channelWrite(BufferUtil.EMPTY_BUFFER, true);
            }

            this.onWriteComplete(last, (Throwable)null);
         } catch (Throwable t) {
            this.onWriteComplete(last, t);
            throw t;
         }
      }
   }

   public void write(ByteBuffer buffer) throws IOException {
      int len = BufferUtil.length(buffer);

      boolean flush;
      boolean last;
      boolean async;
      try (AutoLock l = this._channelState.lock()) {
         this.checkWritable();
         long written = this._written + (long)len;
         last = this._channel.getResponse().isAllContentWritten(written);
         flush = last || len > 0 || BufferUtil.hasContent(this._aggregate);
         if (last && this._state == HttpOutput.State.OPEN) {
            this._state = HttpOutput.State.CLOSING;
         }

         switch (this._apiState.ordinal()) {
            case 0:
               async = false;
               this._apiState = flush ? HttpOutput.ApiState.BLOCKED : HttpOutput.ApiState.BLOCKING;
               break;
            case 1:
            default:
               throw new IllegalStateException(this.stateString());
            case 2:
               throw new IllegalStateException("isReady() not called: " + this.stateString());
            case 3:
               async = true;
               this._apiState = flush ? HttpOutput.ApiState.PENDING : HttpOutput.ApiState.ASYNC;
               break;
            case 4:
            case 5:
               throw new WritePendingException();
         }

         this._written = written;
      }

      if (flush) {
         if (async) {
            (new AsyncWrite(buffer, last)).iterate();
         } else {
            try {
               boolean complete = false;
               if (BufferUtil.hasContent(this._aggregate)) {
                  complete = last && len == 0;
                  this.channelWrite(this._aggregate, complete);
               }

               if (len > 0) {
                  this.channelWrite(buffer, last);
               } else if (last && !complete) {
                  this.channelWrite(BufferUtil.EMPTY_BUFFER, true);
               }

               this.onWriteComplete(last, (Throwable)null);
            } catch (Throwable t) {
               this.onWriteComplete(last, t);
               throw t;
            }
         }

      }
   }

   public void write(int b) throws IOException {
      boolean async = false;

      boolean flush;
      boolean last;
      try (AutoLock l = this._channelState.lock()) {
         this.checkWritable();
         long written = this._written + 1L;
         int space = this.maximizeAggregateSpace();
         last = this._channel.getResponse().isAllContentWritten(written);
         flush = last || space == 1;
         if (last && this._state == HttpOutput.State.OPEN) {
            this._state = HttpOutput.State.CLOSING;
         }

         switch (this._apiState.ordinal()) {
            case 0:
               this._apiState = flush ? HttpOutput.ApiState.BLOCKED : HttpOutput.ApiState.BLOCKING;
               break;
            case 1:
            default:
               throw new IllegalStateException(this.stateString());
            case 2:
               throw new IllegalStateException("isReady() not called: " + this.stateString());
            case 3:
               async = true;
               this._apiState = flush ? HttpOutput.ApiState.PENDING : HttpOutput.ApiState.ASYNC;
               break;
            case 4:
            case 5:
               throw new WritePendingException();
         }

         this._written = written;
         this.acquireBuffer();
         BufferUtil.append(this._aggregate, (byte)b);
      }

      if (flush) {
         if (async) {
            (new AsyncFlush(last)).iterate();
         } else {
            try {
               this.channelWrite(this._aggregate, last);
               this.onWriteComplete(last, (Throwable)null);
            } catch (Throwable t) {
               this.onWriteComplete(last, t);
               throw t;
            }
         }

      }
   }

   public void print(String s) throws IOException {
      this.print(s, false);
   }

   public void println(String s) throws IOException {
      this.print(s, true);
   }

   private void print(String s, boolean eoln) throws IOException {
      if (this.isClosed()) {
         throw new IOException("Closed");
      } else {
         s = String.valueOf(s);
         String charset = this._channel.getResponse().getCharacterEncoding();
         CharsetEncoder encoder = (CharsetEncoder)_encoder.get();
         if (encoder != null && encoder.charset().name().equalsIgnoreCase(charset)) {
            encoder.reset();
         } else {
            encoder = Charset.forName(charset).newEncoder();
            encoder.onMalformedInput(CodingErrorAction.REPLACE);
            encoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
            _encoder.set(encoder);
         }

         CharBuffer in = CharBuffer.wrap(s);
         CharBuffer crlf = eoln ? CharBuffer.wrap("\r\n") : null;
         ByteBuffer out = this.getHttpChannel().getByteBufferPool().acquire((int)(1.0F + (float)(s.length() + 2) * encoder.averageBytesPerChar()), false);
         BufferUtil.flipToFill(out);

         while(true) {
            CoderResult result;
            if (in.hasRemaining()) {
               result = encoder.encode(in, out, crlf == null);
               if (result.isUnderflow()) {
                  if (crlf == null) {
                     break;
                  }
                  continue;
               }
            } else {
               if (crlf == null || !crlf.hasRemaining()) {
                  break;
               }

               result = encoder.encode(crlf, out, true);
               if (result.isUnderflow()) {
                  if (!encoder.flush(out).isUnderflow()) {
                     result.throwException();
                  }
                  break;
               }
            }

            if (result.isOverflow()) {
               BufferUtil.flipToFlush(out, 0);
               ByteBuffer bigger = BufferUtil.ensureCapacity(out, out.capacity() + s.length() + 2);
               this.getHttpChannel().getByteBufferPool().release(out);
               BufferUtil.flipToFill(bigger);
               out = bigger;
            } else {
               result.throwException();
            }
         }

         BufferUtil.flipToFlush(out, 0);
         this.write(out.array(), out.arrayOffset(), out.remaining());
         this.getHttpChannel().getByteBufferPool().release(out);
      }
   }

   public void sendContent(ByteBuffer content) throws IOException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("sendContent({})", BufferUtil.toDetailString(content));
      }

      this._written += (long)content.remaining();
      this.channelWrite(content, true);
   }

   public void sendContent(InputStream in) throws IOException {
      SharedBlockingCallback.Blocker blocker = this._writeBlocker.acquire();

      try {
         (new InputStreamWritingCB(in, blocker)).iterate();
         blocker.block();
      } catch (Throwable var6) {
         if (blocker != null) {
            try {
               blocker.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (blocker != null) {
         blocker.close();
      }

   }

   public void sendContent(ReadableByteChannel in) throws IOException {
      SharedBlockingCallback.Blocker blocker = this._writeBlocker.acquire();

      try {
         (new ReadableByteChannelWritingCB(in, blocker)).iterate();
         blocker.block();
      } catch (Throwable var6) {
         if (blocker != null) {
            try {
               blocker.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (blocker != null) {
         blocker.close();
      }

   }

   public void sendContent(HttpContent content) throws IOException {
      SharedBlockingCallback.Blocker blocker = this._writeBlocker.acquire();

      try {
         this.sendContent((HttpContent)content, blocker);
         blocker.block();
      } catch (Throwable var6) {
         if (blocker != null) {
            try {
               blocker.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (blocker != null) {
         blocker.close();
      }

   }

   public void sendContent(ByteBuffer content, Callback callback) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("sendContent(buffer={},{})", BufferUtil.toDetailString(content), callback);
      }

      if (this.prepareSendContent(content.remaining(), callback)) {
         this.channelWrite(content, true, new Callback.Nested(callback) {
            public void succeeded() {
               HttpOutput.this.onWriteComplete(true, (Throwable)null);
               super.succeeded();
            }

            public void failed(Throwable x) {
               HttpOutput.this.onWriteComplete(true, x);
               super.failed(x);
            }
         });
      }

   }

   public void sendContent(InputStream in, Callback callback) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("sendContent(stream={},{})", in, callback);
      }

      if (this.prepareSendContent(0, callback)) {
         (new InputStreamWritingCB(in, callback)).iterate();
      }

   }

   public void sendContent(ReadableByteChannel in, Callback callback) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("sendContent(channel={},{})", in, callback);
      }

      if (this.prepareSendContent(0, callback)) {
         (new ReadableByteChannelWritingCB(in, callback)).iterate();
      }

   }

   private boolean prepareSendContent(int len, Callback callback) {
      try (AutoLock l = this._channelState.lock()) {
         if (BufferUtil.hasContent(this._aggregate)) {
            callback.failed(new IOException("cannot sendContent() after write()"));
            return false;
         } else if (this._channel.isCommitted()) {
            callback.failed(new IOException("cannot sendContent(), output already committed"));
            return false;
         } else {
            switch (this._state.ordinal()) {
               case 2:
               case 3:
                  callback.failed(new EofException("Closed"));
                  return false;
               default:
                  this._state = HttpOutput.State.CLOSING;
                  if (this._onError != null) {
                     callback.failed(this._onError);
                     return false;
                  } else if (this._apiState != HttpOutput.ApiState.BLOCKING) {
                     throw new IllegalStateException(this.stateString());
                  } else {
                     this._apiState = HttpOutput.ApiState.PENDING;
                     if (len > 0) {
                        this._written += (long)len;
                     }

                     return true;
                  }
            }
         }
      }
   }

   public void sendContent(HttpContent httpContent, Callback callback) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("sendContent(http={},{})", httpContent, callback);
      }

      ByteBuffer buffer = this._channel.isUseOutputDirectByteBuffers() ? httpContent.getDirectBuffer() : null;
      if (buffer == null) {
         buffer = httpContent.getIndirectBuffer();
      }

      if (buffer != null) {
         this.sendContent(buffer, callback);
      } else {
         ReadableByteChannel rbc = null;

         try {
            rbc = httpContent.getReadableByteChannel();
         } catch (Throwable x) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Unable to access ReadableByteChannel for content {}", httpContent, x);
            }
         }

         if (rbc != null) {
            this.sendContent(rbc, callback);
         } else {
            InputStream in = null;

            try {
               in = httpContent.getInputStream();
            } catch (Throwable x) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Unable to access InputStream for content {}", httpContent, x);
               }
            }

            if (in != null) {
               this.sendContent(in, callback);
            } else {
               Throwable cause = new IllegalArgumentException("unknown content for " + String.valueOf(httpContent));
               this._channel.abort(cause);
               callback.failed(cause);
            }
         }
      }
   }

   public int getBufferSize() {
      return this._bufferSize;
   }

   public void setBufferSize(int size) {
      this._bufferSize = size;
      this._commitSize = size;
   }

   public void onFlushed(long bytes) throws IOException {
      if (this._firstByteNanoTime != -1L && this._firstByteNanoTime != Long.MAX_VALUE) {
         long minDataRate = this.getHttpChannel().getHttpConfiguration().getMinResponseDataRate();
         this._flushed += bytes;
         long elapsed = NanoTime.since(this._firstByteNanoTime);
         long minFlushed = minDataRate * TimeUnit.NANOSECONDS.toMillis(elapsed) / TimeUnit.SECONDS.toMillis(1L);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Flushed bytes min/actual {}/{}", minFlushed, this._flushed);
         }

         if (this._flushed < minFlushed) {
            IOException ioe = new IOException(String.format("Response content data rate < %d B/s", minDataRate));
            this._channel.abort(ioe);
            throw ioe;
         }
      }
   }

   public void recycle() {
      try (AutoLock l = this._channelState.lock()) {
         this._state = HttpOutput.State.OPEN;
         this._apiState = HttpOutput.ApiState.BLOCKING;
         this._softClose = true;
         this._interceptor = this._channel;
         HttpConfiguration config = this._channel.getHttpConfiguration();
         this._bufferSize = config.getOutputBufferSize();
         this._commitSize = config.getOutputAggregationSize();
         if (this._commitSize > this._bufferSize) {
            this._commitSize = this._bufferSize;
         }

         this.releaseBuffer();
         this._written = 0L;
         this._writeListener = null;
         this._onError = null;
         this._firstByteNanoTime = -1L;
         this._flushed = 0L;
         this._closedCallback = null;
      }

   }

   public void resetBuffer() {
      try (AutoLock l = this._channelState.lock()) {
         this._interceptor.resetBuffer();
         if (BufferUtil.hasContent(this._aggregate)) {
            BufferUtil.clear(this._aggregate);
         }

         this._written = 0L;
      }

   }

   public void setWriteListener(WriteListener writeListener) {
      if (!this._channel.getState().isAsync()) {
         throw new IllegalStateException("!ASYNC: " + this.stateString());
      } else {
         boolean wake;
         try (AutoLock l = this._channelState.lock()) {
            if (this._apiState != HttpOutput.ApiState.BLOCKING) {
               throw new IllegalStateException("!OPEN" + this.stateString());
            }

            this._apiState = HttpOutput.ApiState.READY;
            this._writeListener = writeListener;
            wake = this._channel.getState().onWritePossible();
         }

         if (wake) {
            this._channel.execute(this._channel);
         }

      }
   }

   public boolean isReady() {
      try (AutoLock l = this._channelState.lock()) {
         switch (this._apiState.ordinal()) {
            case 0:
            case 3:
               return true;
            case 1:
            case 5:
               return false;
            case 2:
               this._apiState = HttpOutput.ApiState.READY;
               return true;
            case 4:
               this._apiState = HttpOutput.ApiState.UNREADY;
               return false;
            default:
               throw new IllegalStateException(this.stateString());
         }
      }
   }

   public void run() {
      Throwable error = null;

      try (AutoLock l = this._channelState.lock()) {
         if (this._onError != null) {
            error = this._onError;
            this._onError = null;
         }
      }

      try {
         if (error == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("onWritePossible");
            }

            this._writeListener.onWritePossible();
            return;
         }
      } catch (Throwable t) {
         error = t;
      }

      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onError", error);
         }

         this._writeListener.onError(error);
      } catch (Throwable t) {
         if (LOG.isDebugEnabled()) {
            t.addSuppressed(error);
            LOG.debug("Failed in call onError on {}", this._writeListener, t);
         }
      } finally {
         IO.close((OutputStream)this);
      }

   }

   private String stateString() {
      return String.format("s=%s,api=%s,sc=%b,e=%s", this._state, this._apiState, this._softClose, this._onError);
   }

   public String toString() {
      try (AutoLock l = this._channelState.lock()) {
         return String.format("%s@%x{%s}", this.getClass().getSimpleName(), this.hashCode(), this.stateString());
      }
   }

   static enum State {
      OPEN,
      CLOSE,
      CLOSING,
      CLOSED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{OPEN, CLOSE, CLOSING, CLOSED};
      }
   }

   static enum ApiState {
      BLOCKING,
      BLOCKED,
      ASYNC,
      READY,
      PENDING,
      UNREADY;

      // $FF: synthetic method
      private static ApiState[] $values() {
         return new ApiState[]{BLOCKING, BLOCKED, ASYNC, READY, PENDING, UNREADY};
      }
   }

   public interface Interceptor {
      void write(ByteBuffer var1, boolean var2, Callback var3);

      Interceptor getNextInterceptor();

      default void resetBuffer() throws IllegalStateException {
         Interceptor next = this.getNextInterceptor();
         if (next != null) {
            next.resetBuffer();
         }

      }
   }

   private abstract class ChannelWriteCB extends IteratingCallback {
      final boolean _last;

      private ChannelWriteCB(boolean last) {
         this._last = last;
      }

      public Invocable.InvocationType getInvocationType() {
         return Invocable.InvocationType.NON_BLOCKING;
      }

      protected void onCompleteSuccess() {
         HttpOutput.this.onWriteComplete(this._last, (Throwable)null);
      }

      public void onCompleteFailure(Throwable e) {
         HttpOutput.this.onWriteComplete(this._last, e);
      }
   }

   private abstract class NestedChannelWriteCB extends ChannelWriteCB {
      private final Callback _callback;

      private NestedChannelWriteCB(Callback callback, boolean last) {
         super(last);
         this._callback = callback;
      }

      public Invocable.InvocationType getInvocationType() {
         return this._callback.getInvocationType();
      }

      protected void onCompleteSuccess() {
         try {
            super.onCompleteSuccess();
         } finally {
            this._callback.succeeded();
         }

      }

      public void onCompleteFailure(Throwable e) {
         try {
            super.onCompleteFailure(e);
         } catch (Throwable t) {
            if (t != e) {
               e.addSuppressed(t);
            }
         } finally {
            this._callback.failed(e);
         }

      }
   }

   private class AsyncFlush extends ChannelWriteCB {
      private volatile boolean _flushed;

      private AsyncFlush(boolean last) {
         super(last);
      }

      protected IteratingCallback.Action process() throws Exception {
         if (BufferUtil.hasContent(HttpOutput.this._aggregate)) {
            this._flushed = true;
            HttpOutput.this.channelWrite(HttpOutput.this._aggregate, false, this);
            return IteratingCallback.Action.SCHEDULED;
         } else if (!this._flushed) {
            this._flushed = true;
            HttpOutput.this.channelWrite(BufferUtil.EMPTY_BUFFER, false, this);
            return IteratingCallback.Action.SCHEDULED;
         } else {
            return IteratingCallback.Action.SUCCEEDED;
         }
      }
   }

   private class AsyncWrite extends ChannelWriteCB {
      private final ByteBuffer _buffer;
      private final ByteBuffer _slice;
      private final int _len;
      private boolean _completed;

      private AsyncWrite(byte[] b, int off, int len, boolean last) {
         super(last);
         this._buffer = ByteBuffer.wrap(b, off, len);
         this._len = len;
         this._slice = this._len < HttpOutput.this.getBufferSize() ? null : this._buffer.duplicate();
      }

      private AsyncWrite(ByteBuffer buffer, boolean last) {
         super(last);
         this._buffer = buffer;
         this._len = buffer.remaining();
         if (!this._buffer.isDirect() && this._len >= HttpOutput.this.getBufferSize()) {
            this._slice = this._buffer.duplicate();
         } else {
            this._slice = null;
         }

      }

      protected IteratingCallback.Action process() throws Exception {
         if (BufferUtil.hasContent(HttpOutput.this._aggregate)) {
            this._completed = this._len == 0;
            HttpOutput.this.channelWrite(HttpOutput.this._aggregate, this._last && this._completed, this);
            return IteratingCallback.Action.SCHEDULED;
         } else if (!this._last && HttpOutput.this._aggregate != null && this._len < HttpOutput.this.maximizeAggregateSpace() && this._len < HttpOutput.this._commitSize) {
            int position = BufferUtil.flipToFill(HttpOutput.this._aggregate);
            BufferUtil.put(this._buffer, HttpOutput.this._aggregate);
            BufferUtil.flipToFlush(HttpOutput.this._aggregate, position);
            return IteratingCallback.Action.SUCCEEDED;
         } else if (!this._buffer.hasRemaining()) {
            if (this._last && !this._completed) {
               this._completed = true;
               HttpOutput.this.channelWrite(BufferUtil.EMPTY_BUFFER, true, this);
               return IteratingCallback.Action.SCHEDULED;
            } else {
               if (HttpOutput.LOG.isDebugEnabled() && this._completed) {
                  HttpOutput.LOG.debug("EOF of {}", this);
               }

               return IteratingCallback.Action.SUCCEEDED;
            }
         } else if (this._slice == null) {
            this._completed = true;
            HttpOutput.this.channelWrite(this._buffer, this._last, this);
            return IteratingCallback.Action.SCHEDULED;
         } else {
            int p = this._buffer.position();
            int l = Math.min(HttpOutput.this.getBufferSize(), this._buffer.remaining());
            int pl = p + l;
            this._slice.limit(pl);
            this._buffer.position(pl);
            this._slice.position(p);
            this._completed = !this._buffer.hasRemaining();
            HttpOutput.this.channelWrite(this._slice, this._last && this._completed, this);
            return IteratingCallback.Action.SCHEDULED;
         }
      }
   }

   private class InputStreamWritingCB extends NestedChannelWriteCB {
      private final InputStream _in;
      private final ByteBuffer _buffer;
      private boolean _eof;
      private boolean _closed;

      private InputStreamWritingCB(InputStream in, Callback callback) {
         super(callback, true);
         this._in = in;
         this._buffer = HttpOutput.this._channel.getByteBufferPool().acquire(HttpOutput.this.getBufferSize(), false);
      }

      protected IteratingCallback.Action process() throws Exception {
         if (this._eof) {
            if (HttpOutput.LOG.isDebugEnabled()) {
               HttpOutput.LOG.debug("EOF of {}", this);
            }

            if (!this._closed) {
               this._closed = true;
               HttpOutput.this._channel.getByteBufferPool().release(this._buffer);
               IO.close(this._in);
            }

            return IteratingCallback.Action.SUCCEEDED;
         } else {
            int len = 0;

            while(len < this._buffer.capacity() && !this._eof) {
               int r = this._in.read(this._buffer.array(), this._buffer.arrayOffset() + len, this._buffer.capacity() - len);
               if (r < 0) {
                  this._eof = true;
               } else {
                  len += r;
               }
            }

            this._buffer.position(0);
            this._buffer.limit(len);
            HttpOutput var10000 = HttpOutput.this;
            var10000._written += (long)len;
            HttpOutput.this.channelWrite(this._buffer, this._eof, this);
            return IteratingCallback.Action.SCHEDULED;
         }
      }

      public void onCompleteFailure(Throwable x) {
         try {
            HttpOutput.this._channel.getByteBufferPool().release(this._buffer);
         } finally {
            super.onCompleteFailure(x);
         }

      }
   }

   private class ReadableByteChannelWritingCB extends NestedChannelWriteCB {
      private final ReadableByteChannel _in;
      private final ByteBuffer _buffer;
      private boolean _eof;
      private boolean _closed;

      private ReadableByteChannelWritingCB(ReadableByteChannel in, Callback callback) {
         super(callback, true);
         this._in = in;
         this._buffer = HttpOutput.this._channel.getByteBufferPool().acquire(HttpOutput.this.getBufferSize(), HttpOutput.this._channel.isUseOutputDirectByteBuffers());
      }

      protected IteratingCallback.Action process() throws Exception {
         if (this._eof) {
            if (HttpOutput.LOG.isDebugEnabled()) {
               HttpOutput.LOG.debug("EOF of {}", this);
            }

            if (!this._closed) {
               this._closed = true;
               HttpOutput.this._channel.getByteBufferPool().release(this._buffer);
               IO.close((Closeable)this._in);
            }

            return IteratingCallback.Action.SUCCEEDED;
         } else {
            BufferUtil.clearToFill(this._buffer);

            while(this._buffer.hasRemaining() && !this._eof) {
               this._eof = this._in.read(this._buffer) < 0;
            }

            BufferUtil.flipToFlush(this._buffer, 0);
            HttpOutput var10000 = HttpOutput.this;
            var10000._written += (long)this._buffer.remaining();
            HttpOutput.this.channelWrite(this._buffer, this._eof, this);
            return IteratingCallback.Action.SCHEDULED;
         }
      }

      public void onCompleteFailure(Throwable x) {
         HttpOutput.this._channel.getByteBufferPool().release(this._buffer);
         IO.close((Closeable)this._in);
         super.onCompleteFailure(x);
      }
   }

   private static class WriteBlocker extends SharedBlockingCallback {
      private final HttpChannel _channel;

      private WriteBlocker(HttpChannel channel) {
         this._channel = channel;
      }
   }

   private class WriteCompleteCB implements Callback {
      public void succeeded() {
         HttpOutput.this.onWriteComplete(true, (Throwable)null);
      }

      public void failed(Throwable x) {
         HttpOutput.this.onWriteComplete(true, x);
      }

      public Invocable.InvocationType getInvocationType() {
         return Invocable.InvocationType.NON_BLOCKING;
      }
   }
}
