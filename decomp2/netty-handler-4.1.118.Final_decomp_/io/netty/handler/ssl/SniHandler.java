package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.util.AsyncMapping;
import io.netty.util.DomainNameMapping;
import io.netty.util.Mapping;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;

public class SniHandler extends AbstractSniHandler {
   private static final Selection EMPTY_SELECTION = new Selection((SslContext)null, (String)null);
   protected final AsyncMapping mapping;
   private volatile Selection selection;

   public SniHandler(Mapping mapping) {
      this((AsyncMapping)(new AsyncMappingAdapter(mapping)));
   }

   public SniHandler(Mapping mapping, int maxClientHelloLength, long handshakeTimeoutMillis) {
      this((AsyncMapping)(new AsyncMappingAdapter(mapping)), maxClientHelloLength, handshakeTimeoutMillis);
   }

   public SniHandler(DomainNameMapping mapping) {
      this((Mapping)mapping);
   }

   public SniHandler(AsyncMapping mapping) {
      this((AsyncMapping)mapping, 0, 0L);
   }

   public SniHandler(AsyncMapping mapping, int maxClientHelloLength, long handshakeTimeoutMillis) {
      super(maxClientHelloLength, handshakeTimeoutMillis);
      this.selection = EMPTY_SELECTION;
      this.mapping = (AsyncMapping)ObjectUtil.checkNotNull(mapping, "mapping");
   }

   public SniHandler(Mapping mapping, long handshakeTimeoutMillis) {
      this((AsyncMapping)(new AsyncMappingAdapter(mapping)), handshakeTimeoutMillis);
   }

   public SniHandler(AsyncMapping mapping, long handshakeTimeoutMillis) {
      this((AsyncMapping)mapping, 0, handshakeTimeoutMillis);
   }

   public String hostname() {
      return this.selection.hostname;
   }

   public SslContext sslContext() {
      return this.selection.context;
   }

   protected Future lookup(ChannelHandlerContext ctx, String hostname) throws Exception {
      return this.mapping.map(hostname, ctx.executor().newPromise());
   }

   protected final void onLookupComplete(ChannelHandlerContext ctx, String hostname, Future future) throws Exception {
      if (!future.isSuccess()) {
         Throwable cause = future.cause();
         if (cause instanceof Error) {
            throw (Error)cause;
         } else {
            throw new DecoderException("failed to get the SslContext for " + hostname, cause);
         }
      } else {
         SslContext sslContext = (SslContext)future.getNow();
         this.selection = new Selection(sslContext, hostname);

         try {
            this.replaceHandler(ctx, hostname, sslContext);
         } catch (Throwable cause) {
            this.selection = EMPTY_SELECTION;
            PlatformDependent.throwException(cause);
         }

      }
   }

   protected void replaceHandler(ChannelHandlerContext ctx, String hostname, SslContext sslContext) throws Exception {
      SslHandler sslHandler = null;

      try {
         sslHandler = this.newSslHandler(sslContext, ctx.alloc());
         ctx.pipeline().replace(this, SslHandler.class.getName(), sslHandler);
         sslHandler = null;
      } finally {
         if (sslHandler != null) {
            ReferenceCountUtil.safeRelease(sslHandler.engine());
         }

      }

   }

   protected SslHandler newSslHandler(SslContext context, ByteBufAllocator allocator) {
      SslHandler sslHandler = context.newHandler(allocator);
      sslHandler.setHandshakeTimeoutMillis(this.handshakeTimeoutMillis);
      return sslHandler;
   }

   private static final class AsyncMappingAdapter implements AsyncMapping {
      private final Mapping mapping;

      private AsyncMappingAdapter(Mapping mapping) {
         this.mapping = (Mapping)ObjectUtil.checkNotNull(mapping, "mapping");
      }

      public Future map(String input, Promise promise) {
         SslContext context;
         try {
            context = (SslContext)this.mapping.map(input);
         } catch (Throwable cause) {
            return promise.setFailure(cause);
         }

         return promise.setSuccess(context);
      }
   }

   private static final class Selection {
      final SslContext context;
      final String hostname;

      Selection(SslContext context, String hostname) {
         this.context = context;
         this.hostname = hostname;
      }
   }
}
