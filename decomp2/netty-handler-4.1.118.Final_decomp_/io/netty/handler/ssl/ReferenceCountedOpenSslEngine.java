package io.netty.handler.ssl;

import [Ljava.security.cert.Certificate;;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.util.LazyJavaxX509Certificate;
import io.netty.handler.ssl.util.LazyX509Certificate;
import io.netty.internal.tcnative.AsyncTask;
import io.netty.internal.tcnative.Buffer;
import io.netty.internal.tcnative.SSL;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import javax.security.cert.X509Certificate;

public class ReferenceCountedOpenSslEngine extends SSLEngine implements ReferenceCounted, ApplicationProtocolAccessor {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ReferenceCountedOpenSslEngine.class);
   private static final ResourceLeakDetector leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ReferenceCountedOpenSslEngine.class);
   private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV2 = 0;
   private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV3 = 1;
   private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1 = 2;
   private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_1 = 3;
   private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_2 = 4;
   private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_3 = 5;
   private static final int[] OPENSSL_OP_NO_PROTOCOLS;
   static final int MAX_PLAINTEXT_LENGTH;
   static final int MAX_RECORD_SIZE;
   private static final SSLEngineResult NEED_UNWRAP_OK;
   private static final SSLEngineResult NEED_UNWRAP_CLOSED;
   private static final SSLEngineResult NEED_WRAP_OK;
   private static final SSLEngineResult NEED_WRAP_CLOSED;
   private static final SSLEngineResult CLOSED_NOT_HANDSHAKING;
   private long ssl;
   private long networkBIO;
   private HandshakeState handshakeState;
   private boolean receivedShutdown;
   private volatile boolean destroyed;
   private volatile String applicationProtocol;
   private volatile boolean needTask;
   private boolean hasTLSv13Cipher;
   private boolean sessionSet;
   private final ResourceLeakTracker leak;
   private final AbstractReferenceCounted refCnt;
   private final Set enabledProtocols;
   private volatile ClientAuth clientAuth;
   private String endpointIdentificationAlgorithm;
   private Object algorithmConstraints;
   private List sniHostNames;
   private volatile Collection matchers;
   private boolean isInboundDone;
   private boolean outboundClosed;
   final boolean jdkCompatibilityMode;
   private final boolean clientMode;
   final ByteBufAllocator alloc;
   private final OpenSslEngineMap engineMap;
   private final OpenSslApplicationProtocolNegotiator apn;
   private final ReferenceCountedOpenSslContext parentContext;
   private final OpenSslInternalSession session;
   private final ByteBuffer[] singleSrcBuffer;
   private final ByteBuffer[] singleDstBuffer;
   private final boolean enableOcsp;
   private int maxWrapOverhead;
   private int maxWrapBufferSize;
   private Throwable pendingException;
   private static final X509Certificate[] JAVAX_CERTS_NOT_SUPPORTED;

   ReferenceCountedOpenSslEngine(ReferenceCountedOpenSslContext context, ByteBufAllocator alloc, String peerHost, int peerPort, boolean jdkCompatibilityMode, boolean leakDetection, String endpointIdentificationAlgorithm) {
      super(peerHost, peerPort);
      this.handshakeState = ReferenceCountedOpenSslEngine.HandshakeState.NOT_STARTED;
      this.refCnt = new AbstractReferenceCounted() {
         public ReferenceCounted touch(Object hint) {
            if (ReferenceCountedOpenSslEngine.this.leak != null) {
               ReferenceCountedOpenSslEngine.this.leak.record(hint);
            }

            return ReferenceCountedOpenSslEngine.this;
         }

         protected void deallocate() {
            ReferenceCountedOpenSslEngine.this.shutdown();
            if (ReferenceCountedOpenSslEngine.this.leak != null) {
               boolean closed = ReferenceCountedOpenSslEngine.this.leak.close(ReferenceCountedOpenSslEngine.this);

               assert closed;
            }

            ReferenceCountedOpenSslEngine.this.parentContext.release();
         }
      };
      this.enabledProtocols = new LinkedHashSet();
      this.clientAuth = ClientAuth.NONE;
      this.singleSrcBuffer = new ByteBuffer[1];
      this.singleDstBuffer = new ByteBuffer[1];
      OpenSsl.ensureAvailability();
      this.engineMap = context.engineMap;
      this.enableOcsp = context.enableOcsp;
      this.jdkCompatibilityMode = jdkCompatibilityMode;
      this.alloc = (ByteBufAllocator)ObjectUtil.checkNotNull(alloc, "alloc");
      this.apn = (OpenSslApplicationProtocolNegotiator)context.applicationProtocolNegotiator();
      this.clientMode = context.isClient();
      this.endpointIdentificationAlgorithm = endpointIdentificationAlgorithm;
      if (PlatformDependent.javaVersion() >= 7) {
         this.session = new ExtendedOpenSslSession(new DefaultOpenSslSession(context.sessionContext())) {
            private String[] peerSupportedSignatureAlgorithms;
            private List requestedServerNames;

            public List getRequestedServerNames() {
               if (ReferenceCountedOpenSslEngine.this.clientMode) {
                  return Java8SslUtils.getSniHostNames(ReferenceCountedOpenSslEngine.this.sniHostNames);
               } else {
                  synchronized(ReferenceCountedOpenSslEngine.this) {
                     if (this.requestedServerNames == null) {
                        if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                           this.requestedServerNames = Collections.emptyList();
                        } else {
                           String name = SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl);
                           if (name == null) {
                              this.requestedServerNames = Collections.emptyList();
                           } else {
                              this.requestedServerNames = Java8SslUtils.getSniHostName(SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl).getBytes(CharsetUtil.UTF_8));
                           }
                        }
                     }

                     return this.requestedServerNames;
                  }
               }
            }

            public String[] getPeerSupportedSignatureAlgorithms() {
               synchronized(ReferenceCountedOpenSslEngine.this) {
                  if (this.peerSupportedSignatureAlgorithms == null) {
                     if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                        this.peerSupportedSignatureAlgorithms = EmptyArrays.EMPTY_STRINGS;
                     } else {
                        String[] algs = SSL.getSigAlgs(ReferenceCountedOpenSslEngine.this.ssl);
                        if (algs == null) {
                           this.peerSupportedSignatureAlgorithms = EmptyArrays.EMPTY_STRINGS;
                        } else {
                           Set<String> algorithmList = new LinkedHashSet(algs.length);

                           for(String alg : algs) {
                              String converted = SignatureAlgorithmConverter.toJavaName(alg);
                              if (converted != null) {
                                 algorithmList.add(converted);
                              }
                           }

                           this.peerSupportedSignatureAlgorithms = (String[])algorithmList.toArray(EmptyArrays.EMPTY_STRINGS);
                        }
                     }
                  }

                  return (String[])this.peerSupportedSignatureAlgorithms.clone();
               }
            }

            public List getStatusResponses() {
               byte[] ocspResponse = null;
               if (ReferenceCountedOpenSslEngine.this.enableOcsp && ReferenceCountedOpenSslEngine.this.clientMode) {
                  synchronized(ReferenceCountedOpenSslEngine.this) {
                     if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                        ocspResponse = SSL.getOcspResponse(ReferenceCountedOpenSslEngine.this.ssl);
                     }
                  }
               }

               return ocspResponse == null ? Collections.emptyList() : Collections.singletonList(ocspResponse);
            }
         };
      } else {
         this.session = new DefaultOpenSslSession(context.sessionContext());
      }

      if (!context.sessionContext().useKeyManager()) {
         this.session.setLocalCertificate(context.keyCertChain);
      }

      Lock readerLock = context.ctxLock.readLock();
      readerLock.lock();

      long finalSsl;
      try {
         finalSsl = SSL.newSSL(context.ctx, !context.isClient());
      } finally {
         readerLock.unlock();
      }

      synchronized(this) {
         this.ssl = finalSsl;

         try {
            this.networkBIO = SSL.bioNewByteBuffer(this.ssl, context.getBioNonApplicationBufferSize());
            this.setClientAuth(this.clientMode ? ClientAuth.NONE : context.clientAuth);

            assert context.protocols != null;

            this.hasTLSv13Cipher = context.hasTLSv13Cipher;
            this.setEnabledProtocols(context.protocols);
            if (this.clientMode && SslUtils.isValidHostNameForSNI(peerHost)) {
               if (PlatformDependent.javaVersion() >= 8) {
                  if (Java8SslUtils.isValidHostNameForSNI(peerHost)) {
                     SSL.setTlsExtHostName(this.ssl, peerHost);
                     this.sniHostNames = Collections.singletonList(peerHost);
                  }
               } else {
                  SSL.setTlsExtHostName(this.ssl, peerHost);
                  this.sniHostNames = Collections.singletonList(peerHost);
               }
            }

            if (this.enableOcsp) {
               SSL.enableOcsp(this.ssl);
            }

            if (!jdkCompatibilityMode) {
               SSL.setMode(this.ssl, SSL.getMode(this.ssl) | SSL.SSL_MODE_ENABLE_PARTIAL_WRITE);
            }

            if (isProtocolEnabled(SSL.getOptions(this.ssl), SSL.SSL_OP_NO_TLSv1_3, "TLSv1.3")) {
               boolean enableTickets = this.clientMode ? ReferenceCountedOpenSslContext.CLIENT_ENABLE_SESSION_TICKET_TLSV13 : ReferenceCountedOpenSslContext.SERVER_ENABLE_SESSION_TICKET_TLSV13;
               if (enableTickets) {
                  SSL.clearOptions(this.ssl, SSL.SSL_OP_NO_TICKET);
               }
            }

            if (OpenSsl.isBoringSSL() && this.clientMode) {
               SSL.setRenegotiateMode(this.ssl, SSL.SSL_RENEGOTIATE_ONCE);
            }

            this.calculateMaxWrapOverhead();
            this.configureEndpointVerification(endpointIdentificationAlgorithm);
         } catch (Throwable cause) {
            this.shutdown();
            PlatformDependent.throwException(cause);
         }
      }

      this.parentContext = context;
      this.parentContext.retain();
      this.leak = leakDetection ? leakDetector.track(this) : null;
   }

   final synchronized String[] authMethods() {
      return this.isDestroyed() ? EmptyArrays.EMPTY_STRINGS : SSL.authenticationMethods(this.ssl);
   }

   final boolean setKeyMaterial(OpenSslKeyMaterial keyMaterial) throws Exception {
      synchronized(this) {
         if (this.isDestroyed()) {
            return false;
         }

         SSL.setKeyMaterial(this.ssl, keyMaterial.certificateChainAddress(), keyMaterial.privateKeyAddress());
      }

      this.session.setLocalCertificate(keyMaterial.certificateChain());
      return true;
   }

   final synchronized SecretKeySpec masterKey() {
      return this.isDestroyed() ? null : new SecretKeySpec(SSL.getMasterKey(this.ssl), "AES");
   }

   synchronized boolean isSessionReused() {
      return this.isDestroyed() ? false : SSL.isSessionReused(this.ssl);
   }

   public void setOcspResponse(byte[] response) {
      if (!this.enableOcsp) {
         throw new IllegalStateException("OCSP stapling is not enabled");
      } else if (this.clientMode) {
         throw new IllegalStateException("Not a server SSLEngine");
      } else {
         synchronized(this) {
            if (!this.isDestroyed()) {
               SSL.setOcspResponse(this.ssl, response);
            }

         }
      }
   }

   public byte[] getOcspResponse() {
      if (!this.enableOcsp) {
         throw new IllegalStateException("OCSP stapling is not enabled");
      } else if (!this.clientMode) {
         throw new IllegalStateException("Not a client SSLEngine");
      } else {
         synchronized(this) {
            return this.isDestroyed() ? EmptyArrays.EMPTY_BYTES : SSL.getOcspResponse(this.ssl);
         }
      }
   }

   public final int refCnt() {
      return this.refCnt.refCnt();
   }

   public final ReferenceCounted retain() {
      this.refCnt.retain();
      return this;
   }

   public final ReferenceCounted retain(int increment) {
      this.refCnt.retain(increment);
      return this;
   }

   public final ReferenceCounted touch() {
      this.refCnt.touch();
      return this;
   }

   public final ReferenceCounted touch(Object hint) {
      this.refCnt.touch(hint);
      return this;
   }

   public final boolean release() {
      return this.refCnt.release();
   }

   public final boolean release(int decrement) {
      return this.refCnt.release(decrement);
   }

   public String getApplicationProtocol() {
      return this.applicationProtocol;
   }

   public String getHandshakeApplicationProtocol() {
      return this.applicationProtocol;
   }

   public final synchronized SSLSession getHandshakeSession() {
      switch (this.handshakeState) {
         case NOT_STARTED:
         case FINISHED:
            return null;
         default:
            return this.session;
      }
   }

   public final synchronized long sslPointer() {
      return this.ssl;
   }

   public final synchronized void shutdown() {
      if (!this.destroyed) {
         this.destroyed = true;
         if (this.engineMap != null) {
            this.engineMap.remove(this.ssl);
         }

         SSL.freeSSL(this.ssl);
         this.ssl = this.networkBIO = 0L;
         this.isInboundDone = this.outboundClosed = true;
      }

      SSL.clearError();
   }

   private int writePlaintextData(ByteBuffer src, int len) {
      int pos = src.position();
      int limit = src.limit();
      int sslWrote;
      if (src.isDirect()) {
         sslWrote = SSL.writeToSSL(this.ssl, bufferAddress(src) + (long)pos, len);
         if (sslWrote > 0) {
            src.position(pos + sslWrote);
         }
      } else {
         ByteBuf buf = this.alloc.directBuffer(len);

         try {
            src.limit(pos + len);
            buf.setBytes(0, src);
            src.limit(limit);
            sslWrote = SSL.writeToSSL(this.ssl, OpenSsl.memoryAddress(buf), len);
            if (sslWrote > 0) {
               src.position(pos + sslWrote);
            } else {
               src.position(pos);
            }
         } finally {
            buf.release();
         }
      }

      return sslWrote;
   }

   synchronized void bioSetFd(int fd) {
      if (!this.isDestroyed()) {
         SSL.bioSetFd(this.ssl, fd);
      }

   }

   private ByteBuf writeEncryptedData(ByteBuffer src, int len) throws SSLException {
      int pos = src.position();
      if (src.isDirect()) {
         SSL.bioSetByteBuffer(this.networkBIO, bufferAddress(src) + (long)pos, len, false);
      } else {
         ByteBuf buf = this.alloc.directBuffer(len);

         try {
            int limit = src.limit();
            src.limit(pos + len);
            buf.writeBytes(src);
            src.position(pos);
            src.limit(limit);
            SSL.bioSetByteBuffer(this.networkBIO, OpenSsl.memoryAddress(buf), len, false);
            return buf;
         } catch (Throwable cause) {
            buf.release();
            PlatformDependent.throwException(cause);
         }
      }

      return null;
   }

   private int readPlaintextData(ByteBuffer dst) throws SSLException {
      int pos = dst.position();
      int sslRead;
      if (dst.isDirect()) {
         sslRead = SSL.readFromSSL(this.ssl, bufferAddress(dst) + (long)pos, dst.limit() - pos);
         if (sslRead > 0) {
            dst.position(pos + sslRead);
         }
      } else {
         int limit = dst.limit();
         int len = Math.min(this.maxEncryptedPacketLength0(), limit - pos);
         ByteBuf buf = this.alloc.directBuffer(len);

         try {
            sslRead = SSL.readFromSSL(this.ssl, OpenSsl.memoryAddress(buf), len);
            if (sslRead > 0) {
               dst.limit(pos + sslRead);
               buf.getBytes(buf.readerIndex(), dst);
               dst.limit(limit);
            }
         } finally {
            buf.release();
         }
      }

      return sslRead;
   }

   final synchronized int maxWrapOverhead() {
      return this.maxWrapOverhead;
   }

   final synchronized int maxEncryptedPacketLength() {
      return this.maxEncryptedPacketLength0();
   }

   final int maxEncryptedPacketLength0() {
      return this.maxWrapOverhead + MAX_PLAINTEXT_LENGTH;
   }

   final int calculateMaxLengthForWrap(int plaintextLength, int numComponents) {
      return (int)Math.min((long)this.maxWrapBufferSize, (long)plaintextLength + (long)this.maxWrapOverhead * (long)numComponents);
   }

   final int calculateOutNetBufSize(int plaintextLength, int numComponents) {
      return (int)Math.min(2147483647L, (long)plaintextLength + (long)this.maxWrapOverhead * (long)numComponents);
   }

   final synchronized int sslPending() {
      return this.sslPending0();
   }

   private void calculateMaxWrapOverhead() {
      this.maxWrapOverhead = SSL.getMaxWrapOverhead(this.ssl);
      this.maxWrapBufferSize = this.jdkCompatibilityMode ? this.maxEncryptedPacketLength0() : this.maxEncryptedPacketLength0() << 4;
   }

   private int sslPending0() {
      return this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.FINISHED ? 0 : SSL.sslPending(this.ssl);
   }

   private boolean isBytesAvailableEnoughForWrap(int bytesAvailable, int plaintextLength, int numComponents) {
      return (long)bytesAvailable - (long)this.maxWrapOverhead * (long)numComponents >= (long)plaintextLength;
   }

   public final SSLEngineResult wrap(ByteBuffer[] srcs, int offset, int length, ByteBuffer dst) throws SSLException {
      ObjectUtil.checkNotNullWithIAE(srcs, "srcs");
      ObjectUtil.checkNotNullWithIAE(dst, "dst");
      if (offset < srcs.length && offset + length <= srcs.length) {
         if (dst.isReadOnly()) {
            throw new ReadOnlyBufferException();
         } else {
            synchronized(this) {
               if (this.isOutboundDone()) {
                  return !this.isInboundDone() && !this.isDestroyed() ? NEED_UNWRAP_CLOSED : CLOSED_NOT_HANDSHAKING;
               } else {
                  int bytesProduced = 0;
                  ByteBuf bioReadCopyBuf = null;

                  SSLEngineResult var9;
                  try {
                     if (dst.isDirect()) {
                        SSL.bioSetByteBuffer(this.networkBIO, bufferAddress(dst) + (long)dst.position(), dst.remaining(), true);
                     } else {
                        bioReadCopyBuf = this.alloc.directBuffer(dst.remaining());
                        SSL.bioSetByteBuffer(this.networkBIO, OpenSsl.memoryAddress(bioReadCopyBuf), bioReadCopyBuf.writableBytes(), true);
                     }

                     int bioLengthBefore = SSL.bioLengthByteBuffer(this.networkBIO);
                     if (!this.outboundClosed) {
                        SSLEngineResult.HandshakeStatus status = HandshakeStatus.NOT_HANDSHAKING;
                        HandshakeState oldHandshakeState = this.handshakeState;
                        if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.FINISHED) {
                           if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.STARTED_EXPLICITLY) {
                              this.handshakeState = ReferenceCountedOpenSslEngine.HandshakeState.STARTED_IMPLICITLY;
                           }

                           bytesProduced = SSL.bioFlushByteBuffer(this.networkBIO);
                           if (this.pendingException != null) {
                              if (bytesProduced > 0) {
                                 SSLEngineResult var35 = this.newResult(HandshakeStatus.NEED_WRAP, 0, bytesProduced);
                                 return var35;
                              }

                              SSLEngineResult var34 = this.newResult(this.handshakeException(), 0, 0);
                              return var34;
                           }

                           status = this.handshake();
                           bytesProduced = bioLengthBefore - SSL.bioLengthByteBuffer(this.networkBIO);
                           if (status == HandshakeStatus.NEED_TASK) {
                              SSLEngineResult var33 = this.newResult(status, 0, bytesProduced);
                              return var33;
                           }

                           if (bytesProduced > 0) {
                              SSLEngineResult var32 = this.newResult(this.mayFinishHandshake(status != HandshakeStatus.FINISHED ? (bytesProduced == bioLengthBefore ? HandshakeStatus.NEED_WRAP : this.getHandshakeStatus(SSL.bioLengthNonApplication(this.networkBIO))) : HandshakeStatus.FINISHED), 0, bytesProduced);
                              return var32;
                           }

                           if (status == HandshakeStatus.NEED_UNWRAP) {
                              SSLEngineResult var31 = this.isOutboundDone() ? NEED_UNWRAP_CLOSED : NEED_UNWRAP_OK;
                              return var31;
                           }

                           if (this.outboundClosed) {
                              bytesProduced = SSL.bioFlushByteBuffer(this.networkBIO);
                              SSLEngineResult var30 = this.newResultMayFinishHandshake(status, 0, bytesProduced);
                              return var30;
                           }
                        }

                        int endOffset = offset + length;
                        if (this.jdkCompatibilityMode || oldHandshakeState != ReferenceCountedOpenSslEngine.HandshakeState.FINISHED) {
                           int srcsLen = 0;

                           for(int i = offset; i < endOffset; ++i) {
                              ByteBuffer src = srcs[i];
                              if (src == null) {
                                 throw new IllegalArgumentException("srcs[" + i + "] is null");
                              }

                              if (srcsLen != MAX_PLAINTEXT_LENGTH) {
                                 srcsLen += src.remaining();
                                 if (srcsLen > MAX_PLAINTEXT_LENGTH || srcsLen < 0) {
                                    srcsLen = MAX_PLAINTEXT_LENGTH;
                                 }
                              }
                           }

                           if (!this.isBytesAvailableEnoughForWrap(dst.remaining(), srcsLen, 1)) {
                              SSLEngineResult var41 = new SSLEngineResult(Status.BUFFER_OVERFLOW, this.getHandshakeStatus(), 0, 0);
                              return var41;
                           }
                        }

                        int bytesConsumed = 0;

                        assert bytesProduced == 0;

                        bytesProduced = SSL.bioFlushByteBuffer(this.networkBIO);
                        if (bytesProduced > 0) {
                           SSLEngineResult var40 = this.newResultMayFinishHandshake(status, bytesConsumed, bytesProduced);
                           return var40;
                        }

                        if (this.pendingException != null) {
                           Throwable error = this.pendingException;
                           this.pendingException = null;
                           this.shutdown();
                           throw new SSLException(error);
                        }

                        for(; offset < endOffset; ++offset) {
                           ByteBuffer src = srcs[offset];
                           int remaining = src.remaining();
                           if (remaining != 0) {
                              int bytesWritten;
                              if (this.jdkCompatibilityMode) {
                                 bytesWritten = this.writePlaintextData(src, Math.min(remaining, MAX_PLAINTEXT_LENGTH - bytesConsumed));
                              } else {
                                 int availableCapacityForWrap = dst.remaining() - bytesProduced - this.maxWrapOverhead;
                                 if (availableCapacityForWrap <= 0) {
                                    SSLEngineResult var45 = new SSLEngineResult(Status.BUFFER_OVERFLOW, this.getHandshakeStatus(), bytesConsumed, bytesProduced);
                                    return var45;
                                 }

                                 bytesWritten = this.writePlaintextData(src, Math.min(remaining, availableCapacityForWrap));
                              }

                              int pendingNow = SSL.bioLengthByteBuffer(this.networkBIO);
                              bytesProduced += bioLengthBefore - pendingNow;
                              bioLengthBefore = pendingNow;
                              if (bytesWritten <= 0) {
                                 int sslError = SSL.getError(this.ssl, bytesWritten);
                                 if (sslError != SSL.SSL_ERROR_ZERO_RETURN) {
                                    if (sslError == SSL.SSL_ERROR_WANT_READ) {
                                       SSLEngineResult var50 = this.newResult(HandshakeStatus.NEED_UNWRAP, bytesConsumed, bytesProduced);
                                       return var50;
                                    }

                                    if (sslError != SSL.SSL_ERROR_WANT_WRITE) {
                                       if (sslError != SSL.SSL_ERROR_WANT_X509_LOOKUP && sslError != SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY && sslError != SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                                          throw this.shutdownWithError("SSL_write", sslError, SSL.getLastErrorNumber());
                                       }

                                       SSLEngineResult var49 = this.newResult(HandshakeStatus.NEED_TASK, bytesConsumed, bytesProduced);
                                       return var49;
                                    }

                                    if (bytesProduced > 0) {
                                       SSLEngineResult var48 = this.newResult(HandshakeStatus.NEED_WRAP, bytesConsumed, bytesProduced);
                                       return var48;
                                    }

                                    SSLEngineResult var47 = this.newResult(Status.BUFFER_OVERFLOW, status, bytesConsumed, bytesProduced);
                                    return var47;
                                 }

                                 if (!this.receivedShutdown) {
                                    this.closeAll();
                                    bytesProduced += pendingNow - SSL.bioLengthByteBuffer(this.networkBIO);
                                    SSLEngineResult.HandshakeStatus hs = this.mayFinishHandshake(status != HandshakeStatus.FINISHED ? (bytesProduced == dst.remaining() ? HandshakeStatus.NEED_WRAP : this.getHandshakeStatus(SSL.bioLengthNonApplication(this.networkBIO))) : HandshakeStatus.FINISHED);
                                    SSLEngineResult var19 = this.newResult(hs, bytesConsumed, bytesProduced);
                                    return var19;
                                 }

                                 SSLEngineResult hs = this.newResult(HandshakeStatus.NOT_HANDSHAKING, bytesConsumed, bytesProduced);
                                 return hs;
                              }

                              bytesConsumed += bytesWritten;
                              if (this.jdkCompatibilityMode || bytesProduced == dst.remaining()) {
                                 SSLEngineResult var17 = this.newResultMayFinishHandshake(status, bytesConsumed, bytesProduced);
                                 return var17;
                              }
                           }
                        }

                        SSLEngineResult var38 = this.newResultMayFinishHandshake(status, bytesConsumed, bytesProduced);
                        return var38;
                     }

                     if (this.isBytesAvailableEnoughForWrap(dst.remaining(), 2, 1)) {
                        bytesProduced = SSL.bioFlushByteBuffer(this.networkBIO);
                        if (bytesProduced <= 0) {
                           var9 = this.newResultMayFinishHandshake(HandshakeStatus.NOT_HANDSHAKING, 0, 0);
                           return var9;
                        }

                        if (!this.doSSLShutdown()) {
                           var9 = this.newResultMayFinishHandshake(HandshakeStatus.NOT_HANDSHAKING, 0, bytesProduced);
                           return var9;
                        }

                        bytesProduced = bioLengthBefore - SSL.bioLengthByteBuffer(this.networkBIO);
                        var9 = this.newResultMayFinishHandshake(HandshakeStatus.NEED_WRAP, 0, bytesProduced);
                        return var9;
                     }

                     var9 = new SSLEngineResult(Status.BUFFER_OVERFLOW, this.getHandshakeStatus(), 0, 0);
                  } finally {
                     SSL.bioClearByteBuffer(this.networkBIO);
                     if (bioReadCopyBuf == null) {
                        dst.position(dst.position() + bytesProduced);
                     } else {
                        assert bioReadCopyBuf.readableBytes() <= dst.remaining() : "The destination buffer " + dst + " didn't have enough remaining space to hold the encrypted content in " + bioReadCopyBuf;

                        dst.put(bioReadCopyBuf.internalNioBuffer(bioReadCopyBuf.readerIndex(), bytesProduced));
                        bioReadCopyBuf.release();
                     }

                  }

                  return var9;
               }
            }
         }
      } else {
         throw new IndexOutOfBoundsException("offset: " + offset + ", length: " + length + " (expected: offset <= offset + length <= srcs.length (" + srcs.length + "))");
      }
   }

   private SSLEngineResult newResult(SSLEngineResult.HandshakeStatus hs, int bytesConsumed, int bytesProduced) {
      return this.newResult(Status.OK, hs, bytesConsumed, bytesProduced);
   }

   private SSLEngineResult newResult(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus hs, int bytesConsumed, int bytesProduced) {
      if (this.isOutboundDone()) {
         if (this.isInboundDone()) {
            hs = HandshakeStatus.NOT_HANDSHAKING;
            this.shutdown();
         }

         return new SSLEngineResult(Status.CLOSED, hs, bytesConsumed, bytesProduced);
      } else {
         if (hs == HandshakeStatus.NEED_TASK) {
            this.needTask = true;
         }

         return new SSLEngineResult(status, hs, bytesConsumed, bytesProduced);
      }
   }

   private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus hs, int bytesConsumed, int bytesProduced) throws SSLException {
      return this.newResult(this.mayFinishHandshake(hs, bytesConsumed, bytesProduced), bytesConsumed, bytesProduced);
   }

   private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus hs, int bytesConsumed, int bytesProduced) throws SSLException {
      return this.newResult(status, this.mayFinishHandshake(hs, bytesConsumed, bytesProduced), bytesConsumed, bytesProduced);
   }

   private SSLException shutdownWithError(String operation, int sslError, int error) {
      if (logger.isDebugEnabled()) {
         String errorString = SSL.getErrorString((long)error);
         logger.debug("{} failed with {}: OpenSSL error: {} {}", new Object[]{operation, sslError, error, errorString});
      }

      this.shutdown();
      SSLException exception = this.newSSLExceptionForError(error);
      if (this.pendingException != null) {
         exception.initCause(this.pendingException);
         this.pendingException = null;
      }

      return exception;
   }

   private SSLEngineResult handleUnwrapException(int bytesConsumed, int bytesProduced, SSLException e) throws SSLException {
      int lastError = SSL.getLastErrorNumber();
      if (lastError != 0) {
         return this.sslReadErrorResult(SSL.SSL_ERROR_SSL, lastError, bytesConsumed, bytesProduced);
      } else {
         throw e;
      }
   }

   public final SSLEngineResult unwrap(ByteBuffer[] srcs, int srcsOffset, int srcsLength, ByteBuffer[] dsts, int dstsOffset, int dstsLength) throws SSLException {
      ObjectUtil.checkNotNullWithIAE(srcs, "srcs");
      if (srcsOffset < srcs.length && srcsOffset + srcsLength <= srcs.length) {
         ObjectUtil.checkNotNullWithIAE(dsts, "dsts");
         if (dstsOffset < dsts.length && dstsOffset + dstsLength <= dsts.length) {
            long capacity = 0L;
            int dstsEndOffset = dstsOffset + dstsLength;

            for(int i = dstsOffset; i < dstsEndOffset; ++i) {
               ByteBuffer dst = (ByteBuffer)ObjectUtil.checkNotNullArrayParam(dsts[i], i, "dsts");
               if (dst.isReadOnly()) {
                  throw new ReadOnlyBufferException();
               }

               capacity += (long)dst.remaining();
            }

            int srcsEndOffset = srcsOffset + srcsLength;
            long len = 0L;

            for(int i = srcsOffset; i < srcsEndOffset; ++i) {
               ByteBuffer src = (ByteBuffer)ObjectUtil.checkNotNullArrayParam(srcs[i], i, "srcs");
               len += (long)src.remaining();
            }

            synchronized(this) {
               if (this.isInboundDone()) {
                  return !this.isOutboundDone() && !this.isDestroyed() ? NEED_WRAP_CLOSED : CLOSED_NOT_HANDSHAKING;
               } else {
                  SSLEngineResult.HandshakeStatus status = HandshakeStatus.NOT_HANDSHAKING;
                  HandshakeState oldHandshakeState = this.handshakeState;
                  if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.FINISHED) {
                     if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.STARTED_EXPLICITLY) {
                        this.handshakeState = ReferenceCountedOpenSslEngine.HandshakeState.STARTED_IMPLICITLY;
                     }

                     status = this.handshake();
                     if (status == HandshakeStatus.NEED_TASK) {
                        return this.newResult(status, 0, 0);
                     }

                     if (status == HandshakeStatus.NEED_WRAP) {
                        return NEED_WRAP_OK;
                     }

                     if (this.isInboundDone) {
                        return NEED_WRAP_CLOSED;
                     }
                  }

                  int sslPending = this.sslPending0();
                  int packetLength;
                  if (!this.jdkCompatibilityMode && oldHandshakeState == ReferenceCountedOpenSslEngine.HandshakeState.FINISHED) {
                     if (len == 0L && sslPending <= 0) {
                        return this.newResultMayFinishHandshake(Status.BUFFER_UNDERFLOW, status, 0, 0);
                     }

                     if (capacity == 0L) {
                        return this.newResultMayFinishHandshake(Status.BUFFER_OVERFLOW, status, 0, 0);
                     }

                     packetLength = (int)Math.min(2147483647L, len);
                  } else {
                     if (len < 5L) {
                        return this.newResultMayFinishHandshake(Status.BUFFER_UNDERFLOW, status, 0, 0);
                     }

                     packetLength = SslUtils.getEncryptedPacketLength(srcs, srcsOffset);
                     if (packetLength == -2) {
                        throw new NotSslRecordException("not an SSL/TLS record");
                     }

                     assert packetLength >= 0;

                     int packetLengthDataOnly = packetLength - 5;
                     if ((long)packetLengthDataOnly > capacity) {
                        if (packetLengthDataOnly > MAX_RECORD_SIZE) {
                           throw new SSLException("Illegal packet length: " + packetLengthDataOnly + " > " + this.session.getApplicationBufferSize());
                        }

                        this.session.tryExpandApplicationBufferSize(packetLengthDataOnly);
                        return this.newResultMayFinishHandshake(Status.BUFFER_OVERFLOW, status, 0, 0);
                     }

                     if (len < (long)packetLength) {
                        return this.newResultMayFinishHandshake(Status.BUFFER_UNDERFLOW, status, 0, 0);
                     }
                  }

                  assert srcsOffset < srcsEndOffset;

                  assert capacity > 0L;

                  int bytesProduced = 0;
                  int bytesConsumed = 0;

                  try {
                     label945:
                     while(true) {
                        ByteBuffer src = srcs[srcsOffset];
                        int remaining = src.remaining();
                        ByteBuf bioWriteCopyBuf;
                        int pendingEncryptedBytes;
                        if (remaining == 0) {
                           if (sslPending <= 0) {
                              ++srcsOffset;
                              if (srcsOffset < srcsEndOffset) {
                                 continue;
                              }
                              break;
                           }

                           bioWriteCopyBuf = null;
                           pendingEncryptedBytes = SSL.bioLengthByteBuffer(this.networkBIO);
                        } else {
                           pendingEncryptedBytes = Math.min(packetLength, remaining);

                           try {
                              bioWriteCopyBuf = this.writeEncryptedData(src, pendingEncryptedBytes);
                           } catch (SSLException e) {
                              SSLEngineResult var25 = this.handleUnwrapException(bytesConsumed, bytesProduced, e);
                              return var25;
                           }
                        }

                        try {
                           while(true) {
                              ByteBuffer dst = dsts[dstsOffset];
                              if (!dst.hasRemaining()) {
                                 ++dstsOffset;
                                 if (dstsOffset >= dstsEndOffset) {
                                    break label945;
                                 }
                              } else {
                                 int sslError;
                                 int bytesRead;
                                 try {
                                    bytesRead = this.readPlaintextData(dst);
                                 } catch (SSLException e) {
                                    sslError = (int)this.handleUnwrapException(bytesConsumed, bytesProduced, e);
                                    return sslError;
                                 }

                                 int localBytesConsumed = pendingEncryptedBytes - SSL.bioLengthByteBuffer(this.networkBIO);
                                 bytesConsumed += localBytesConsumed;
                                 packetLength -= localBytesConsumed;
                                 pendingEncryptedBytes -= localBytesConsumed;
                                 src.position(src.position() + localBytesConsumed);
                                 if (bytesRead <= 0) {
                                    sslError = SSL.getError(this.ssl, bytesRead);
                                    if (sslError != SSL.SSL_ERROR_WANT_READ && sslError != SSL.SSL_ERROR_WANT_WRITE) {
                                       SSLEngineResult var28;
                                       if (sslError != SSL.SSL_ERROR_ZERO_RETURN) {
                                          if (sslError != SSL.SSL_ERROR_WANT_X509_LOOKUP && sslError != SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY && sslError != SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                                             var28 = this.sslReadErrorResult(sslError, SSL.getLastErrorNumber(), bytesConsumed, bytesProduced);
                                             return var28;
                                          }

                                          var28 = this.newResult(this.isInboundDone() ? Status.CLOSED : Status.OK, HandshakeStatus.NEED_TASK, bytesConsumed, bytesProduced);
                                          return var28;
                                       }

                                       if (!this.receivedShutdown) {
                                          this.closeAll();
                                       }

                                       var28 = this.newResultMayFinishHandshake(this.isInboundDone() ? Status.CLOSED : Status.OK, status, bytesConsumed, bytesProduced);
                                       return var28;
                                    }

                                    ++srcsOffset;
                                    if (srcsOffset < srcsEndOffset) {
                                       break;
                                    }
                                    break label945;
                                 }

                                 bytesProduced += bytesRead;
                                 if (!dst.hasRemaining()) {
                                    sslPending = this.sslPending0();
                                    ++dstsOffset;
                                    if (dstsOffset >= dstsEndOffset) {
                                       sslError = (int)(sslPending > 0 ? this.newResult(Status.BUFFER_OVERFLOW, status, bytesConsumed, bytesProduced) : this.newResultMayFinishHandshake(this.isInboundDone() ? Status.CLOSED : Status.OK, status, bytesConsumed, bytesProduced));
                                       return sslError;
                                    }
                                 } else if (packetLength == 0 || this.jdkCompatibilityMode) {
                                    break label945;
                                 }
                              }
                           }
                        } finally {
                           if (bioWriteCopyBuf != null) {
                              bioWriteCopyBuf.release();
                           }

                        }
                     }
                  } finally {
                     SSL.bioClearByteBuffer(this.networkBIO);
                     this.rejectRemoteInitiatedRenegotiation();
                  }

                  if (!this.receivedShutdown && (SSL.getShutdown(this.ssl) & SSL.SSL_RECEIVED_SHUTDOWN) == SSL.SSL_RECEIVED_SHUTDOWN) {
                     this.closeAll();
                  }

                  return this.newResultMayFinishHandshake(this.isInboundDone() ? Status.CLOSED : Status.OK, status, bytesConsumed, bytesProduced);
               }
            }
         } else {
            throw new IndexOutOfBoundsException("offset: " + dstsOffset + ", length: " + dstsLength + " (expected: offset <= offset + length <= dsts.length (" + dsts.length + "))");
         }
      } else {
         throw new IndexOutOfBoundsException("offset: " + srcsOffset + ", length: " + srcsLength + " (expected: offset <= offset + length <= srcs.length (" + srcs.length + "))");
      }
   }

   private boolean needWrapAgain(int stackError) {
      if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
         if (this.pendingException == null) {
            this.pendingException = this.newSSLExceptionForError(stackError);
         } else if (shouldAddSuppressed(this.pendingException, stackError)) {
            ThrowableUtil.addSuppressed(this.pendingException, this.newSSLExceptionForError(stackError));
         }

         SSL.clearError();
         return true;
      } else {
         return false;
      }
   }

   private SSLException newSSLExceptionForError(int stackError) {
      String message = SSL.getErrorString((long)stackError);
      return (SSLException)(this.handshakeState == ReferenceCountedOpenSslEngine.HandshakeState.FINISHED ? new OpenSslException(message, stackError) : new OpenSslHandshakeException(message, stackError));
   }

   private static boolean shouldAddSuppressed(Throwable target, int errorCode) {
      for(Throwable suppressed : ThrowableUtil.getSuppressed(target)) {
         if (suppressed instanceof NativeSslException && ((NativeSslException)suppressed).errorCode() == errorCode) {
            return false;
         }
      }

      return true;
   }

   private SSLEngineResult sslReadErrorResult(int error, int stackError, int bytesConsumed, int bytesProduced) throws SSLException {
      if (this.needWrapAgain(stackError)) {
         return new SSLEngineResult(Status.OK, HandshakeStatus.NEED_WRAP, bytesConsumed, bytesProduced);
      } else {
         throw this.shutdownWithError("SSL_read", error, stackError);
      }
   }

   private void closeAll() throws SSLException {
      this.receivedShutdown = true;
      this.closeOutbound();
      this.closeInbound();
   }

   private void rejectRemoteInitiatedRenegotiation() throws SSLHandshakeException {
      if (!this.isDestroyed() && (!this.clientMode && SSL.getHandshakeCount(this.ssl) > 1 || this.clientMode && SSL.getHandshakeCount(this.ssl) > 2) && !"TLSv1.3".equals(this.session.getProtocol()) && this.handshakeState == ReferenceCountedOpenSslEngine.HandshakeState.FINISHED) {
         this.shutdown();
         throw new SSLHandshakeException("remote-initiated renegotiation not allowed");
      }
   }

   public final SSLEngineResult unwrap(ByteBuffer[] srcs, ByteBuffer[] dsts) throws SSLException {
      return this.unwrap(srcs, 0, srcs.length, dsts, 0, dsts.length);
   }

   private ByteBuffer[] singleSrcBuffer(ByteBuffer src) {
      this.singleSrcBuffer[0] = src;
      return this.singleSrcBuffer;
   }

   private void resetSingleSrcBuffer() {
      this.singleSrcBuffer[0] = null;
   }

   private ByteBuffer[] singleDstBuffer(ByteBuffer src) {
      this.singleDstBuffer[0] = src;
      return this.singleDstBuffer;
   }

   private void resetSingleDstBuffer() {
      this.singleDstBuffer[0] = null;
   }

   public final synchronized SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts, int offset, int length) throws SSLException {
      SSLEngineResult var5;
      try {
         var5 = this.unwrap(this.singleSrcBuffer(src), 0, 1, dsts, offset, length);
      } finally {
         this.resetSingleSrcBuffer();
      }

      return var5;
   }

   public final synchronized SSLEngineResult wrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
      SSLEngineResult var3;
      try {
         var3 = this.wrap(this.singleSrcBuffer(src), dst);
      } finally {
         this.resetSingleSrcBuffer();
      }

      return var3;
   }

   public final synchronized SSLEngineResult unwrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
      SSLEngineResult var3;
      try {
         var3 = this.unwrap(this.singleSrcBuffer(src), this.singleDstBuffer(dst));
      } finally {
         this.resetSingleSrcBuffer();
         this.resetSingleDstBuffer();
      }

      return var3;
   }

   public final synchronized SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts) throws SSLException {
      SSLEngineResult var3;
      try {
         var3 = this.unwrap(this.singleSrcBuffer(src), dsts);
      } finally {
         this.resetSingleSrcBuffer();
      }

      return var3;
   }

   private void runAndResetNeedTask(Runnable task) {
      synchronized(this) {
         try {
            if (!this.isDestroyed()) {
               task.run();
               if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.FINISHED && !this.isDestroyed() && SSL.doHandshake(this.ssl) <= 0) {
                  SSL.clearError();
               }

               return;
            }
         } finally {
            this.needTask = false;
         }

      }
   }

   public final synchronized Runnable getDelegatedTask() {
      if (this.isDestroyed()) {
         return null;
      } else {
         Runnable task = SSL.getTask(this.ssl);
         if (task == null) {
            return null;
         } else {
            return (Runnable)(task instanceof AsyncTask ? new AsyncTaskDecorator((AsyncTask)task) : new TaskDecorator(task));
         }
      }
   }

   public final synchronized void closeInbound() throws SSLException {
      if (!this.isInboundDone) {
         this.isInboundDone = true;
         if (this.isOutboundDone()) {
            this.shutdown();
         }

         if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.NOT_STARTED && !this.receivedShutdown) {
            throw new SSLException("Inbound closed before receiving peer's close_notify: possible truncation attack?");
         }
      }
   }

   public final synchronized boolean isInboundDone() {
      return this.isInboundDone;
   }

   public final synchronized void closeOutbound() {
      if (!this.outboundClosed) {
         this.outboundClosed = true;
         if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.NOT_STARTED && !this.isDestroyed()) {
            int mode = SSL.getShutdown(this.ssl);
            if ((mode & SSL.SSL_SENT_SHUTDOWN) != SSL.SSL_SENT_SHUTDOWN) {
               this.doSSLShutdown();
            }
         } else {
            this.shutdown();
         }

      }
   }

   private boolean doSSLShutdown() {
      if (SSL.isInInit(this.ssl) != 0) {
         return false;
      } else {
         int err = SSL.shutdownSSL(this.ssl);
         if (err < 0) {
            int sslErr = SSL.getError(this.ssl, err);
            if (sslErr == SSL.SSL_ERROR_SYSCALL || sslErr == SSL.SSL_ERROR_SSL) {
               if (logger.isDebugEnabled()) {
                  int error = SSL.getLastErrorNumber();
                  logger.debug("SSL_shutdown failed: OpenSSL error: {} {}", error, SSL.getErrorString((long)error));
               }

               this.shutdown();
               return false;
            }

            SSL.clearError();
         }

         return true;
      }
   }

   public final synchronized boolean isOutboundDone() {
      return this.outboundClosed && (this.networkBIO == 0L || SSL.bioLengthNonApplication(this.networkBIO) == 0);
   }

   public final String[] getSupportedCipherSuites() {
      return (String[])OpenSsl.AVAILABLE_CIPHER_SUITES.toArray(EmptyArrays.EMPTY_STRINGS);
   }

   public final String[] getEnabledCipherSuites() {
      String[] extraCiphers;
      String[] enabled;
      boolean tls13Enabled;
      synchronized(this) {
         if (this.isDestroyed()) {
            return EmptyArrays.EMPTY_STRINGS;
         }

         enabled = SSL.getCiphers(this.ssl);
         int opts = SSL.getOptions(this.ssl);
         if (isProtocolEnabled(opts, SSL.SSL_OP_NO_TLSv1_3, "TLSv1.3")) {
            extraCiphers = OpenSsl.EXTRA_SUPPORTED_TLS_1_3_CIPHERS;
            tls13Enabled = true;
         } else {
            extraCiphers = EmptyArrays.EMPTY_STRINGS;
            tls13Enabled = false;
         }
      }

      if (enabled == null) {
         return EmptyArrays.EMPTY_STRINGS;
      } else {
         Set<String> enabledSet = new LinkedHashSet(enabled.length + extraCiphers.length);
         synchronized(this) {
            for(int i = 0; i < enabled.length; ++i) {
               String mapped = this.toJavaCipherSuite(enabled[i]);
               String cipher = mapped == null ? enabled[i] : mapped;
               if (tls13Enabled && OpenSsl.isTlsv13Supported() || !SslUtils.isTLSv13Cipher(cipher)) {
                  enabledSet.add(cipher);
               }
            }

            Collections.addAll(enabledSet, extraCiphers);
         }

         return (String[])enabledSet.toArray(EmptyArrays.EMPTY_STRINGS);
      }
   }

   public final void setEnabledCipherSuites(String[] cipherSuites) {
      ObjectUtil.checkNotNull(cipherSuites, "cipherSuites");
      StringBuilder buf = new StringBuilder();
      StringBuilder bufTLSv13 = new StringBuilder();
      CipherSuiteConverter.convertToCipherStrings(Arrays.asList(cipherSuites), buf, bufTLSv13, OpenSsl.isBoringSSL());
      String cipherSuiteSpec = buf.toString();
      String cipherSuiteSpecTLSv13 = bufTLSv13.toString();
      if (!OpenSsl.isTlsv13Supported() && !cipherSuiteSpecTLSv13.isEmpty()) {
         throw new IllegalArgumentException("TLSv1.3 is not supported by this java version.");
      } else {
         synchronized(this) {
            this.hasTLSv13Cipher = !cipherSuiteSpecTLSv13.isEmpty();
            if (this.isDestroyed()) {
               throw new IllegalStateException("failed to enable cipher suites: " + cipherSuiteSpec);
            } else {
               try {
                  SSL.setCipherSuites(this.ssl, cipherSuiteSpec, false);
                  if (OpenSsl.isTlsv13Supported()) {
                     SSL.setCipherSuites(this.ssl, OpenSsl.checkTls13Ciphers(logger, cipherSuiteSpecTLSv13), true);
                  }

                  Set<String> protocols = new HashSet(this.enabledProtocols);
                  if (cipherSuiteSpec.isEmpty()) {
                     protocols.remove("TLSv1");
                     protocols.remove("TLSv1.1");
                     protocols.remove("TLSv1.2");
                     protocols.remove("SSLv3");
                     protocols.remove("SSLv2");
                     protocols.remove("SSLv2Hello");
                  }

                  if (cipherSuiteSpecTLSv13.isEmpty()) {
                     protocols.remove("TLSv1.3");
                  }

                  this.setEnabledProtocols0((String[])protocols.toArray(EmptyArrays.EMPTY_STRINGS), !this.hasTLSv13Cipher);
               } catch (Exception e) {
                  throw new IllegalStateException("failed to enable cipher suites: " + cipherSuiteSpec, e);
               }

            }
         }
      }
   }

   public final String[] getSupportedProtocols() {
      return (String[])OpenSsl.SUPPORTED_PROTOCOLS_SET.toArray(EmptyArrays.EMPTY_STRINGS);
   }

   public final String[] getEnabledProtocols() {
      return (String[])this.enabledProtocols.toArray(EmptyArrays.EMPTY_STRINGS);
   }

   private static boolean isProtocolEnabled(int opts, int disableMask, String protocolString) {
      return (opts & disableMask) == 0 && OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(protocolString);
   }

   public final void setEnabledProtocols(String[] protocols) {
      ObjectUtil.checkNotNullWithIAE(protocols, "protocols");
      synchronized(this) {
         this.enabledProtocols.clear();
         this.enabledProtocols.add("SSLv2Hello");
         Collections.addAll(this.enabledProtocols, protocols);
         this.setEnabledProtocols0(protocols, !this.hasTLSv13Cipher);
      }
   }

   private void setEnabledProtocols0(String[] protocols, boolean explicitDisableTLSv13) {
      assert Thread.holdsLock(this);

      int minProtocolIndex = OPENSSL_OP_NO_PROTOCOLS.length;
      int maxProtocolIndex = 0;

      for(String p : protocols) {
         if (!OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(p)) {
            throw new IllegalArgumentException("Protocol " + p + " is not supported.");
         }

         if (p.equals("SSLv2")) {
            if (minProtocolIndex > 0) {
               minProtocolIndex = 0;
            }

            if (maxProtocolIndex < 0) {
               maxProtocolIndex = 0;
            }
         } else if (p.equals("SSLv3")) {
            if (minProtocolIndex > 1) {
               minProtocolIndex = 1;
            }

            if (maxProtocolIndex < 1) {
               maxProtocolIndex = 1;
            }
         } else if (p.equals("TLSv1")) {
            if (minProtocolIndex > 2) {
               minProtocolIndex = 2;
            }

            if (maxProtocolIndex < 2) {
               maxProtocolIndex = 2;
            }
         } else if (p.equals("TLSv1.1")) {
            if (minProtocolIndex > 3) {
               minProtocolIndex = 3;
            }

            if (maxProtocolIndex < 3) {
               maxProtocolIndex = 3;
            }
         } else if (p.equals("TLSv1.2")) {
            if (minProtocolIndex > 4) {
               minProtocolIndex = 4;
            }

            if (maxProtocolIndex < 4) {
               maxProtocolIndex = 4;
            }
         } else if (!explicitDisableTLSv13 && p.equals("TLSv1.3")) {
            if (minProtocolIndex > 5) {
               minProtocolIndex = 5;
            }

            if (maxProtocolIndex < 5) {
               maxProtocolIndex = 5;
            }
         }
      }

      if (this.isDestroyed()) {
         throw new IllegalStateException("failed to enable protocols: " + Arrays.asList(protocols));
      } else {
         SSL.clearOptions(this.ssl, SSL.SSL_OP_NO_SSLv2 | SSL.SSL_OP_NO_SSLv3 | SSL.SSL_OP_NO_TLSv1 | SSL.SSL_OP_NO_TLSv1_1 | SSL.SSL_OP_NO_TLSv1_2 | SSL.SSL_OP_NO_TLSv1_3);
         int opts = 0;

         for(int i = 0; i < minProtocolIndex; ++i) {
            opts |= OPENSSL_OP_NO_PROTOCOLS[i];
         }

         assert maxProtocolIndex != Integer.MAX_VALUE;

         for(int i = maxProtocolIndex + 1; i < OPENSSL_OP_NO_PROTOCOLS.length; ++i) {
            opts |= OPENSSL_OP_NO_PROTOCOLS[i];
         }

         SSL.setOptions(this.ssl, opts);
      }
   }

   public final SSLSession getSession() {
      return this.session;
   }

   public final synchronized void beginHandshake() throws SSLException {
      switch (this.handshakeState) {
         case NOT_STARTED:
            this.handshakeState = ReferenceCountedOpenSslEngine.HandshakeState.STARTED_EXPLICITLY;
            if (this.handshake() == HandshakeStatus.NEED_TASK) {
               this.needTask = true;
            }

            this.calculateMaxWrapOverhead();
            break;
         case FINISHED:
            throw new SSLException("renegotiation unsupported");
         case STARTED_IMPLICITLY:
            this.checkEngineClosed();
            this.handshakeState = ReferenceCountedOpenSslEngine.HandshakeState.STARTED_EXPLICITLY;
            this.calculateMaxWrapOverhead();
         case STARTED_EXPLICITLY:
            break;
         default:
            throw new Error();
      }

   }

   private void checkEngineClosed() throws SSLException {
      if (this.isDestroyed()) {
         throw new SSLException("engine closed");
      }
   }

   private static SSLEngineResult.HandshakeStatus pendingStatus(int pendingStatus) {
      return pendingStatus > 0 ? HandshakeStatus.NEED_WRAP : HandshakeStatus.NEED_UNWRAP;
   }

   private static boolean isEmpty(Object[] arr) {
      return arr == null || arr.length == 0;
   }

   private static boolean isEmpty(byte[] cert) {
      return cert == null || cert.length == 0;
   }

   private SSLEngineResult.HandshakeStatus handshakeException() throws SSLException {
      if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
         return HandshakeStatus.NEED_WRAP;
      } else {
         Throwable exception = this.pendingException;

         assert exception != null;

         this.pendingException = null;
         this.shutdown();
         if (exception instanceof SSLHandshakeException) {
            throw (SSLHandshakeException)exception;
         } else {
            SSLHandshakeException e = new SSLHandshakeException("General OpenSslEngine problem");
            e.initCause(exception);
            throw e;
         }
      }
   }

   final void initHandshakeException(Throwable cause) {
      if (this.pendingException == null) {
         this.pendingException = cause;
      } else {
         ThrowableUtil.addSuppressed(this.pendingException, cause);
      }

   }

   private SSLEngineResult.HandshakeStatus handshake() throws SSLException {
      if (this.needTask) {
         return HandshakeStatus.NEED_TASK;
      } else if (this.handshakeState == ReferenceCountedOpenSslEngine.HandshakeState.FINISHED) {
         return HandshakeStatus.FINISHED;
      } else {
         this.checkEngineClosed();
         if (this.pendingException != null) {
            if (SSL.doHandshake(this.ssl) <= 0) {
               SSL.clearError();
            }

            return this.handshakeException();
         } else {
            this.engineMap.add(this);
            if (!this.sessionSet) {
               if (!this.parentContext.sessionContext().setSessionFromCache(this.ssl, this.session, this.getPeerHost(), this.getPeerPort())) {
                  this.session.prepareHandshake();
               }

               this.sessionSet = true;
            }

            int code = SSL.doHandshake(this.ssl);
            if (code <= 0) {
               int sslError = SSL.getError(this.ssl, code);
               if (sslError != SSL.SSL_ERROR_WANT_READ && sslError != SSL.SSL_ERROR_WANT_WRITE) {
                  if (sslError != SSL.SSL_ERROR_WANT_X509_LOOKUP && sslError != SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY && sslError != SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                     int errorNumber = SSL.getLastErrorNumber();
                     if (this.needWrapAgain(errorNumber)) {
                        return HandshakeStatus.NEED_WRAP;
                     } else if (this.pendingException != null) {
                        return this.handshakeException();
                     } else {
                        throw this.shutdownWithError("SSL_do_handshake", sslError, errorNumber);
                     }
                  } else {
                     return HandshakeStatus.NEED_TASK;
                  }
               } else {
                  return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
               }
            } else if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
               return HandshakeStatus.NEED_WRAP;
            } else {
               this.session.handshakeFinished(SSL.getSessionId(this.ssl), SSL.getCipherForSSL(this.ssl), SSL.getVersion(this.ssl), SSL.getPeerCertificate(this.ssl), SSL.getPeerCertChain(this.ssl), SSL.getTime(this.ssl) * 1000L, this.parentContext.sessionTimeout() * 1000L);
               this.selectApplicationProtocol();
               return HandshakeStatus.FINISHED;
            }
         }
      }
   }

   private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus hs, int bytesConsumed, int bytesProduced) throws SSLException {
      return (hs != HandshakeStatus.NEED_UNWRAP || bytesProduced <= 0) && (hs != HandshakeStatus.NEED_WRAP || bytesConsumed <= 0) ? this.mayFinishHandshake(hs != HandshakeStatus.FINISHED ? this.getHandshakeStatus() : HandshakeStatus.FINISHED) : this.handshake();
   }

   private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus status) throws SSLException {
      if (status == HandshakeStatus.NOT_HANDSHAKING) {
         if (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.FINISHED) {
            return this.handshake();
         }

         if (!this.isDestroyed() && SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            return HandshakeStatus.NEED_WRAP;
         }
      }

      return status;
   }

   public final synchronized SSLEngineResult.HandshakeStatus getHandshakeStatus() {
      if (this.needPendingStatus()) {
         return this.needTask ? HandshakeStatus.NEED_TASK : pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
      } else {
         return HandshakeStatus.NOT_HANDSHAKING;
      }
   }

   private SSLEngineResult.HandshakeStatus getHandshakeStatus(int pending) {
      if (this.needPendingStatus()) {
         return this.needTask ? HandshakeStatus.NEED_TASK : pendingStatus(pending);
      } else {
         return HandshakeStatus.NOT_HANDSHAKING;
      }
   }

   private boolean needPendingStatus() {
      return this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.NOT_STARTED && !this.isDestroyed() && (this.handshakeState != ReferenceCountedOpenSslEngine.HandshakeState.FINISHED || this.isInboundDone() || this.isOutboundDone());
   }

   private String toJavaCipherSuite(String openSslCipherSuite) {
      if (openSslCipherSuite == null) {
         return null;
      } else {
         String version = SSL.getVersion(this.ssl);
         String prefix = toJavaCipherSuitePrefix(version);
         return CipherSuiteConverter.toJava(openSslCipherSuite, prefix);
      }
   }

   private static String toJavaCipherSuitePrefix(String protocolVersion) {
      char c;
      if (protocolVersion != null && !protocolVersion.isEmpty()) {
         c = protocolVersion.charAt(0);
      } else {
         c = 0;
      }

      switch (c) {
         case 'S':
            return "SSL";
         case 'T':
            return "TLS";
         default:
            return "UNKNOWN";
      }
   }

   public final void setUseClientMode(boolean clientMode) {
      if (clientMode != this.clientMode) {
         throw new UnsupportedOperationException();
      }
   }

   public final boolean getUseClientMode() {
      return this.clientMode;
   }

   public final void setNeedClientAuth(boolean b) {
      this.setClientAuth(b ? ClientAuth.REQUIRE : ClientAuth.NONE);
   }

   public final boolean getNeedClientAuth() {
      return this.clientAuth == ClientAuth.REQUIRE;
   }

   public final void setWantClientAuth(boolean b) {
      this.setClientAuth(b ? ClientAuth.OPTIONAL : ClientAuth.NONE);
   }

   public final boolean getWantClientAuth() {
      return this.clientAuth == ClientAuth.OPTIONAL;
   }

   public final synchronized void setVerify(int verifyMode, int depth) {
      if (!this.isDestroyed()) {
         SSL.setVerify(this.ssl, verifyMode, depth);
      }

   }

   private void setClientAuth(ClientAuth mode) {
      if (!this.clientMode) {
         synchronized(this) {
            if (this.clientAuth != mode) {
               if (!this.isDestroyed()) {
                  switch (mode) {
                     case NONE:
                        SSL.setVerify(this.ssl, 0, 10);
                        break;
                     case REQUIRE:
                        SSL.setVerify(this.ssl, 2, 10);
                        break;
                     case OPTIONAL:
                        SSL.setVerify(this.ssl, 1, 10);
                        break;
                     default:
                        throw new Error(mode.toString());
                  }
               }

               this.clientAuth = mode;
            }
         }
      }
   }

   public final void setEnableSessionCreation(boolean b) {
      if (b) {
         throw new UnsupportedOperationException();
      }
   }

   public final boolean getEnableSessionCreation() {
      return false;
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public final synchronized SSLParameters getSSLParameters() {
      SSLParameters sslParameters = super.getSSLParameters();
      int version = PlatformDependent.javaVersion();
      if (version >= 7) {
         Java7SslParametersUtils.setEndpointIdentificationAlgorithm(sslParameters, this.endpointIdentificationAlgorithm);
         Java7SslParametersUtils.setAlgorithmConstraints(sslParameters, this.algorithmConstraints);
         if (version >= 8) {
            if (this.sniHostNames != null) {
               Java8SslUtils.setSniHostNames(sslParameters, this.sniHostNames);
            }

            if (!this.isDestroyed()) {
               Java8SslUtils.setUseCipherSuitesOrder(sslParameters, (SSL.getOptions(this.ssl) & SSL.SSL_OP_CIPHER_SERVER_PREFERENCE) != 0);
            }

            Java8SslUtils.setSNIMatchers(sslParameters, this.matchers);
         }
      }

      return sslParameters;
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public final synchronized void setSSLParameters(SSLParameters sslParameters) {
      int version = PlatformDependent.javaVersion();
      if (version >= 7) {
         if (sslParameters.getAlgorithmConstraints() != null) {
            throw new IllegalArgumentException("AlgorithmConstraints are not supported.");
         }

         boolean isDestroyed = this.isDestroyed();
         if (version >= 8) {
            if (!isDestroyed) {
               if (this.clientMode) {
                  List<String> sniHostNames = Java8SslUtils.getSniHostNames(sslParameters);

                  for(String name : sniHostNames) {
                     SSL.setTlsExtHostName(this.ssl, name);
                  }

                  this.sniHostNames = sniHostNames;
               }

               if (Java8SslUtils.getUseCipherSuitesOrder(sslParameters)) {
                  SSL.setOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
               } else {
                  SSL.clearOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
               }
            }

            this.matchers = sslParameters.getSNIMatchers();
         }

         String endpointIdentificationAlgorithm = sslParameters.getEndpointIdentificationAlgorithm();
         if (!isDestroyed) {
            this.configureEndpointVerification(endpointIdentificationAlgorithm);
         }

         this.endpointIdentificationAlgorithm = endpointIdentificationAlgorithm;
         this.algorithmConstraints = sslParameters.getAlgorithmConstraints();
      }

      super.setSSLParameters(sslParameters);
   }

   private void configureEndpointVerification(String endpointIdentificationAlgorithm) {
      if (this.clientMode && isEndPointVerificationEnabled(endpointIdentificationAlgorithm)) {
         SSL.setVerify(this.ssl, 2, -1);
      }

   }

   private static boolean isEndPointVerificationEnabled(String endPointIdentificationAlgorithm) {
      return endPointIdentificationAlgorithm != null && !endPointIdentificationAlgorithm.isEmpty();
   }

   private boolean isDestroyed() {
      return this.destroyed;
   }

   final boolean checkSniHostnameMatch(byte[] hostname) {
      return Java8SslUtils.checkSniHostnameMatch(this.matchers, hostname);
   }

   public String getNegotiatedApplicationProtocol() {
      return this.applicationProtocol;
   }

   private static long bufferAddress(ByteBuffer b) {
      assert b.isDirect();

      return PlatformDependent.hasUnsafe() ? PlatformDependent.directBufferAddress(b) : Buffer.address(b);
   }

   private void selectApplicationProtocol() throws SSLException {
      ApplicationProtocolConfig.SelectedListenerFailureBehavior behavior = this.apn.selectedListenerFailureBehavior();
      List<String> protocols = this.apn.protocols();
      switch (this.apn.protocol()) {
         case NONE:
            break;
         case ALPN:
            String applicationProtocol = SSL.getAlpnSelected(this.ssl);
            if (applicationProtocol != null) {
               this.applicationProtocol = this.selectApplicationProtocol(protocols, behavior, applicationProtocol);
            }
            break;
         case NPN:
            String applicationProtocol = SSL.getNextProtoNegotiated(this.ssl);
            if (applicationProtocol != null) {
               this.applicationProtocol = this.selectApplicationProtocol(protocols, behavior, applicationProtocol);
            }
            break;
         case NPN_AND_ALPN:
            String applicationProtocol = SSL.getAlpnSelected(this.ssl);
            if (applicationProtocol == null) {
               applicationProtocol = SSL.getNextProtoNegotiated(this.ssl);
            }

            if (applicationProtocol != null) {
               this.applicationProtocol = this.selectApplicationProtocol(protocols, behavior, applicationProtocol);
            }
            break;
         default:
            throw new Error();
      }

   }

   private String selectApplicationProtocol(List protocols, ApplicationProtocolConfig.SelectedListenerFailureBehavior behavior, String applicationProtocol) throws SSLException {
      if (behavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT) {
         return applicationProtocol;
      } else {
         int size = protocols.size();

         assert size > 0;

         if (protocols.contains(applicationProtocol)) {
            return applicationProtocol;
         } else if (behavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL) {
            return (String)protocols.get(size - 1);
         } else {
            throw new SSLException("unknown protocol " + applicationProtocol);
         }
      }
   }

   static {
      OPENSSL_OP_NO_PROTOCOLS = new int[]{SSL.SSL_OP_NO_SSLv2, SSL.SSL_OP_NO_SSLv3, SSL.SSL_OP_NO_TLSv1, SSL.SSL_OP_NO_TLSv1_1, SSL.SSL_OP_NO_TLSv1_2, SSL.SSL_OP_NO_TLSv1_3};
      MAX_PLAINTEXT_LENGTH = SSL.SSL_MAX_PLAINTEXT_LENGTH;
      MAX_RECORD_SIZE = SSL.SSL_MAX_RECORD_LENGTH;
      NEED_UNWRAP_OK = new SSLEngineResult(Status.OK, HandshakeStatus.NEED_UNWRAP, 0, 0);
      NEED_UNWRAP_CLOSED = new SSLEngineResult(Status.CLOSED, HandshakeStatus.NEED_UNWRAP, 0, 0);
      NEED_WRAP_OK = new SSLEngineResult(Status.OK, HandshakeStatus.NEED_WRAP, 0, 0);
      NEED_WRAP_CLOSED = new SSLEngineResult(Status.CLOSED, HandshakeStatus.NEED_WRAP, 0, 0);
      CLOSED_NOT_HANDSHAKING = new SSLEngineResult(Status.CLOSED, HandshakeStatus.NOT_HANDSHAKING, 0, 0);
      JAVAX_CERTS_NOT_SUPPORTED = new X509Certificate[0];
   }

   private static enum HandshakeState {
      NOT_STARTED,
      STARTED_IMPLICITLY,
      STARTED_EXPLICITLY,
      FINISHED;
   }

   private class TaskDecorator implements Runnable {
      protected final Runnable task;

      TaskDecorator(Runnable task) {
         this.task = task;
      }

      public void run() {
         ReferenceCountedOpenSslEngine.this.runAndResetNeedTask(this.task);
      }
   }

   private final class AsyncTaskDecorator extends TaskDecorator implements AsyncRunnable {
      AsyncTaskDecorator(AsyncTask task) {
         super(task);
      }

      public void run(Runnable runnable) {
         if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
            ((AsyncTask)this.task).runAsync(ReferenceCountedOpenSslEngine.this.new TaskDecorator(runnable));
         }
      }
   }

   private final class DefaultOpenSslSession implements OpenSslInternalSession {
      private final OpenSslSessionContext sessionContext;
      private X509Certificate[] x509PeerCerts;
      private Certificate[] peerCerts;
      private boolean valid = true;
      private String protocol;
      private String cipher;
      private OpenSslSessionId id;
      private long creationTime;
      private long lastAccessed;
      private volatile int applicationBufferSize;
      private volatile Certificate[] localCertificateChain;
      private volatile Map keyValueStorage;

      DefaultOpenSslSession(OpenSslSessionContext sessionContext) {
         this.id = OpenSslSessionId.NULL_ID;
         this.lastAccessed = -1L;
         this.applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH;
         this.keyValueStorage = new ConcurrentHashMap();
         this.sessionContext = sessionContext;
      }

      private SSLSessionBindingEvent newSSLSessionBindingEvent(String name) {
         return new SSLSessionBindingEvent(ReferenceCountedOpenSslEngine.this.session, name);
      }

      public void prepareHandshake() {
         this.keyValueStorage.clear();
      }

      public void setSessionDetails(long creationTime, long lastAccessedTime, OpenSslSessionId sessionId, Map keyValueStorage) {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            if (this.id == OpenSslSessionId.NULL_ID) {
               this.id = sessionId;
               this.creationTime = creationTime;
               this.lastAccessed = lastAccessedTime;
               this.keyValueStorage = keyValueStorage;
            }

         }
      }

      public Map keyValueStorage() {
         return this.keyValueStorage;
      }

      public OpenSslSessionId sessionId() {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            if (this.id == OpenSslSessionId.NULL_ID && !ReferenceCountedOpenSslEngine.this.isDestroyed()) {
               byte[] sessionId = SSL.getSessionId(ReferenceCountedOpenSslEngine.this.ssl);
               if (sessionId != null) {
                  this.id = new OpenSslSessionId(sessionId);
               }
            }

            return this.id;
         }
      }

      public void setLocalCertificate(Certificate[] localCertificate) {
         this.localCertificateChain = localCertificate;
      }

      public byte[] getId() {
         return this.sessionId().cloneBytes();
      }

      public OpenSslSessionContext getSessionContext() {
         return this.sessionContext;
      }

      public long getCreationTime() {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            return this.creationTime;
         }
      }

      public void setLastAccessedTime(long time) {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            this.lastAccessed = time;
         }
      }

      public long getLastAccessedTime() {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            return this.lastAccessed == -1L ? this.creationTime : this.lastAccessed;
         }
      }

      public void invalidate() {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            this.valid = false;
            this.sessionContext.removeFromCache(this.id);
         }
      }

      public boolean isValid() {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            return this.valid || this.sessionContext.isInCache(this.id);
         }
      }

      public void putValue(String name, Object value) {
         ObjectUtil.checkNotNull(name, "name");
         ObjectUtil.checkNotNull(value, "value");
         Object old = this.keyValueStorage.put(name, value);
         if (value instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener)value).valueBound(this.newSSLSessionBindingEvent(name));
         }

         this.notifyUnbound(old, name);
      }

      public Object getValue(String name) {
         ObjectUtil.checkNotNull(name, "name");
         return this.keyValueStorage.get(name);
      }

      public void removeValue(String name) {
         ObjectUtil.checkNotNull(name, "name");
         Object old = this.keyValueStorage.remove(name);
         this.notifyUnbound(old, name);
      }

      public String[] getValueNames() {
         return (String[])this.keyValueStorage.keySet().toArray(EmptyArrays.EMPTY_STRINGS);
      }

      private void notifyUnbound(Object value, String name) {
         if (value instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener)value).valueUnbound(this.newSSLSessionBindingEvent(name));
         }

      }

      public void handshakeFinished(byte[] id, String cipher, String protocol, byte[] peerCertificate, byte[][] peerCertificateChain, long creationTime, long timeout) throws SSLException {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
               if (this.id == OpenSslSessionId.NULL_ID) {
                  this.id = id == null ? OpenSslSessionId.NULL_ID : new OpenSslSessionId(id);
                  this.creationTime = this.lastAccessed = creationTime;
               }

               this.cipher = ReferenceCountedOpenSslEngine.this.toJavaCipherSuite(cipher);
               this.protocol = protocol;
               if (ReferenceCountedOpenSslEngine.this.clientMode) {
                  if (ReferenceCountedOpenSslEngine.isEmpty((Object[])peerCertificateChain)) {
                     this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
                     if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                        this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
                     } else {
                        this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                     }
                  } else {
                     this.peerCerts = new Certificate[peerCertificateChain.length];
                     if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                        this.x509PeerCerts = new X509Certificate[peerCertificateChain.length];
                     } else {
                        this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                     }

                     this.initCerts(peerCertificateChain, 0);
                  }
               } else if (ReferenceCountedOpenSslEngine.isEmpty(peerCertificate)) {
                  this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
                  this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
               } else if (ReferenceCountedOpenSslEngine.isEmpty((Object[])peerCertificateChain)) {
                  this.peerCerts = new Certificate[]{new LazyX509Certificate(peerCertificate)};
                  if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                     this.x509PeerCerts = new X509Certificate[]{new LazyJavaxX509Certificate(peerCertificate)};
                  } else {
                     this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                  }
               } else {
                  this.peerCerts = new Certificate[peerCertificateChain.length + 1];
                  this.peerCerts[0] = new LazyX509Certificate(peerCertificate);
                  if (OpenSsl.JAVAX_CERTIFICATE_CREATION_SUPPORTED) {
                     this.x509PeerCerts = new X509Certificate[peerCertificateChain.length + 1];
                     this.x509PeerCerts[0] = new LazyJavaxX509Certificate(peerCertificate);
                  } else {
                     this.x509PeerCerts = ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED;
                  }

                  this.initCerts(peerCertificateChain, 1);
               }

               ReferenceCountedOpenSslEngine.this.calculateMaxWrapOverhead();
               ReferenceCountedOpenSslEngine.this.handshakeState = ReferenceCountedOpenSslEngine.HandshakeState.FINISHED;
            } else {
               throw new SSLException("Already closed");
            }
         }
      }

      private void initCerts(byte[][] chain, int startPos) {
         for(int i = 0; i < chain.length; ++i) {
            int certPos = startPos + i;
            this.peerCerts[certPos] = new LazyX509Certificate(chain[i]);
            if (this.x509PeerCerts != ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED) {
               this.x509PeerCerts[certPos] = new LazyJavaxX509Certificate(chain[i]);
            }
         }

      }

      public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            if (ReferenceCountedOpenSslEngine.isEmpty((Object[])this.peerCerts)) {
               throw new SSLPeerUnverifiedException("peer not verified");
            } else {
               return (Certificate[])this.peerCerts.clone();
            }
         }
      }

      public boolean hasPeerCertificates() {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            return !ReferenceCountedOpenSslEngine.isEmpty((Object[])this.peerCerts);
         }
      }

      public Certificate[] getLocalCertificates() {
         Certificate[] localCerts = this.localCertificateChain;
         return localCerts == null ? null : (Certificate[])((Certificate;)localCerts).clone();
      }

      public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            if (this.x509PeerCerts == ReferenceCountedOpenSslEngine.JAVAX_CERTS_NOT_SUPPORTED) {
               throw new UnsupportedOperationException();
            } else if (ReferenceCountedOpenSslEngine.isEmpty((Object[])this.x509PeerCerts)) {
               throw new SSLPeerUnverifiedException("peer not verified");
            } else {
               return (X509Certificate[])this.x509PeerCerts.clone();
            }
         }
      }

      public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
         Certificate[] peer = this.getPeerCertificates();
         return ((java.security.cert.X509Certificate)peer[0]).getSubjectX500Principal();
      }

      public Principal getLocalPrincipal() {
         Certificate[] local = this.localCertificateChain;
         return local != null && local.length != 0 ? ((java.security.cert.X509Certificate)local[0]).getSubjectX500Principal() : null;
      }

      public String getCipherSuite() {
         synchronized(ReferenceCountedOpenSslEngine.this) {
            return this.cipher == null ? "SSL_NULL_WITH_NULL_NULL" : this.cipher;
         }
      }

      public String getProtocol() {
         String protocol = this.protocol;
         if (protocol == null) {
            synchronized(ReferenceCountedOpenSslEngine.this) {
               if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                  protocol = SSL.getVersion(ReferenceCountedOpenSslEngine.this.ssl);
               } else {
                  protocol = "";
               }
            }
         }

         return protocol;
      }

      public String getPeerHost() {
         return ReferenceCountedOpenSslEngine.this.getPeerHost();
      }

      public int getPeerPort() {
         return ReferenceCountedOpenSslEngine.this.getPeerPort();
      }

      public int getPacketBufferSize() {
         return SSL.SSL_MAX_ENCRYPTED_LENGTH;
      }

      public int getApplicationBufferSize() {
         return this.applicationBufferSize;
      }

      public void tryExpandApplicationBufferSize(int packetLengthDataOnly) {
         if (packetLengthDataOnly > ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH && this.applicationBufferSize != ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE) {
            this.applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE;
         }

      }

      public String toString() {
         return "DefaultOpenSslSession{sessionContext=" + this.sessionContext + ", id=" + this.id + '}';
      }

      public int hashCode() {
         return this.sessionId().hashCode();
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            return !(o instanceof OpenSslInternalSession) ? false : this.sessionId().equals(((OpenSslInternalSession)o).sessionId());
         }
      }
   }

   private static final class OpenSslException extends SSLException implements NativeSslException {
      private final int errorCode;

      OpenSslException(String reason, int errorCode) {
         super(reason);
         this.errorCode = errorCode;
      }

      public int errorCode() {
         return this.errorCode;
      }
   }

   private static final class OpenSslHandshakeException extends SSLHandshakeException implements NativeSslException {
      private final int errorCode;

      OpenSslHandshakeException(String reason, int errorCode) {
         super(reason);
         this.errorCode = errorCode;
      }

      public int errorCode() {
         return this.errorCode;
      }
   }

   private interface NativeSslException {
      int errorCode();
   }
}
