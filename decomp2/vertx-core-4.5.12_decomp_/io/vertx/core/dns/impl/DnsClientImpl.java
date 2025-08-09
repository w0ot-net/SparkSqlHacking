package io.vertx.core.dns.impl;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.MaxMessagesRecvByteBufAllocator;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.dns.DatagramDnsQuery;
import io.netty.handler.codec.dns.DatagramDnsQueryEncoder;
import io.netty.handler.codec.dns.DatagramDnsResponseDecoder;
import io.netty.handler.codec.dns.DefaultDnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.handler.codec.dns.DnsSection;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.collection.LongObjectHashMap;
import io.netty.util.collection.LongObjectMap;
import io.netty.util.concurrent.Promise;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.impl.PartialPooledByteBufAllocator;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.DnsClientOptions;
import io.vertx.core.dns.DnsException;
import io.vertx.core.dns.DnsResponseCode;
import io.vertx.core.dns.MxRecord;
import io.vertx.core.dns.SrvRecord;
import io.vertx.core.dns.impl.decoder.RecordDecoder;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.spi.transport.Transport;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public final class DnsClientImpl implements DnsClient {
   private static final char[] HEX_TABLE = "0123456789abcdef".toCharArray();
   private final VertxInternal vertx;
   private final LongObjectMap inProgressMap = new LongObjectHashMap();
   private final InetSocketAddress dnsServer;
   private final ContextInternal context;
   private final DatagramChannel channel;
   private final DnsClientOptions options;
   private volatile Future closed;

   public DnsClientImpl(VertxInternal vertx, DnsClientOptions options) {
      Objects.requireNonNull(options, "no null options accepted");
      Objects.requireNonNull(options.getHost(), "no null host accepted");
      this.options = new DnsClientOptions(options);
      this.dnsServer = new InetSocketAddress(options.getHost(), options.getPort());
      if (this.dnsServer.isUnresolved()) {
         throw new IllegalArgumentException("Cannot resolve the host to a valid ip address");
      } else {
         this.vertx = vertx;
         Transport transport = vertx.transport();
         this.context = vertx.getOrCreateContext();
         this.channel = transport.datagramChannel(this.dnsServer.getAddress() instanceof Inet4Address ? InternetProtocolFamily.IPv4 : InternetProtocolFamily.IPv6);
         this.channel.config().setOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, true);
         MaxMessagesRecvByteBufAllocator bufAllocator = (MaxMessagesRecvByteBufAllocator)this.channel.config().getRecvByteBufAllocator();
         bufAllocator.maxMessagesPerRead(1);
         this.channel.config().setAllocator(PartialPooledByteBufAllocator.INSTANCE);
         this.context.nettyEventLoop().register(this.channel);
         if (options.getLogActivity()) {
            this.channel.pipeline().addLast("logging", new LoggingHandler(options.getActivityLogFormat()));
         }

         this.channel.pipeline().addLast(new ChannelHandler[]{new DatagramDnsQueryEncoder()});
         this.channel.pipeline().addLast(new ChannelHandler[]{new DatagramDnsResponseDecoder()});
         this.channel.pipeline().addLast(new ChannelHandler[]{new SimpleChannelInboundHandler() {
            protected void channelRead0(ChannelHandlerContext ctx, DnsResponse msg) {
               DefaultDnsQuestion question = (DefaultDnsQuestion)msg.recordAt(DnsSection.QUESTION);
               Query query = (Query)DnsClientImpl.this.inProgressMap.get(DnsClientImpl.this.dnsMessageId(msg.id(), question.name()));
               if (query != null) {
                  query.handle(msg);
               }

            }
         }});
      }
   }

   public DnsClient lookup4(String name, Handler handler) {
      this.lookup4(name).onComplete(handler);
      return this;
   }

   public Future lookup4(String name) {
      return this.lookupSingle(name, DnsRecordType.A);
   }

   public DnsClient lookup6(String name, Handler handler) {
      this.lookup6(name).onComplete(handler);
      return this;
   }

   public Future lookup6(String name) {
      return this.lookupSingle(name, DnsRecordType.AAAA);
   }

   public DnsClient lookup(String name, Handler handler) {
      this.lookup(name).onComplete(handler);
      return this;
   }

   public Future lookup(String name) {
      return this.lookupSingle(name, DnsRecordType.A, DnsRecordType.AAAA);
   }

   public DnsClient resolveA(String name, Handler handler) {
      this.resolveA(name).onComplete(handler);
      return this;
   }

   public Future resolveA(String name) {
      return this.lookupList(name, DnsRecordType.A);
   }

   public DnsClient resolveCNAME(String name, Handler handler) {
      this.resolveCNAME(name).onComplete(handler);
      return this;
   }

   public Future resolveCNAME(String name) {
      return this.lookupList(name, DnsRecordType.CNAME);
   }

   public DnsClient resolveMX(String name, Handler handler) {
      this.resolveMX(name).onComplete(handler);
      return this;
   }

   public Future resolveMX(String name) {
      return this.lookupList(name, DnsRecordType.MX);
   }

   public Future resolveTXT(String name) {
      return this.lookupList(name, DnsRecordType.TXT).map((Function)((records) -> {
         List<String> txts = new ArrayList();

         for(List txt : records) {
            txts.addAll(txt);
         }

         return txts;
      }));
   }

   public DnsClient resolveTXT(String name, Handler handler) {
      this.resolveTXT(name).onComplete(handler);
      return this;
   }

   public Future resolvePTR(String name) {
      return this.lookupSingle(name, DnsRecordType.PTR);
   }

   public DnsClient resolvePTR(String name, Handler handler) {
      this.resolvePTR(name).onComplete(handler);
      return this;
   }

   public DnsClient resolveAAAA(String name, Handler handler) {
      this.resolveAAAA(name).onComplete(handler);
      return this;
   }

   public Future resolveAAAA(String name) {
      return this.lookupList(name, DnsRecordType.AAAA);
   }

   public Future resolveNS(String name) {
      return this.lookupList(name, DnsRecordType.NS);
   }

   public DnsClient resolveNS(String name, Handler handler) {
      this.resolveNS(name).onComplete(handler);
      return this;
   }

   public Future resolveSRV(String name) {
      return this.lookupList(name, DnsRecordType.SRV);
   }

   public DnsClient resolveSRV(String name, Handler handler) {
      this.resolveSRV(name).onComplete(handler);
      return this;
   }

   public Future reverseLookup(String address) {
      try {
         InetAddress inetAddress = InetAddress.getByName(address);
         byte[] addr = inetAddress.getAddress();
         StringBuilder reverseName = new StringBuilder(64);
         if (inetAddress instanceof Inet4Address) {
            reverseName.append(addr[3] & 255).append(".").append(addr[2] & 255).append(".").append(addr[1] & 255).append(".").append(addr[0] & 255);
         } else {
            for(int i = 0; i < 16; ++i) {
               reverseName.append(HEX_TABLE[addr[15 - i] & 15]);
               reverseName.append(".");
               reverseName.append(HEX_TABLE[addr[15 - i] >> 4 & 15]);
               if (i != 15) {
                  reverseName.append(".");
               }
            }
         }

         reverseName.append(".in-addr.arpa");
         return this.resolvePTR(reverseName.toString());
      } catch (UnknownHostException e) {
         return Future.failedFuture((Throwable)e);
      }
   }

   public DnsClient reverseLookup(String address, Handler handler) {
      this.reverseLookup(address).onComplete(handler);
      return this;
   }

   private Future lookupSingle(String name, DnsRecordType... types) {
      return this.lookupList(name, types).map((Function)((result) -> result.isEmpty() ? null : result.get(0)));
   }

   private Future lookupList(String name, DnsRecordType... types) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      if (this.closed != null) {
         return ctx.failedFuture((Throwable)ConnectionBase.CLOSED_EXCEPTION);
      } else {
         PromiseInternal<List<T>> promise = ctx.promise();
         Objects.requireNonNull(name, "no null name accepted");
         EventLoop el = this.context.nettyEventLoop();
         Query query = new Query(name, types);
         query.promise.addListener(promise);
         if (el.inEventLoop()) {
            query.run();
         } else {
            el.execute(query::run);
         }

         return promise.future();
      }
   }

   private long dnsMessageId(int id, String query) {
      return ((long)query.hashCode() << 16) + (long)(id & '\uffff');
   }

   public void inProgressQueries(Handler handler) {
      this.context.runOnContext((v) -> handler.handle(this.inProgressMap.size()));
   }

   public void close(Handler handler) {
      this.close().onComplete(handler);
   }

   public Future close() {
      PromiseInternal<Void> promise;
      synchronized(this) {
         if (this.closed != null) {
            return this.closed;
         }

         promise = this.vertx.promise();
         this.closed = promise.future();
      }

      this.context.runOnContext((v) -> {
         (new ArrayList(this.inProgressMap.values())).forEach((query) -> query.fail(ConnectionBase.CLOSED_EXCEPTION));
         this.channel.close().addListener(promise);
      });
      return promise.future();
   }

   private class Query {
      final DatagramDnsQuery msg;
      final Promise promise;
      final String name;
      final DnsRecordType[] types;
      long timerID;

      public Query(String name, DnsRecordType[] types) {
         this.msg = (new DatagramDnsQuery((InetSocketAddress)null, DnsClientImpl.this.dnsServer, ThreadLocalRandom.current().nextInt())).setRecursionDesired(DnsClientImpl.this.options.isRecursionDesired());
         if (!name.endsWith(".")) {
            name = name + ".";
         }

         for(DnsRecordType type : types) {
            this.msg.addRecord(DnsSection.QUESTION, new DefaultDnsQuestion(name, type, 1));
         }

         this.promise = DnsClientImpl.this.context.nettyEventLoop().newPromise();
         this.types = types;
         this.name = name;
      }

      private void fail(Throwable cause) {
         DnsClientImpl.this.inProgressMap.remove(DnsClientImpl.this.dnsMessageId(this.msg.id(), this.name));
         if (this.timerID >= 0L) {
            DnsClientImpl.this.vertx.cancelTimer(this.timerID);
         }

         this.promise.setFailure(cause);
      }

      void handle(DnsResponse msg) {
         DnsResponseCode code = DnsResponseCode.valueOf(msg.code().intValue());
         if (code == DnsResponseCode.NOERROR) {
            DnsClientImpl.this.inProgressMap.remove(DnsClientImpl.this.dnsMessageId(msg.id(), this.name));
            if (this.timerID >= 0L) {
               DnsClientImpl.this.vertx.cancelTimer(this.timerID);
            }

            int count = msg.count(DnsSection.ANSWER);
            List<T> records = new ArrayList(count);

            for(int idx = 0; idx < count; ++idx) {
               DnsRecord a = msg.recordAt(DnsSection.ANSWER, idx);

               T record;
               try {
                  record = (T)RecordDecoder.decode(a);
               } catch (DecoderException e) {
                  this.fail(e);
                  return;
               }

               if (this.isRequestedType(a.type(), this.types)) {
                  records.add(record);
               }
            }

            if (records.size() > 0 && (records.get(0) instanceof MxRecordImpl || records.get(0) instanceof SrvRecordImpl)) {
               Collections.sort(records);
            }

            this.promise.setSuccess(records);
         } else {
            this.fail(new DnsException(code));
         }

      }

      void run() {
         DnsClientImpl.this.inProgressMap.put(DnsClientImpl.this.dnsMessageId(this.msg.id(), this.name), this);
         this.timerID = DnsClientImpl.this.vertx.setTimer(DnsClientImpl.this.options.getQueryTimeout(), (id) -> {
            this.timerID = -1L;
            DnsClientImpl.this.context.runOnContext((v) -> this.fail(new VertxException("DNS query timeout for " + this.name)));
         });
         DnsClientImpl.this.channel.writeAndFlush(this.msg).addListener((ChannelFutureListener)(future) -> {
            if (!future.isSuccess()) {
               DnsClientImpl.this.context.emit(future.cause(), this::fail);
            }

         });
      }

      private boolean isRequestedType(DnsRecordType dnsRecordType, DnsRecordType[] types) {
         for(DnsRecordType t : types) {
            if (t.equals(dnsRecordType)) {
               return true;
            }
         }

         return false;
      }
   }
}
