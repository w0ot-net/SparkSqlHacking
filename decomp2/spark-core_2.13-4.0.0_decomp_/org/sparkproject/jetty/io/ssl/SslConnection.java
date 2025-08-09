package org.sparkproject.jetty.io.ssl;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.AbstractEndPoint;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.RetainableByteBuffer;
import org.sparkproject.jetty.io.RetainableByteBufferPool;
import org.sparkproject.jetty.io.WriteFlusher;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Invocable;
import org.sparkproject.jetty.util.thread.Scheduler;

public class SslConnection extends AbstractConnection implements Connection.UpgradeTo {
   private static final Logger LOG = LoggerFactory.getLogger(SslConnection.class);
   private static final String TLS_1_3 = "TLSv1.3";
   private final AutoLock _lock;
   private final AtomicReference _handshake;
   private final List handshakeListeners;
   private final AtomicLong _bytesIn;
   private final AtomicLong _bytesOut;
   private final ByteBufferPool _bufferPool;
   private final RetainableByteBufferPool _retainableByteBufferPool;
   private final SSLEngine _sslEngine;
   private final DecryptedEndPoint _decryptedEndPoint;
   private ByteBuffer _decryptedInput;
   private RetainableByteBuffer _encryptedInput;
   private ByteBuffer _encryptedOutput;
   private final boolean _encryptedDirectBuffers;
   private final boolean _decryptedDirectBuffers;
   private boolean _renegotiationAllowed;
   private int _renegotiationLimit;
   private boolean _closedOutbound;
   private boolean _requireCloseMessage;
   private FlushState _flushState;
   private FillState _fillState;
   private boolean _underflown;
   private final Runnable _runFillable;
   private final Callback _sslReadCallback;

   public SslConnection(ByteBufferPool byteBufferPool, Executor executor, EndPoint endPoint, SSLEngine sslEngine) {
      this(byteBufferPool, executor, endPoint, sslEngine, false, false);
   }

   public SslConnection(ByteBufferPool byteBufferPool, Executor executor, EndPoint endPoint, SSLEngine sslEngine, boolean useDirectBuffersForEncryption, boolean useDirectBuffersForDecryption) {
      this(byteBufferPool.asRetainableByteBufferPool(), byteBufferPool, executor, endPoint, sslEngine, useDirectBuffersForEncryption, useDirectBuffersForDecryption);
   }

   public SslConnection(RetainableByteBufferPool retainableByteBufferPool, ByteBufferPool byteBufferPool, Executor executor, EndPoint endPoint, SSLEngine sslEngine, boolean useDirectBuffersForEncryption, boolean useDirectBuffersForDecryption) {
      super(endPoint, executor);
      this._lock = new AutoLock();
      this._handshake = new AtomicReference(SslConnection.HandshakeState.INITIAL);
      this.handshakeListeners = new ArrayList();
      this._bytesIn = new AtomicLong();
      this._bytesOut = new AtomicLong();
      this._renegotiationLimit = -1;
      this._flushState = SslConnection.FlushState.IDLE;
      this._fillState = SslConnection.FillState.IDLE;
      this._runFillable = new RunnableTask("runFillable") {
         public void run() {
            SslConnection.this._decryptedEndPoint.getFillInterest().fillable();
         }

         public Invocable.InvocationType getInvocationType() {
            return SslConnection.this._decryptedEndPoint.getFillInterest().getCallbackInvocationType();
         }
      };
      this._sslReadCallback = new Callback() {
         public void succeeded() {
            SslConnection.this.onFillable();
         }

         public void failed(Throwable x) {
            SslConnection.this.onFillInterestedFailed(x);
         }

         public Invocable.InvocationType getInvocationType() {
            return SslConnection.this.getDecryptedEndPoint().getFillInterest().getCallbackInvocationType();
         }

         public String toString() {
            return String.format("SSLC.NBReadCB@%x{%s}", SslConnection.this.hashCode(), SslConnection.this);
         }
      };
      this._bufferPool = byteBufferPool;
      this._retainableByteBufferPool = retainableByteBufferPool;
      this._sslEngine = sslEngine;
      this._decryptedEndPoint = this.newDecryptedEndPoint();
      this._encryptedDirectBuffers = useDirectBuffersForEncryption;
      this._decryptedDirectBuffers = useDirectBuffersForDecryption;
   }

   public long getBytesIn() {
      return this._bytesIn.get();
   }

   public long getBytesOut() {
      return this._bytesOut.get();
   }

   public void addHandshakeListener(SslHandshakeListener listener) {
      this.handshakeListeners.add(listener);
   }

   public boolean removeHandshakeListener(SslHandshakeListener listener) {
      return this.handshakeListeners.remove(listener);
   }

   protected DecryptedEndPoint newDecryptedEndPoint() {
      return new DecryptedEndPoint();
   }

   public SSLEngine getSSLEngine() {
      return this._sslEngine;
   }

   public DecryptedEndPoint getDecryptedEndPoint() {
      return this._decryptedEndPoint;
   }

   public boolean isRenegotiationAllowed() {
      return this._renegotiationAllowed;
   }

   public void setRenegotiationAllowed(boolean renegotiationAllowed) {
      this._renegotiationAllowed = renegotiationAllowed;
   }

   public int getRenegotiationLimit() {
      return this._renegotiationLimit;
   }

   public void setRenegotiationLimit(int renegotiationLimit) {
      this._renegotiationLimit = renegotiationLimit;
   }

   public boolean isRequireCloseMessage() {
      return this._requireCloseMessage;
   }

   public void setRequireCloseMessage(boolean requireCloseMessage) {
      this._requireCloseMessage = requireCloseMessage;
   }

   private boolean isHandshakeInitial() {
      return this._handshake.get() == SslConnection.HandshakeState.INITIAL;
   }

   private boolean isHandshakeSucceeded() {
      return this._handshake.get() == SslConnection.HandshakeState.SUCCEEDED;
   }

   private boolean isHandshakeComplete() {
      HandshakeState state = (HandshakeState)this._handshake.get();
      return state == SslConnection.HandshakeState.SUCCEEDED || state == SslConnection.HandshakeState.FAILED;
   }

   private int getApplicationBufferSize() {
      return this.getBufferSize(SSLSession::getApplicationBufferSize);
   }

   private int getPacketBufferSize() {
      return this.getBufferSize(SSLSession::getPacketBufferSize);
   }

   private int getBufferSize(ToIntFunction bufferSizeFn) {
      SSLSession hsSession = this._sslEngine.getHandshakeSession();
      SSLSession session = this._sslEngine.getSession();
      int size = bufferSizeFn.applyAsInt(session);
      if (hsSession != null && hsSession != session) {
         int hsSize = bufferSizeFn.applyAsInt(hsSession);
         return Math.max(hsSize, size);
      } else {
         return size;
      }
   }

   private void acquireEncryptedInput() {
      if (this._encryptedInput == null) {
         this._encryptedInput = this._retainableByteBufferPool.acquire(this.getPacketBufferSize(), this._encryptedDirectBuffers);
      }

   }

   private void acquireEncryptedOutput() {
      if (this._encryptedOutput == null) {
         this._encryptedOutput = this._bufferPool.acquire(this.getPacketBufferSize(), this._encryptedDirectBuffers);
      }

   }

   public void onUpgradeTo(ByteBuffer buffer) {
      this.acquireEncryptedInput();
      BufferUtil.append(this._encryptedInput.getBuffer(), buffer);
   }

   public void onOpen() {
      super.onOpen();
      this.getDecryptedEndPoint().getConnection().onOpen();
   }

   public void onClose(Throwable cause) {
      this._decryptedEndPoint.getConnection().onClose(cause);
      super.onClose(cause);
   }

   public void close() {
      this.getDecryptedEndPoint().getConnection().close();
   }

   public boolean onIdleExpired() {
      return this.getDecryptedEndPoint().getConnection().onIdleExpired();
   }

   public void onFillable() {
      if (LOG.isDebugEnabled()) {
         LOG.debug(">c.onFillable {}", this);
      }

      if (this._decryptedEndPoint.isInputShutdown()) {
         this._decryptedEndPoint.close();
      }

      this._decryptedEndPoint.onFillable();
      if (LOG.isDebugEnabled()) {
         LOG.debug("<c.onFillable {}", this);
      }

   }

   public void onFillInterestedFailed(Throwable cause) {
      this._decryptedEndPoint.onFillableFail((Throwable)(cause == null ? new IOException() : cause));
   }

   protected SSLEngineResult wrap(SSLEngine sslEngine, ByteBuffer[] input, ByteBuffer output) throws SSLException {
      return sslEngine.wrap(input, output);
   }

   protected SSLEngineResult unwrap(SSLEngine sslEngine, ByteBuffer input, ByteBuffer output) throws SSLException {
      return sslEngine.unwrap(input, output);
   }

   public String toConnectionString() {
      ByteBuffer b = this._encryptedInput == null ? null : this._encryptedInput.getBuffer();
      int ei = b == null ? -1 : b.remaining();
      b = this._encryptedOutput;
      int eo = b == null ? -1 : b.remaining();
      b = this._decryptedInput;
      int di = b == null ? -1 : b.remaining();
      Connection connection = this._decryptedEndPoint.getConnection();
      return String.format("%s@%x{%s,eio=%d/%d,di=%d,fill=%s,flush=%s}~>%s=>%s", this.getClass().getSimpleName(), this.hashCode(), this._sslEngine.getHandshakeStatus(), ei, eo, di, this._fillState, this._flushState, this._decryptedEndPoint.toEndPointString(), connection instanceof AbstractConnection ? ((AbstractConnection)connection).toConnectionString() : connection);
   }

   private void releaseEmptyEncryptedInputBuffer() {
      if (!this._lock.isHeldByCurrentThread()) {
         throw new IllegalStateException();
      } else {
         if (this._encryptedInput != null && !this._encryptedInput.hasRemaining()) {
            this._encryptedInput.release();
            this._encryptedInput = null;
         }

      }
   }

   private void releaseEmptyDecryptedInputBuffer() {
      if (!this._lock.isHeldByCurrentThread()) {
         throw new IllegalStateException();
      } else {
         if (this._decryptedInput != null && !this._decryptedInput.hasRemaining()) {
            this._bufferPool.release(this._decryptedInput);
            this._decryptedInput = null;
         }

      }
   }

   private void discardInputBuffers() {
      if (!this._lock.isHeldByCurrentThread()) {
         throw new IllegalStateException();
      } else {
         if (this._encryptedInput != null) {
            this._encryptedInput.clear();
         }

         BufferUtil.clear(this._decryptedInput);
         this.releaseEmptyInputBuffers();
      }
   }

   private void releaseEmptyInputBuffers() {
      this.releaseEmptyEncryptedInputBuffer();
      this.releaseEmptyDecryptedInputBuffer();
   }

   private void discardEncryptedOutputBuffer() {
      if (!this._lock.isHeldByCurrentThread()) {
         throw new IllegalStateException();
      } else {
         BufferUtil.clear(this._encryptedOutput);
         this.releaseEmptyEncryptedOutputBuffer();
      }
   }

   private void releaseEmptyEncryptedOutputBuffer() {
      if (!this._lock.isHeldByCurrentThread()) {
         throw new IllegalStateException();
      } else {
         if (this._encryptedOutput != null && !this._encryptedOutput.hasRemaining()) {
            this._bufferPool.release(this._encryptedOutput);
            this._encryptedOutput = null;
         }

      }
   }

   protected int networkFill(ByteBuffer input) throws IOException {
      return this.getEndPoint().fill(input);
   }

   protected boolean networkFlush(ByteBuffer output) throws IOException {
      return this.getEndPoint().flush(output);
   }

   private static enum HandshakeState {
      INITIAL,
      HANDSHAKE,
      SUCCEEDED,
      FAILED;

      // $FF: synthetic method
      private static HandshakeState[] $values() {
         return new HandshakeState[]{INITIAL, HANDSHAKE, SUCCEEDED, FAILED};
      }
   }

   private static enum FillState {
      IDLE,
      INTERESTED,
      WAIT_FOR_FLUSH;

      // $FF: synthetic method
      private static FillState[] $values() {
         return new FillState[]{IDLE, INTERESTED, WAIT_FOR_FLUSH};
      }
   }

   private static enum FlushState {
      IDLE,
      WRITING,
      WAIT_FOR_FILL;

      // $FF: synthetic method
      private static FlushState[] $values() {
         return new FlushState[]{IDLE, WRITING, WAIT_FOR_FILL};
      }
   }

   public class DecryptedEndPoint extends AbstractEndPoint implements EndPoint.Wrapper {
      private final Callback _incompleteWriteCallback = new IncompleteWriteCallback();
      private Throwable _failure;

      public DecryptedEndPoint() {
         super((Scheduler)null);
         super.setIdleTimeout(-1L);
      }

      public EndPoint unwrap() {
         return SslConnection.this.getEndPoint();
      }

      public long getIdleTimeout() {
         return SslConnection.this.getEndPoint().getIdleTimeout();
      }

      public void setIdleTimeout(long idleTimeout) {
         SslConnection.this.getEndPoint().setIdleTimeout(idleTimeout);
      }

      public boolean isOpen() {
         return SslConnection.this.getEndPoint().isOpen();
      }

      public SocketAddress getLocalSocketAddress() {
         return SslConnection.this.getEndPoint().getLocalSocketAddress();
      }

      public SocketAddress getRemoteSocketAddress() {
         return SslConnection.this.getEndPoint().getRemoteSocketAddress();
      }

      public WriteFlusher getWriteFlusher() {
         return super.getWriteFlusher();
      }

      protected void onFillable() {
         try {
            boolean waitingForFill;
            try (AutoLock l = SslConnection.this._lock.lock()) {
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("onFillable {}", SslConnection.this);
               }

               SslConnection.this._fillState = SslConnection.FillState.IDLE;
               waitingForFill = SslConnection.this._flushState == SslConnection.FlushState.WAIT_FOR_FILL;
            }

            this.getFillInterest().fillable();
            if (waitingForFill) {
               try (AutoLock l = SslConnection.this._lock.lock()) {
                  waitingForFill = SslConnection.this._flushState == SslConnection.FlushState.WAIT_FOR_FILL;
               }

               if (waitingForFill) {
                  this.fill(BufferUtil.EMPTY_BUFFER);
               }
            }
         } catch (Throwable e) {
            this.close(e);
         }

      }

      protected void onFillableFail(Throwable failure) {
         boolean fail = false;

         try (AutoLock l = SslConnection.this._lock.lock()) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug("onFillableFail {}", SslConnection.this, failure);
            }

            SslConnection.this._fillState = SslConnection.FillState.IDLE;
            if (SslConnection.this._flushState == SslConnection.FlushState.WAIT_FOR_FILL) {
               SslConnection.this._flushState = SslConnection.FlushState.IDLE;
               fail = true;
            }
         }

         this.getFillInterest().onFail(failure);
         if (fail && !this.getWriteFlusher().onFail(failure)) {
            this.close(failure);
         }

      }

      public void setConnection(Connection connection) {
         if (connection instanceof AbstractConnection) {
            AbstractConnection c = (AbstractConnection)connection;
            int appBufferSize = SslConnection.this.getApplicationBufferSize();
            if (c.getInputBufferSize() < appBufferSize) {
               c.setInputBufferSize(appBufferSize);
            }
         }

         super.setConnection(connection);
      }

      public SslConnection getSslConnection() {
         return SslConnection.this;
      }

      public int fill(ByteBuffer buffer) throws IOException {
         try (AutoLock l = SslConnection.this._lock.lock()) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug(">fill {}", SslConnection.this);
            }

            int filled = -2;

            try {
               if (SslConnection.this._fillState != SslConnection.FillState.IDLE) {
                  filled = 0;
                  return 0;
               } else if (BufferUtil.hasContent(SslConnection.this._decryptedInput)) {
                  return filled = BufferUtil.append(buffer, SslConnection.this._decryptedInput);
               } else {
                  while(true) {
                     SSLEngineResult.HandshakeStatus status = SslConnection.this._sslEngine.getHandshakeStatus();
                     if (SslConnection.LOG.isDebugEnabled()) {
                        SslConnection.LOG.debug("fill {}", status);
                     }

                     switch (status) {
                        case NEED_UNWRAP:
                        case NOT_HANDSHAKING:
                           SslConnection.this.acquireEncryptedInput();
                           int appBufferSize = SslConnection.this.getApplicationBufferSize();
                           ByteBuffer appIn;
                           if (SslConnection.this._decryptedInput == null) {
                              if (BufferUtil.space(buffer) > appBufferSize) {
                                 appIn = buffer;
                              } else {
                                 appIn = SslConnection.this._decryptedInput = SslConnection.this._bufferPool.acquire(appBufferSize, SslConnection.this._decryptedDirectBuffers);
                              }
                           } else {
                              appIn = SslConnection.this._decryptedInput;
                           }

                           int netFilled = SslConnection.this.networkFill(SslConnection.this._encryptedInput.getBuffer());
                           if (netFilled > 0) {
                              SslConnection.this._bytesIn.addAndGet((long)netFilled);
                           }

                           if (SslConnection.LOG.isDebugEnabled()) {
                              SslConnection.LOG.debug("net filled={}", netFilled);
                           }

                           if (netFilled < 0 && SslConnection.this.isHandshakeInitial() && (SslConnection.this._encryptedInput == null || SslConnection.this._encryptedInput.isEmpty())) {
                              this.closeInbound();
                           }

                           if (netFilled > 0 && !SslConnection.this.isHandshakeComplete() && this.isOutboundDone()) {
                              throw new SSLHandshakeException("Closed during handshake");
                           }

                           if (SslConnection.this._handshake.compareAndSet(SslConnection.HandshakeState.INITIAL, SslConnection.HandshakeState.HANDSHAKE) && SslConnection.LOG.isDebugEnabled()) {
                              SslConnection.LOG.debug("fill starting handshake {}", SslConnection.this);
                           }

                           int pos = BufferUtil.flipToFill(appIn);

                           SSLEngineResult unwrapResult;
                           try {
                              SslConnection.this._underflown = false;
                              unwrapResult = SslConnection.this.unwrap(SslConnection.this._sslEngine, SslConnection.this._encryptedInput.getBuffer(), appIn);
                           } finally {
                              BufferUtil.flipToFlush(appIn, pos);
                           }

                           if (SslConnection.LOG.isDebugEnabled()) {
                              SslConnection.LOG.debug("unwrap net_filled={} {} encryptedBuffer={} unwrapBuffer={} appBuffer={}", new Object[]{netFilled, StringUtil.replace(unwrapResult.toString(), '\n', ' '), SslConnection.this._encryptedInput, BufferUtil.toDetailString(appIn), BufferUtil.toDetailString(buffer)});
                           }

                           SSLEngineResult.Status unwrap = unwrapResult.getStatus();
                           if (unwrap == Status.OK && unwrapResult.bytesConsumed() == 0 && unwrapResult.bytesProduced() == 0) {
                              unwrap = Status.BUFFER_UNDERFLOW;
                           }

                           switch (unwrap) {
                              case CLOSED:
                                 Throwable failure = this._failure;
                                 if (failure != null) {
                                    this.rethrow(failure);
                                 }

                                 filled = -1;
                                 return -1;
                              case BUFFER_UNDERFLOW:
                                 if (!BufferUtil.compact(SslConnection.this._encryptedInput.getBuffer())) {
                                    if (BufferUtil.space(SslConnection.this._encryptedInput.getBuffer()) == 0) {
                                       BufferUtil.clear(SslConnection.this._encryptedInput.getBuffer());
                                       throw new SSLHandshakeException("Encrypted buffer max length exceeded");
                                    }

                                    if (netFilled <= 0) {
                                       SslConnection.this._underflown = true;
                                       if (netFilled < 0 && SslConnection.this._sslEngine.getUseClientMode()) {
                                          Throwable closeFailure = this.closeInbound();
                                          if (SslConnection.this._flushState == SslConnection.FlushState.WAIT_FOR_FILL) {
                                             Throwable handshakeFailure = new SSLHandshakeException("Abruptly closed by peer");
                                             if (closeFailure != null) {
                                                handshakeFailure.addSuppressed(closeFailure);
                                             }

                                             throw handshakeFailure;
                                          }

                                          filled = -1;
                                          return -1;
                                       }

                                       filled = netFilled;
                                       return netFilled;
                                    }
                                 }
                                 continue;
                              case BUFFER_OVERFLOW:
                                 if (BufferUtil.isEmpty(SslConnection.this._decryptedInput) && appBufferSize < SslConnection.this.getApplicationBufferSize()) {
                                    SslConnection.this.releaseEmptyDecryptedInputBuffer();
                                    continue;
                                 }

                                 throw new IllegalStateException("Unexpected unwrap result " + String.valueOf(unwrap));
                              case OK:
                                 if (unwrapResult.getHandshakeStatus() == HandshakeStatus.FINISHED) {
                                    this.handshakeSucceeded();
                                 }

                                 if (this.isRenegotiating() && !this.allowRenegotiate()) {
                                    filled = -1;
                                    return -1;
                                 }

                                 if (unwrapResult.bytesProduced() > 0) {
                                    if (appIn == buffer) {
                                       return filled = unwrapResult.bytesProduced();
                                    }

                                    return filled = BufferUtil.append(buffer, SslConnection.this._decryptedInput);
                                 }
                                 continue;
                              default:
                                 throw new IllegalStateException("Unexpected unwrap result " + String.valueOf(unwrap));
                           }
                        case NEED_TASK:
                           SslConnection.this._sslEngine.getDelegatedTask().run();
                           break;
                        case NEED_WRAP:
                           if (SslConnection.this._flushState == SslConnection.FlushState.IDLE && this.flush(BufferUtil.EMPTY_BUFFER)) {
                              Throwable failure = this._failure;
                              if (failure != null) {
                                 this.rethrow(failure);
                              }

                              if (SslConnection.this._sslEngine.isInboundDone()) {
                                 filled = -1;
                                 return -1;
                              }
                              break;
                           }

                           filled = 0;
                           return 0;
                        default:
                           throw new IllegalStateException("Unexpected HandshakeStatus " + String.valueOf(status));
                     }
                  }
               }
            } catch (Throwable x) {
               SslConnection.this.discardInputBuffers();
               Throwable f = this.handleException(x, "fill");
               Throwable failure = this.handshakeFailed(f);
               if (SslConnection.this._flushState == SslConnection.FlushState.WAIT_FOR_FILL) {
                  SslConnection.this._flushState = SslConnection.FlushState.IDLE;
                  SslConnection.this.getExecutor().execute(() -> SslConnection.this._decryptedEndPoint.getWriteFlusher().onFail(failure));
               }

               throw failure;
            } finally {
               SslConnection.this.releaseEmptyInputBuffers();
               if (SslConnection.this._flushState == SslConnection.FlushState.WAIT_FOR_FILL) {
                  SslConnection.this._flushState = SslConnection.FlushState.IDLE;
                  SslConnection.this.getExecutor().execute(() -> SslConnection.this._decryptedEndPoint.getWriteFlusher().completeWrite());
               }

               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("<fill f={} uf={} {}", new Object[]{filled, SslConnection.this._underflown, SslConnection.this});
               }

            }
         } catch (Throwable x) {
            this.close(x);
            this.rethrow(x);
            throw new AssertionError();
         }
      }

      protected void needsFillInterest() {
         try {
            ByteBuffer write = null;
            boolean interest = false;

            boolean fillable;
            try (AutoLock l = SslConnection.this._lock.lock()) {
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug(">needFillInterest s={}/{} uf={} ei={} di={} {}", new Object[]{SslConnection.this._flushState, SslConnection.this._fillState, SslConnection.this._underflown, SslConnection.this._encryptedInput, BufferUtil.toDetailString(SslConnection.this._decryptedInput), SslConnection.this});
               }

               if (SslConnection.this._fillState != SslConnection.FillState.IDLE) {
                  return;
               }

               fillable = BufferUtil.hasContent(SslConnection.this._decryptedInput) || SslConnection.this._encryptedInput != null && SslConnection.this._encryptedInput.hasRemaining() && !SslConnection.this._underflown;
               SSLEngineResult.HandshakeStatus status = SslConnection.this._sslEngine.getHandshakeStatus();
               switch (status) {
                  case NEED_UNWRAP:
                  case NOT_HANDSHAKING:
                     if (!fillable) {
                        interest = true;
                        SslConnection.this._fillState = SslConnection.FillState.INTERESTED;
                        if (SslConnection.this._flushState == SslConnection.FlushState.IDLE && BufferUtil.hasContent(SslConnection.this._encryptedOutput)) {
                           SslConnection.this._flushState = SslConnection.FlushState.WRITING;
                           write = SslConnection.this._encryptedOutput;
                        }
                     }
                     break;
                  case NEED_TASK:
                     fillable = true;
                     break;
                  case NEED_WRAP:
                     if (!fillable) {
                        SslConnection.this._fillState = SslConnection.FillState.WAIT_FOR_FLUSH;
                        if (SslConnection.this._flushState == SslConnection.FlushState.IDLE) {
                           SslConnection.this._flushState = SslConnection.FlushState.WRITING;
                           write = BufferUtil.hasContent(SslConnection.this._encryptedOutput) ? SslConnection.this._encryptedOutput : BufferUtil.EMPTY_BUFFER;
                        }
                     }
                     break;
                  default:
                     throw new IllegalStateException("Unexpected HandshakeStatus " + String.valueOf(status));
               }

               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("<needFillInterest s={}/{} f={} i={} w={}", new Object[]{SslConnection.this._flushState, SslConnection.this._fillState, fillable, interest, BufferUtil.toDetailString(write)});
               }
            }

            if (write != null) {
               SslConnection.this.getEndPoint().write(this._incompleteWriteCallback, write);
            } else if (fillable) {
               SslConnection.this.getExecutor().execute(SslConnection.this._runFillable);
            } else if (interest) {
               this.ensureFillInterested();
            }

         } catch (Throwable var9) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug(SslConnection.this.toString(), var9);
            }

            this.close(var9);
            throw var9;
         }
      }

      private void handshakeSucceeded() throws SSLException {
         if (SslConnection.this._handshake.compareAndSet(SslConnection.HandshakeState.HANDSHAKE, SslConnection.HandshakeState.SUCCEEDED)) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug("handshake succeeded {} {} {}/{}", new Object[]{SslConnection.this, SslConnection.this._sslEngine.getUseClientMode() ? "client" : "resumed server", SslConnection.this._sslEngine.getSession().getProtocol(), SslConnection.this._sslEngine.getSession().getCipherSuite()});
            }

            this.notifyHandshakeSucceeded(SslConnection.this._sslEngine);
         } else if (SslConnection.this.isHandshakeSucceeded() && SslConnection.this._renegotiationLimit > 0) {
            --SslConnection.this._renegotiationLimit;
         }

      }

      private Throwable handshakeFailed(Throwable failure) {
         if (SslConnection.this._handshake.compareAndSet(SslConnection.HandshakeState.HANDSHAKE, SslConnection.HandshakeState.FAILED)) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug("handshake failed {} {}", SslConnection.this, failure);
            }

            if (!(failure instanceof SSLHandshakeException)) {
               failure = (new SSLHandshakeException(failure.getMessage())).initCause(failure);
            }

            this.notifyHandshakeFailed(SslConnection.this._sslEngine, failure);
         }

         return failure;
      }

      private void terminateInput() {
         try {
            SslConnection.this._sslEngine.closeInbound();
         } catch (Throwable x) {
            SslConnection.LOG.trace("IGNORED", x);
         }

      }

      private Throwable closeInbound() throws SSLException {
         SSLEngineResult.HandshakeStatus handshakeStatus = SslConnection.this._sslEngine.getHandshakeStatus();

         try {
            SslConnection.this._sslEngine.closeInbound();
            return null;
         } catch (SSLException x) {
            if (handshakeStatus == HandshakeStatus.NOT_HANDSHAKING && SslConnection.this.isRequireCloseMessage()) {
               throw x;
            } else {
               SslConnection.LOG.trace("IGNORED", x);
               return x;
            }
         } catch (Throwable x) {
            SslConnection.LOG.trace("IGNORED", x);
            return x;
         }
      }

      public boolean flush(ByteBuffer... appOuts) throws IOException {
         try (AutoLock l = SslConnection.this._lock.lock()) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug(">flush {}", SslConnection.this);
               int i = 0;

               for(ByteBuffer b : appOuts) {
                  SslConnection.LOG.debug("flush b[{}]={}", i++, BufferUtil.toDetailString(b));
               }
            }

            Boolean result = null;

            try {
               if (SslConnection.this._encryptedOutput != null) {
                  int remaining = SslConnection.this._encryptedOutput.remaining();
                  if (remaining > 0) {
                     boolean flushed = SslConnection.this.networkFlush(SslConnection.this._encryptedOutput);
                     int written = remaining - SslConnection.this._encryptedOutput.remaining();
                     if (written > 0) {
                        SslConnection.this._bytesOut.addAndGet((long)written);
                     }

                     if (!flushed) {
                        return false;
                     }
                  }
               }

               boolean isEmpty = BufferUtil.isEmpty(appOuts);
               if (SslConnection.this._flushState != SslConnection.FlushState.IDLE) {
                  return result = false;
               } else {
                  while(true) {
                     SSLEngineResult.HandshakeStatus status = SslConnection.this._sslEngine.getHandshakeStatus();
                     if (SslConnection.LOG.isDebugEnabled()) {
                        SslConnection.LOG.debug("flush {}", status);
                     }

                     switch (status) {
                        case NEED_UNWRAP:
                           if (!SslConnection.this.isHandshakeInitial() || !this.isOutboundDone()) {
                              if (SslConnection.this._fillState == SslConnection.FillState.IDLE) {
                                 int filled = this.fill(BufferUtil.EMPTY_BUFFER);
                                 if (SslConnection.this._sslEngine.getHandshakeStatus() != status) {
                                    break;
                                 }

                                 if (filled < 0) {
                                    throw new IOException("Broken pipe");
                                 }
                              }

                              return result = isEmpty;
                           }
                        case NOT_HANDSHAKING:
                        case NEED_WRAP:
                           int packetBufferSize = SslConnection.this.getPacketBufferSize();
                           SslConnection.this.acquireEncryptedOutput();
                           if (SslConnection.this._handshake.compareAndSet(SslConnection.HandshakeState.INITIAL, SslConnection.HandshakeState.HANDSHAKE) && SslConnection.LOG.isDebugEnabled()) {
                              SslConnection.LOG.debug("flush starting handshake {}", SslConnection.this);
                           }

                           BufferUtil.compact(SslConnection.this._encryptedOutput);
                           int pos = BufferUtil.flipToFill(SslConnection.this._encryptedOutput);

                           SSLEngineResult wrapResult;
                           try {
                              wrapResult = SslConnection.this.wrap(SslConnection.this._sslEngine, appOuts, SslConnection.this._encryptedOutput);
                           } finally {
                              BufferUtil.flipToFlush(SslConnection.this._encryptedOutput, pos);
                           }

                           if (SslConnection.LOG.isDebugEnabled()) {
                              SslConnection.LOG.debug("wrap {} {} ioDone={}/{}", new Object[]{StringUtil.replace(wrapResult.toString(), '\n', ' '), BufferUtil.toSummaryString(SslConnection.this._encryptedOutput), SslConnection.this._sslEngine.isInboundDone(), SslConnection.this._sslEngine.isOutboundDone()});
                           }

                           isEmpty = BufferUtil.isEmpty(appOuts);
                           boolean flushed = true;
                           if (SslConnection.this._encryptedOutput != null) {
                              int remaining = SslConnection.this._encryptedOutput.remaining();
                              if (remaining > 0) {
                                 flushed = SslConnection.this.networkFlush(SslConnection.this._encryptedOutput);
                                 int written = remaining - SslConnection.this._encryptedOutput.remaining();
                                 if (written > 0) {
                                    SslConnection.this._bytesOut.addAndGet((long)written);
                                 }
                              }
                           }

                           if (SslConnection.LOG.isDebugEnabled()) {
                              SslConnection.LOG.debug("net flushed={}, ac={}", flushed, isEmpty);
                           }

                           SSLEngineResult.Status wrap = wrapResult.getStatus();
                           switch (wrap) {
                              case CLOSED:
                                 if (!flushed) {
                                    return result = false;
                                 }

                                 SslConnection.this.getEndPoint().shutdownOutput();
                                 if (isEmpty) {
                                    return result = true;
                                 }

                                 throw new IOException("Broken pipe");
                              case BUFFER_UNDERFLOW:
                              default:
                                 throw new IllegalStateException("Unexpected wrap result " + String.valueOf(wrap));
                              case BUFFER_OVERFLOW:
                                 if (!flushed) {
                                    return result = false;
                                 }

                                 if (packetBufferSize >= SslConnection.this.getPacketBufferSize()) {
                                    throw new IllegalStateException("Unexpected wrap result " + String.valueOf(wrap));
                                 }

                                 SslConnection.this.releaseEmptyEncryptedOutputBuffer();
                                 continue;
                              case OK:
                                 if (wrapResult.getHandshakeStatus() == HandshakeStatus.FINISHED) {
                                    this.handshakeSucceeded();
                                 }

                                 if (this.isRenegotiating() && !this.allowRenegotiate()) {
                                    SslConnection.this.getEndPoint().shutdownOutput();
                                    if (!isEmpty || !BufferUtil.isEmpty(SslConnection.this._encryptedOutput)) {
                                       throw new IOException("Broken pipe");
                                    }

                                    return result = true;
                                 }

                                 if (!flushed) {
                                    return result = false;
                                 }

                                 if (!isEmpty || wrapResult.getHandshakeStatus() == HandshakeStatus.NEED_WRAP && wrapResult.bytesProduced() != 0) {
                                    if (SslConnection.this.getEndPoint().isOutputShutdown()) {
                                       return false;
                                    }
                                    continue;
                                 }

                                 return result = true;
                           }
                        case NEED_TASK:
                           SslConnection.this._sslEngine.getDelegatedTask().run();
                           break;
                        default:
                           throw new IllegalStateException("Unexpected HandshakeStatus " + String.valueOf(status));
                     }
                  }
               }
            } catch (Throwable x) {
               SslConnection.this.discardEncryptedOutputBuffer();
               Throwable failure = this.handleException(x, "flush");
               throw this.handshakeFailed(failure);
            } finally {
               SslConnection.this.releaseEmptyEncryptedOutputBuffer();
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("<flush {} {}", result, SslConnection.this);
               }

            }
         } catch (Throwable x) {
            this.close(x);
            this.rethrow(x);
            throw new AssertionError();
         }
      }

      protected void onIncompleteFlush() {
         try {
            boolean fillInterest = false;
            ByteBuffer write = null;

            try (AutoLock l = SslConnection.this._lock.lock()) {
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug(">onIncompleteFlush {} {}", SslConnection.this, BufferUtil.toDetailString(SslConnection.this._encryptedOutput));
               }

               if (SslConnection.this._flushState != SslConnection.FlushState.IDLE) {
                  return;
               }

               label95:
               while(true) {
                  SSLEngineResult.HandshakeStatus status = SslConnection.this._sslEngine.getHandshakeStatus();
                  switch (status) {
                     case NEED_UNWRAP:
                        if (BufferUtil.hasContent(SslConnection.this._encryptedOutput)) {
                           write = SslConnection.this._encryptedOutput;
                           SslConnection.this._flushState = SslConnection.FlushState.WRITING;
                           break label95;
                        }

                        if (SslConnection.this._fillState != SslConnection.FillState.IDLE) {
                           SslConnection.this._flushState = SslConnection.FlushState.WAIT_FOR_FILL;
                           break label95;
                        }

                        try {
                           int filled = this.fill(BufferUtil.EMPTY_BUFFER);
                           if (SslConnection.this._sslEngine.getHandshakeStatus() != status) {
                              break;
                           }

                           if (filled < 0) {
                              throw new IOException("Broken pipe");
                           }
                        } catch (IOException e) {
                           SslConnection.LOG.debug("Incomplete flush?", e);
                           this.close(e);
                           write = BufferUtil.EMPTY_BUFFER;
                           SslConnection.this._flushState = SslConnection.FlushState.WRITING;
                           break label95;
                        }

                        fillInterest = true;
                        SslConnection.this._fillState = SslConnection.FillState.INTERESTED;
                        SslConnection.this._flushState = SslConnection.FlushState.WAIT_FOR_FILL;
                        break label95;
                     case NOT_HANDSHAKING:
                     case NEED_TASK:
                     case NEED_WRAP:
                        write = BufferUtil.hasContent(SslConnection.this._encryptedOutput) ? SslConnection.this._encryptedOutput : BufferUtil.EMPTY_BUFFER;
                        SslConnection.this._flushState = SslConnection.FlushState.WRITING;
                        break label95;
                     default:
                        throw new IllegalStateException("Unexpected HandshakeStatus " + String.valueOf(status));
                  }
               }

               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("<onIncompleteFlush s={}/{} fi={} w={}", new Object[]{SslConnection.this._flushState, SslConnection.this._fillState, fillInterest, BufferUtil.toDetailString(write)});
               }
            }

            if (write != null) {
               SslConnection.this.getEndPoint().write(this._incompleteWriteCallback, write);
            } else if (fillInterest) {
               this.ensureFillInterested();
            }

         } catch (Throwable var9) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug(SslConnection.this.toString(), var9);
            }

            this.close(var9);
            throw var9;
         }
      }

      public void doShutdownOutput() {
         this.doShutdownOutput(false);
      }

      private void doShutdownOutput(boolean close) {
         EndPoint endPoint = SslConnection.this.getEndPoint();

         try {
            boolean flush = false;

            try (AutoLock l = SslConnection.this._lock.lock()) {
               boolean ishut = endPoint.isInputShutdown();
               boolean oshut = endPoint.isOutputShutdown();
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("shutdownOutput: {} oshut={}, ishut={}", new Object[]{SslConnection.this, oshut, ishut});
               }

               this.closeOutbound();
               if (!SslConnection.this._closedOutbound) {
                  SslConnection.this._closedOutbound = true;
                  flush = !oshut;
               }

               if (!close) {
                  close = ishut;
               }
            }

            if (flush && !this.flush(BufferUtil.EMPTY_BUFFER) && !close) {
               ByteBuffer write = null;

               try (AutoLock l = SslConnection.this._lock.lock()) {
                  if (BufferUtil.hasContent(SslConnection.this._encryptedOutput)) {
                     write = SslConnection.this._encryptedOutput;
                     SslConnection.this._flushState = SslConnection.FlushState.WRITING;
                  }
               }

               if (write != null) {
                  endPoint.write(Callback.from((Runnable)(() -> {
                     try (AutoLock l = SslConnection.this._lock.lock()) {
                        SslConnection.this._flushState = SslConnection.FlushState.IDLE;
                        SslConnection.this.releaseEmptyEncryptedOutputBuffer();
                     }

                  }), (Consumer)((t) -> this.disconnect())), write);
               }
            }

            if (close) {
               this.disconnect();
            } else {
               this.ensureFillInterested();
            }
         } catch (Throwable x) {
            if (SslConnection.LOG.isTraceEnabled()) {
               SslConnection.LOG.trace("IGNORED", x);
            }

            this.disconnect();
         }

      }

      private void disconnect() {
         try (AutoLock l = SslConnection.this._lock.lock()) {
            SslConnection.this.discardEncryptedOutputBuffer();
         }

         SslConnection.this.getEndPoint().close();
      }

      private void closeOutbound() {
         try {
            SslConnection.this._sslEngine.closeOutbound();
         } catch (Throwable x) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug("Unable to close outbound", x);
            }
         }

      }

      private void ensureFillInterested() {
         if (SslConnection.LOG.isDebugEnabled()) {
            SslConnection.LOG.debug("ensureFillInterested {}", SslConnection.this);
         }

         SslConnection.this.tryFillInterested(SslConnection.this._sslReadCallback);
      }

      public boolean isOutputShutdown() {
         return this.isOutboundDone() || SslConnection.this.getEndPoint().isOutputShutdown();
      }

      private boolean isOutboundDone() {
         try {
            return SslConnection.this._sslEngine.isOutboundDone();
         } catch (Throwable x) {
            SslConnection.LOG.trace("IGNORED", x);
            return true;
         }
      }

      public void doClose() {
         try (AutoLock l = SslConnection.this._lock.lock()) {
            SslConnection.this.discardInputBuffers();
         }

         this.doShutdownOutput(true);
         super.doClose();
      }

      public Object getTransport() {
         return SslConnection.this.getEndPoint();
      }

      public boolean isInputShutdown() {
         return BufferUtil.isEmpty(SslConnection.this._decryptedInput) && (SslConnection.this.getEndPoint().isInputShutdown() || this.isInboundDone());
      }

      private boolean isInboundDone() {
         try {
            return SslConnection.this._sslEngine.isInboundDone();
         } catch (Throwable x) {
            SslConnection.LOG.trace("IGNORED", x);
            return true;
         }
      }

      private void notifyHandshakeSucceeded(SSLEngine sslEngine) throws SSLException {
         SslHandshakeListener.Event event = null;

         for(SslHandshakeListener listener : SslConnection.this.handshakeListeners) {
            if (event == null) {
               event = new SslHandshakeListener.Event(sslEngine);
            }

            try {
               listener.handshakeSucceeded(event);
            } catch (SSLException x) {
               throw x;
            } catch (Throwable x) {
               SslConnection.LOG.info("Exception while notifying listener {}", listener, x);
            }
         }

      }

      private void notifyHandshakeFailed(SSLEngine sslEngine, Throwable failure) {
         SslHandshakeListener.Event event = null;

         for(SslHandshakeListener listener : SslConnection.this.handshakeListeners) {
            if (event == null) {
               event = new SslHandshakeListener.Event(sslEngine);
            }

            try {
               listener.handshakeFailed(event, failure);
            } catch (Throwable x) {
               SslConnection.LOG.info("Exception while notifying listener {}", listener, x);
            }
         }

      }

      private boolean isRenegotiating() {
         if (!SslConnection.this.isHandshakeComplete()) {
            return false;
         } else if (this.isTLS13()) {
            return false;
         } else {
            return SslConnection.this._sslEngine.getHandshakeStatus() != HandshakeStatus.NOT_HANDSHAKING;
         }
      }

      private boolean allowRenegotiate() {
         if (!SslConnection.this.isRenegotiationAllowed()) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug("Renegotiation denied {}", SslConnection.this);
            }

            this.terminateInput();
            return false;
         } else if (SslConnection.this.getRenegotiationLimit() == 0) {
            if (SslConnection.LOG.isDebugEnabled()) {
               SslConnection.LOG.debug("Renegotiation limit exceeded {}", SslConnection.this);
            }

            this.terminateInput();
            return false;
         } else {
            return true;
         }
      }

      private boolean isTLS13() {
         String protocol = SslConnection.this._sslEngine.getSession().getProtocol();
         return "TLSv1.3".equals(protocol);
      }

      private Throwable handleException(Throwable x, String context) {
         try (AutoLock l = SslConnection.this._lock.lock()) {
            if (this._failure == null) {
               this._failure = x;
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("{} stored {} exception", new Object[]{this, context, x});
               }
            } else if (x != this._failure && x.getCause() != this._failure) {
               this._failure.addSuppressed(x);
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("{} suppressed {} exception", new Object[]{this, context, x});
               }
            }

            return this._failure;
         }
      }

      private void rethrow(Throwable x) throws IOException {
         if (x instanceof RuntimeException) {
            throw (RuntimeException)x;
         } else if (x instanceof Error) {
            throw (Error)x;
         } else if (x instanceof IOException) {
            throw (IOException)x;
         } else {
            throw new IOException(x);
         }
      }

      public String toString() {
         return String.format("%s@%x[%s]", this.getClass().getSimpleName(), this.hashCode(), this.toEndPointString());
      }

      private final class IncompleteWriteCallback implements Callback, Invocable {
         public void succeeded() {
            boolean fillable;
            boolean interested;
            try (AutoLock l = SslConnection.this._lock.lock()) {
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("IncompleteWriteCB succeeded {}", SslConnection.this);
               }

               SslConnection.this.releaseEmptyEncryptedOutputBuffer();
               SslConnection.this._flushState = SslConnection.FlushState.IDLE;
               interested = SslConnection.this._fillState == SslConnection.FillState.INTERESTED;
               fillable = SslConnection.this._fillState == SslConnection.FillState.WAIT_FOR_FLUSH;
               if (fillable) {
                  SslConnection.this._fillState = SslConnection.FillState.IDLE;
               }
            }

            if (interested) {
               DecryptedEndPoint.this.ensureFillInterested();
            } else if (fillable) {
               SslConnection.this._decryptedEndPoint.getFillInterest().fillable();
            }

            SslConnection.this._decryptedEndPoint.getWriteFlusher().completeWrite();
         }

         public void failed(Throwable x) {
            boolean failFillInterest;
            try (AutoLock l = SslConnection.this._lock.lock()) {
               if (SslConnection.LOG.isDebugEnabled()) {
                  SslConnection.LOG.debug("IncompleteWriteCB failed {}", SslConnection.this, x);
               }

               SslConnection.this.discardEncryptedOutputBuffer();
               SslConnection.this._flushState = SslConnection.FlushState.IDLE;
               failFillInterest = SslConnection.this._fillState == SslConnection.FillState.WAIT_FOR_FLUSH || SslConnection.this._fillState == SslConnection.FillState.INTERESTED;
               if (failFillInterest) {
                  SslConnection.this._fillState = SslConnection.FillState.IDLE;
               }
            }

            SslConnection.this.getExecutor().execute(() -> {
               if (failFillInterest) {
                  SslConnection.this._decryptedEndPoint.getFillInterest().onFail(x);
               }

               SslConnection.this._decryptedEndPoint.getWriteFlusher().onFail(x);
            });
         }

         public Invocable.InvocationType getInvocationType() {
            return SslConnection.this._decryptedEndPoint.getWriteFlusher().getCallbackInvocationType();
         }

         public String toString() {
            return String.format("SSL@%h.DEP.writeCallback", SslConnection.this);
         }
      }
   }

   private abstract class RunnableTask implements Invocable.Task {
      private final String _operation;

      protected RunnableTask(String op) {
         this._operation = op;
      }

      public String toString() {
         return String.format("SSL:%s:%s:%s", SslConnection.this, this._operation, this.getInvocationType());
      }
   }
}
