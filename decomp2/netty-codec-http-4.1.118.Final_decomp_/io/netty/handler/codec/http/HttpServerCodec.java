package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.CombinedChannelDuplexHandler;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public final class HttpServerCodec extends CombinedChannelDuplexHandler implements HttpServerUpgradeHandler.SourceCodec {
   private final Queue queue;

   public HttpServerCodec() {
      this(4096, 8192, 8192);
   }

   public HttpServerCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize));
   }

   /** @deprecated */
   @Deprecated
   public HttpServerCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean validateHeaders) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders));
   }

   /** @deprecated */
   @Deprecated
   public HttpServerCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean validateHeaders, int initialBufferSize) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders).setInitialBufferSize(initialBufferSize));
   }

   /** @deprecated */
   @Deprecated
   public HttpServerCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean validateHeaders, int initialBufferSize, boolean allowDuplicateContentLengths) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders).setInitialBufferSize(initialBufferSize).setAllowDuplicateContentLengths(allowDuplicateContentLengths));
   }

   /** @deprecated */
   @Deprecated
   public HttpServerCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean validateHeaders, int initialBufferSize, boolean allowDuplicateContentLengths, boolean allowPartialChunks) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders).setInitialBufferSize(initialBufferSize).setAllowDuplicateContentLengths(allowDuplicateContentLengths).setAllowPartialChunks(allowPartialChunks));
   }

   public HttpServerCodec(HttpDecoderConfig config) {
      this.queue = new ArrayDeque();
      this.init(new HttpServerRequestDecoder(config), new HttpServerResponseEncoder());
   }

   public void upgradeFrom(ChannelHandlerContext ctx) {
      ctx.pipeline().remove(this);
   }

   private final class HttpServerRequestDecoder extends HttpRequestDecoder {
      HttpServerRequestDecoder(HttpDecoderConfig config) {
         super(config);
      }

      protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List out) throws Exception {
         int oldSize = out.size();
         super.decode(ctx, buffer, out);
         int size = out.size();

         for(int i = oldSize; i < size; ++i) {
            Object obj = out.get(i);
            if (obj instanceof HttpRequest) {
               HttpServerCodec.this.queue.add(((HttpRequest)obj).method());
            }
         }

      }
   }

   private final class HttpServerResponseEncoder extends HttpResponseEncoder {
      private HttpMethod method;

      private HttpServerResponseEncoder() {
      }

      protected void sanitizeHeadersBeforeEncode(HttpResponse msg, boolean isAlwaysEmpty) {
         if (!isAlwaysEmpty && HttpMethod.CONNECT.equals(this.method) && msg.status().codeClass() == HttpStatusClass.SUCCESS) {
            msg.headers().remove((CharSequence)HttpHeaderNames.TRANSFER_ENCODING);
         } else {
            super.sanitizeHeadersBeforeEncode(msg, isAlwaysEmpty);
         }
      }

      protected boolean isContentAlwaysEmpty(HttpResponse msg) {
         this.method = (HttpMethod)HttpServerCodec.this.queue.poll();
         return HttpMethod.HEAD.equals(this.method) || super.isContentAlwaysEmpty(msg);
      }
   }
}
