package io.netty.resolver.dns;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.dns.AbstractDnsOptPseudoRrRecord;
import io.netty.handler.codec.dns.DnsQuery;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.handler.codec.dns.DnsSection;
import io.netty.handler.codec.dns.TcpDnsQueryEncoder;
import io.netty.handler.codec.dns.TcpDnsResponseDecoder;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;

abstract class DnsQueryContext {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(DnsQueryContext.class);
   private static final long ID_REUSE_ON_TIMEOUT_DELAY_MILLIS = SystemPropertyUtil.getLong("io.netty.resolver.dns.idReuseOnTimeoutDelayMillis", 10000L);
   private static final TcpDnsQueryEncoder TCP_ENCODER;
   private final Channel channel;
   private final InetSocketAddress nameServerAddr;
   private final DnsQueryContextManager queryContextManager;
   private final Promise promise;
   private final DnsQuestion question;
   private final DnsRecord[] additionals;
   private final DnsRecord optResource;
   private final boolean recursionDesired;
   private final Bootstrap socketBootstrap;
   private final boolean retryWithTcpOnTimeout;
   private final long queryTimeoutMillis;
   private volatile Future timeoutFuture;
   private int id = Integer.MIN_VALUE;

   DnsQueryContext(Channel channel, InetSocketAddress nameServerAddr, DnsQueryContextManager queryContextManager, int maxPayLoadSize, boolean recursionDesired, long queryTimeoutMillis, DnsQuestion question, DnsRecord[] additionals, Promise promise, Bootstrap socketBootstrap, boolean retryWithTcpOnTimeout) {
      this.channel = (Channel)ObjectUtil.checkNotNull(channel, "channel");
      this.queryContextManager = (DnsQueryContextManager)ObjectUtil.checkNotNull(queryContextManager, "queryContextManager");
      this.nameServerAddr = (InetSocketAddress)ObjectUtil.checkNotNull(nameServerAddr, "nameServerAddr");
      this.question = (DnsQuestion)ObjectUtil.checkNotNull(question, "question");
      this.additionals = (DnsRecord[])ObjectUtil.checkNotNull(additionals, "additionals");
      this.promise = (Promise)ObjectUtil.checkNotNull(promise, "promise");
      this.recursionDesired = recursionDesired;
      this.queryTimeoutMillis = queryTimeoutMillis;
      this.socketBootstrap = socketBootstrap;
      this.retryWithTcpOnTimeout = retryWithTcpOnTimeout;
      if (maxPayLoadSize > 0 && !hasOptRecord(additionals)) {
         this.optResource = new AbstractDnsOptPseudoRrRecord(maxPayLoadSize, 0, 0) {
         };
      } else {
         this.optResource = null;
      }

   }

   private static boolean hasOptRecord(DnsRecord[] additionals) {
      if (additionals != null && additionals.length > 0) {
         for(DnsRecord additional : additionals) {
            if (additional.type() == DnsRecordType.OPT) {
               return true;
            }
         }
      }

      return false;
   }

   final boolean isDone() {
      return this.promise.isDone();
   }

   final DnsQuestion question() {
      return this.question;
   }

   protected abstract DnsQuery newQuery(int var1, InetSocketAddress var2);

   protected abstract String protocol();

   final ChannelFuture writeQuery(boolean flush) {
      assert this.id == Integer.MIN_VALUE : this.getClass().getSimpleName() + ".writeQuery(...) can only be executed once.";

      if ((this.id = this.queryContextManager.add(this.nameServerAddr, this)) == -1) {
         IllegalStateException e = new IllegalStateException("query ID space exhausted: " + this.question());
         this.finishFailure("failed to send a query via " + this.protocol(), e, false);
         return this.channel.newFailedFuture(e);
      } else {
         this.promise.addListener(new FutureListener() {
            public void operationComplete(Future future) {
               Future<?> timeoutFuture = DnsQueryContext.this.timeoutFuture;
               if (timeoutFuture != null) {
                  DnsQueryContext.this.timeoutFuture = null;
                  timeoutFuture.cancel(false);
               }

               Throwable cause = future.cause();
               if (!(cause instanceof DnsNameResolverTimeoutException) && !(cause instanceof CancellationException)) {
                  DnsQueryContext.this.removeFromContextManager(DnsQueryContext.this.nameServerAddr);
               } else {
                  DnsQueryContext.this.channel.eventLoop().schedule(new Runnable() {
                     public void run() {
                        DnsQueryContext.this.removeFromContextManager(DnsQueryContext.this.nameServerAddr);
                     }
                  }, DnsQueryContext.ID_REUSE_ON_TIMEOUT_DELAY_MILLIS, TimeUnit.MILLISECONDS);
               }

            }
         });
         DnsQuestion question = this.question();
         DnsQuery query = this.newQuery(this.id, this.nameServerAddr);
         query.setRecursionDesired(this.recursionDesired);
         query.addRecord(DnsSection.QUESTION, question);

         for(DnsRecord record : this.additionals) {
            query.addRecord(DnsSection.ADDITIONAL, record);
         }

         if (this.optResource != null) {
            query.addRecord(DnsSection.ADDITIONAL, this.optResource);
         }

         if (logger.isDebugEnabled()) {
            logger.debug("{} WRITE: {}, [{}: {}], {}", new Object[]{this.channel, this.protocol(), this.id, this.nameServerAddr, question});
         }

         return this.sendQuery(query, flush);
      }
   }

   private void removeFromContextManager(InetSocketAddress nameServerAddr) {
      DnsQueryContext self = this.queryContextManager.remove(nameServerAddr, this.id);

      assert self == this : "Removed DnsQueryContext is not the correct instance";

   }

   private ChannelFuture sendQuery(DnsQuery query, boolean flush) {
      ChannelPromise writePromise = this.channel.newPromise();
      this.writeQuery(query, flush, writePromise);
      return writePromise;
   }

   private void writeQuery(DnsQuery query, boolean flush, ChannelPromise promise) {
      final ChannelFuture writeFuture = flush ? this.channel.writeAndFlush(query, promise) : this.channel.write(query, promise);
      if (writeFuture.isDone()) {
         this.onQueryWriteCompletion(this.queryTimeoutMillis, writeFuture);
      } else {
         writeFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
               DnsQueryContext.this.onQueryWriteCompletion(DnsQueryContext.this.queryTimeoutMillis, writeFuture);
            }
         });
      }

   }

   private void onQueryWriteCompletion(final long queryTimeoutMillis, ChannelFuture writeFuture) {
      if (!writeFuture.isSuccess()) {
         this.finishFailure("failed to send a query '" + this.id + "' via " + this.protocol(), writeFuture.cause(), false);
      } else {
         if (queryTimeoutMillis > 0L) {
            this.timeoutFuture = this.channel.eventLoop().schedule(new Runnable() {
               public void run() {
                  if (!DnsQueryContext.this.promise.isDone()) {
                     DnsQueryContext.this.finishFailure("query '" + DnsQueryContext.this.id + "' via " + DnsQueryContext.this.protocol() + " timed out after " + queryTimeoutMillis + " milliseconds", (Throwable)null, true);
                  }
               }
            }, queryTimeoutMillis, TimeUnit.MILLISECONDS);
         }

      }
   }

   void finishSuccess(AddressedEnvelope envelope, boolean truncated) {
      if (!truncated || !this.retryWithTcp(envelope)) {
         DnsResponse res = (DnsResponse)envelope.content();
         if (res.count(DnsSection.QUESTION) != 1) {
            logger.warn("{} Received a DNS response with invalid number of questions. Expected: 1, found: {}", this.channel, envelope);
         } else if (!this.question().equals(res.recordAt(DnsSection.QUESTION))) {
            logger.warn("{} Received a mismatching DNS response. Expected: [{}], found: {}", new Object[]{this.channel, this.question(), envelope});
         } else if (this.trySuccess(envelope)) {
            return;
         }

         envelope.release();
      }

   }

   private boolean trySuccess(AddressedEnvelope envelope) {
      return this.promise.trySuccess(envelope);
   }

   final boolean finishFailure(String message, Throwable cause, boolean timeout) {
      if (this.promise.isDone()) {
         return false;
      } else {
         DnsQuestion question = this.question();
         StringBuilder buf = new StringBuilder(message.length() + 128);
         buf.append('[').append(this.id).append(": ").append(this.nameServerAddr).append("] ").append(question).append(' ').append(message).append(" (no stack trace available)");
         DnsNameResolverException e;
         if (timeout) {
            e = new DnsNameResolverTimeoutException(this.nameServerAddr, question, buf.toString());
            if (this.retryWithTcpOnTimeout && this.retryWithTcp(e)) {
               return false;
            }
         } else {
            e = new DnsNameResolverException(this.nameServerAddr, question, buf.toString(), cause);
         }

         return this.promise.tryFailure(e);
      }
   }

   private boolean retryWithTcp(final Object originalResult) {
      if (this.socketBootstrap == null) {
         return false;
      } else {
         this.socketBootstrap.connect(this.nameServerAddr).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
               if (!future.isSuccess()) {
                  DnsQueryContext.logger.debug("{} Unable to fallback to TCP [{}: {}]", new Object[]{future.channel(), DnsQueryContext.this.id, DnsQueryContext.this.nameServerAddr, future.cause()});
                  DnsQueryContext.this.finishOriginal(originalResult, future);
               } else {
                  final Channel tcpCh = future.channel();
                  Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise = tcpCh.eventLoop().newPromise();
                  final TcpDnsQueryContext tcpCtx = new TcpDnsQueryContext(tcpCh, (InetSocketAddress)tcpCh.remoteAddress(), DnsQueryContext.this.queryContextManager, 0, DnsQueryContext.this.recursionDesired, DnsQueryContext.this.queryTimeoutMillis, DnsQueryContext.this.question(), DnsQueryContext.this.additionals, promise);
                  tcpCh.pipeline().addLast(new ChannelHandler[]{DnsQueryContext.TCP_ENCODER});
                  tcpCh.pipeline().addLast(new ChannelHandler[]{new TcpDnsResponseDecoder()});
                  tcpCh.pipeline().addLast(new ChannelHandler[]{new ChannelInboundHandlerAdapter() {
                     public void channelRead(ChannelHandlerContext ctx, Object msg) {
                        Channel tcpCh = ctx.channel();
                        DnsResponse response = (DnsResponse)msg;
                        int queryId = response.id();
                        if (DnsQueryContext.logger.isDebugEnabled()) {
                           DnsQueryContext.logger.debug("{} RECEIVED: TCP [{}: {}], {}", new Object[]{tcpCh, queryId, tcpCh.remoteAddress(), response});
                        }

                        DnsQueryContext foundCtx = DnsQueryContext.this.queryContextManager.get(DnsQueryContext.this.nameServerAddr, queryId);
                        if (foundCtx != null && foundCtx.isDone()) {
                           DnsQueryContext.logger.debug("{} Received a DNS response for a query that was timed out or cancelled : TCP [{}: {}]", new Object[]{tcpCh, queryId, DnsQueryContext.this.nameServerAddr});
                           response.release();
                        } else if (foundCtx == tcpCtx) {
                           tcpCtx.finishSuccess(new AddressedEnvelopeAdapter((InetSocketAddress)ctx.channel().remoteAddress(), (InetSocketAddress)ctx.channel().localAddress(), response), false);
                        } else {
                           response.release();
                           tcpCtx.finishFailure("Received TCP DNS response with unexpected ID", (Throwable)null, false);
                           if (DnsQueryContext.logger.isDebugEnabled()) {
                              DnsQueryContext.logger.debug("{} Received a DNS response with an unexpected ID: TCP [{}: {}]", new Object[]{tcpCh, queryId, tcpCh.remoteAddress()});
                           }
                        }

                     }

                     public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                        if (tcpCtx.finishFailure("TCP fallback error", cause, false) && DnsQueryContext.logger.isDebugEnabled()) {
                           DnsQueryContext.logger.debug("{} Error during processing response: TCP [{}: {}]", new Object[]{ctx.channel(), DnsQueryContext.this.id, ctx.channel().remoteAddress(), cause});
                        }

                     }
                  }});
                  promise.addListener(new FutureListener() {
                     public void operationComplete(Future future) {
                        if (future.isSuccess()) {
                           DnsQueryContext.this.finishSuccess((AddressedEnvelope)future.getNow(), false);
                           ReferenceCountUtil.release(originalResult);
                        } else {
                           DnsQueryContext.this.finishOriginal(originalResult, future);
                        }

                        tcpCh.close();
                     }
                  });
                  tcpCtx.writeQuery(true);
               }
            }
         });
         return true;
      }
   }

   private void finishOriginal(Object originalResult, Future future) {
      if (originalResult instanceof Throwable) {
         Throwable error = (Throwable)originalResult;
         ThrowableUtil.addSuppressed(error, future.cause());
         this.promise.tryFailure(error);
      } else {
         this.finishSuccess((AddressedEnvelope)originalResult, false);
      }

   }

   static {
      logger.debug("-Dio.netty.resolver.dns.idReuseOnTimeoutDelayMillis: {}", ID_REUSE_ON_TIMEOUT_DELAY_MILLIS);
      TCP_ENCODER = new TcpDnsQueryEncoder();
   }

   private static final class AddressedEnvelopeAdapter implements AddressedEnvelope {
      private final InetSocketAddress sender;
      private final InetSocketAddress recipient;
      private final DnsResponse response;

      AddressedEnvelopeAdapter(InetSocketAddress sender, InetSocketAddress recipient, DnsResponse response) {
         this.sender = sender;
         this.recipient = recipient;
         this.response = response;
      }

      public DnsResponse content() {
         return this.response;
      }

      public InetSocketAddress sender() {
         return this.sender;
      }

      public InetSocketAddress recipient() {
         return this.recipient;
      }

      public AddressedEnvelope retain() {
         this.response.retain();
         return this;
      }

      public AddressedEnvelope retain(int increment) {
         this.response.retain(increment);
         return this;
      }

      public AddressedEnvelope touch() {
         this.response.touch();
         return this;
      }

      public AddressedEnvelope touch(Object hint) {
         this.response.touch(hint);
         return this;
      }

      public int refCnt() {
         return this.response.refCnt();
      }

      public boolean release() {
         return this.response.release();
      }

      public boolean release(int decrement) {
         return this.response.release(decrement);
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof AddressedEnvelope)) {
            return false;
         } else {
            AddressedEnvelope<?, SocketAddress> that = (AddressedEnvelope)obj;
            if (this.sender() == null) {
               if (that.sender() != null) {
                  return false;
               }
            } else if (!this.sender().equals(that.sender())) {
               return false;
            }

            if (this.recipient() == null) {
               if (that.recipient() != null) {
                  return false;
               }
            } else if (!this.recipient().equals(that.recipient())) {
               return false;
            }

            return this.response.equals(obj);
         }
      }

      public int hashCode() {
         int hashCode = this.response.hashCode();
         if (this.sender() != null) {
            hashCode = hashCode * 31 + this.sender().hashCode();
         }

         if (this.recipient() != null) {
            hashCode = hashCode * 31 + this.recipient().hashCode();
         }

         return hashCode;
      }
   }
}
