package io.netty.handler.codec.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AsciiString;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HttpServerUpgradeHandler extends HttpObjectAggregator {
   private final SourceCodec sourceCodec;
   private final UpgradeCodecFactory upgradeCodecFactory;
   private final HttpHeadersFactory headersFactory;
   private final HttpHeadersFactory trailersFactory;
   private boolean handlingUpgrade;

   public HttpServerUpgradeHandler(SourceCodec sourceCodec, UpgradeCodecFactory upgradeCodecFactory) {
      this(sourceCodec, upgradeCodecFactory, 0, DefaultHttpHeadersFactory.headersFactory(), DefaultHttpHeadersFactory.trailersFactory());
   }

   public HttpServerUpgradeHandler(SourceCodec sourceCodec, UpgradeCodecFactory upgradeCodecFactory, int maxContentLength) {
      this(sourceCodec, upgradeCodecFactory, maxContentLength, DefaultHttpHeadersFactory.headersFactory(), DefaultHttpHeadersFactory.trailersFactory());
   }

   public HttpServerUpgradeHandler(SourceCodec sourceCodec, UpgradeCodecFactory upgradeCodecFactory, int maxContentLength, boolean validateHeaders) {
      this(sourceCodec, upgradeCodecFactory, maxContentLength, DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders), DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders));
   }

   public HttpServerUpgradeHandler(SourceCodec sourceCodec, UpgradeCodecFactory upgradeCodecFactory, int maxContentLength, HttpHeadersFactory headersFactory, HttpHeadersFactory trailersFactory) {
      super(maxContentLength);
      this.sourceCodec = (SourceCodec)ObjectUtil.checkNotNull(sourceCodec, "sourceCodec");
      this.upgradeCodecFactory = (UpgradeCodecFactory)ObjectUtil.checkNotNull(upgradeCodecFactory, "upgradeCodecFactory");
      this.headersFactory = (HttpHeadersFactory)ObjectUtil.checkNotNull(headersFactory, "headersFactory");
      this.trailersFactory = (HttpHeadersFactory)ObjectUtil.checkNotNull(trailersFactory, "trailersFactory");
   }

   protected void decode(ChannelHandlerContext ctx, HttpObject msg, List out) throws Exception {
      if (!this.handlingUpgrade) {
         if (!(msg instanceof HttpRequest)) {
            ReferenceCountUtil.retain(msg);
            ctx.fireChannelRead(msg);
            return;
         }

         HttpRequest req = (HttpRequest)msg;
         if (!req.headers().contains((CharSequence)HttpHeaderNames.UPGRADE) || !this.shouldHandleUpgradeRequest(req)) {
            ReferenceCountUtil.retain(msg);
            ctx.fireChannelRead(msg);
            return;
         }

         this.handlingUpgrade = true;
      }

      FullHttpRequest fullRequest;
      if (msg instanceof FullHttpRequest) {
         fullRequest = (FullHttpRequest)msg;
         ReferenceCountUtil.retain(msg);
         out.add(msg);
      } else {
         super.decode(ctx, msg, out);
         if (out.isEmpty()) {
            return;
         }

         assert out.size() == 1;

         this.handlingUpgrade = false;
         fullRequest = (FullHttpRequest)out.get(0);
      }

      if (this.upgrade(ctx, fullRequest)) {
         out.clear();
      }

   }

   protected boolean shouldHandleUpgradeRequest(HttpRequest req) {
      return true;
   }

   private boolean upgrade(ChannelHandlerContext ctx, FullHttpRequest request) {
      List<CharSequence> requestedProtocols = splitHeader(request.headers().get((CharSequence)HttpHeaderNames.UPGRADE));
      int numRequestedProtocols = requestedProtocols.size();
      UpgradeCodec upgradeCodec = null;
      CharSequence upgradeProtocol = null;

      for(int i = 0; i < numRequestedProtocols; ++i) {
         CharSequence p = (CharSequence)requestedProtocols.get(i);
         UpgradeCodec c = this.upgradeCodecFactory.newUpgradeCodec(p);
         if (c != null) {
            upgradeProtocol = p;
            upgradeCodec = c;
            break;
         }
      }

      if (upgradeCodec == null) {
         return false;
      } else {
         List<String> connectionHeaderValues = request.headers().getAll((CharSequence)HttpHeaderNames.CONNECTION);
         if (connectionHeaderValues != null && !connectionHeaderValues.isEmpty()) {
            StringBuilder concatenatedConnectionValue = new StringBuilder(connectionHeaderValues.size() * 10);

            for(CharSequence connectionHeaderValue : connectionHeaderValues) {
               concatenatedConnectionValue.append(connectionHeaderValue).append(',');
            }

            concatenatedConnectionValue.setLength(concatenatedConnectionValue.length() - 1);
            Collection<CharSequence> requiredHeaders = upgradeCodec.requiredUpgradeHeaders();
            List<CharSequence> values = splitHeader(concatenatedConnectionValue);
            if (AsciiString.containsContentEqualsIgnoreCase(values, HttpHeaderNames.UPGRADE) && AsciiString.containsAllContentEqualsIgnoreCase(values, requiredHeaders)) {
               for(CharSequence requiredHeader : requiredHeaders) {
                  if (!request.headers().contains(requiredHeader)) {
                     return false;
                  }
               }

               FullHttpResponse upgradeResponse = this.createUpgradeResponse(upgradeProtocol);
               if (!upgradeCodec.prepareUpgradeResponse(ctx, request, upgradeResponse.headers())) {
                  return false;
               } else {
                  UpgradeEvent event = new UpgradeEvent(upgradeProtocol, request);

                  try {
                     ChannelFuture writeComplete = ctx.writeAndFlush(upgradeResponse);
                     this.sourceCodec.upgradeFrom(ctx);
                     upgradeCodec.upgradeTo(ctx, request);
                     ctx.pipeline().remove(this);
                     ctx.fireUserEventTriggered(event.retain());
                     writeComplete.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                  } finally {
                     event.release();
                  }

                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private FullHttpResponse createUpgradeResponse(CharSequence upgradeProtocol) {
      DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS, Unpooled.EMPTY_BUFFER, this.headersFactory, this.trailersFactory);
      res.headers().add((CharSequence)HttpHeaderNames.CONNECTION, (Object)HttpHeaderValues.UPGRADE);
      res.headers().add((CharSequence)HttpHeaderNames.UPGRADE, (Object)upgradeProtocol);
      return res;
   }

   private static List splitHeader(CharSequence header) {
      StringBuilder builder = new StringBuilder(header.length());
      List<CharSequence> protocols = new ArrayList(4);

      for(int i = 0; i < header.length(); ++i) {
         char c = header.charAt(i);
         if (!Character.isWhitespace(c)) {
            if (c == ',') {
               protocols.add(builder.toString());
               builder.setLength(0);
            } else {
               builder.append(c);
            }
         }
      }

      if (builder.length() > 0) {
         protocols.add(builder.toString());
      }

      return protocols;
   }

   public static final class UpgradeEvent implements ReferenceCounted {
      private final CharSequence protocol;
      private final FullHttpRequest upgradeRequest;

      UpgradeEvent(CharSequence protocol, FullHttpRequest upgradeRequest) {
         this.protocol = protocol;
         this.upgradeRequest = upgradeRequest;
      }

      public CharSequence protocol() {
         return this.protocol;
      }

      public FullHttpRequest upgradeRequest() {
         return this.upgradeRequest;
      }

      public int refCnt() {
         return this.upgradeRequest.refCnt();
      }

      public UpgradeEvent retain() {
         this.upgradeRequest.retain();
         return this;
      }

      public UpgradeEvent retain(int increment) {
         this.upgradeRequest.retain(increment);
         return this;
      }

      public UpgradeEvent touch() {
         this.upgradeRequest.touch();
         return this;
      }

      public UpgradeEvent touch(Object hint) {
         this.upgradeRequest.touch(hint);
         return this;
      }

      public boolean release() {
         return this.upgradeRequest.release();
      }

      public boolean release(int decrement) {
         return this.upgradeRequest.release(decrement);
      }

      public String toString() {
         return "UpgradeEvent [protocol=" + this.protocol + ", upgradeRequest=" + this.upgradeRequest + ']';
      }
   }

   public interface SourceCodec {
      void upgradeFrom(ChannelHandlerContext var1);
   }

   public interface UpgradeCodec {
      Collection requiredUpgradeHeaders();

      boolean prepareUpgradeResponse(ChannelHandlerContext var1, FullHttpRequest var2, HttpHeaders var3);

      void upgradeTo(ChannelHandlerContext var1, FullHttpRequest var2);
   }

   public interface UpgradeCodecFactory {
      UpgradeCodec newUpgradeCodec(CharSequence var1);
   }
}
