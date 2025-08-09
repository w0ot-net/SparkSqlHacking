package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.PrematureChannelClosureException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public final class HttpClientCodec extends CombinedChannelDuplexHandler implements HttpClientUpgradeHandler.SourceCodec {
   public static final boolean DEFAULT_FAIL_ON_MISSING_RESPONSE = false;
   public static final boolean DEFAULT_PARSE_HTTP_AFTER_CONNECT_REQUEST = false;
   private final Queue queue;
   private final boolean parseHttpAfterConnectRequest;
   private boolean done;
   private final AtomicLong requestResponseCounter;
   private final boolean failOnMissingResponse;

   public HttpClientCodec() {
      this(new HttpDecoderConfig(), false, false);
   }

   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize), false, false);
   }

   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean failOnMissingResponse) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize), false, failOnMissingResponse);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean failOnMissingResponse, boolean validateHeaders) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders), false, failOnMissingResponse);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean failOnMissingResponse, boolean validateHeaders, boolean parseHttpAfterConnectRequest) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders), parseHttpAfterConnectRequest, failOnMissingResponse);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean failOnMissingResponse, boolean validateHeaders, int initialBufferSize) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders).setInitialBufferSize(initialBufferSize), false, failOnMissingResponse);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean failOnMissingResponse, boolean validateHeaders, int initialBufferSize, boolean parseHttpAfterConnectRequest) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders).setInitialBufferSize(initialBufferSize), parseHttpAfterConnectRequest, failOnMissingResponse);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean failOnMissingResponse, boolean validateHeaders, int initialBufferSize, boolean parseHttpAfterConnectRequest, boolean allowDuplicateContentLengths) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders).setInitialBufferSize(initialBufferSize).setAllowDuplicateContentLengths(allowDuplicateContentLengths), parseHttpAfterConnectRequest, failOnMissingResponse);
   }

   /** @deprecated */
   @Deprecated
   public HttpClientCodec(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean failOnMissingResponse, boolean validateHeaders, int initialBufferSize, boolean parseHttpAfterConnectRequest, boolean allowDuplicateContentLengths, boolean allowPartialChunks) {
      this((new HttpDecoderConfig()).setMaxInitialLineLength(maxInitialLineLength).setMaxHeaderSize(maxHeaderSize).setMaxChunkSize(maxChunkSize).setValidateHeaders(validateHeaders).setInitialBufferSize(initialBufferSize).setAllowDuplicateContentLengths(allowDuplicateContentLengths).setAllowPartialChunks(allowPartialChunks), parseHttpAfterConnectRequest, failOnMissingResponse);
   }

   public HttpClientCodec(HttpDecoderConfig config, boolean parseHttpAfterConnectRequest, boolean failOnMissingResponse) {
      this.queue = new ArrayDeque();
      this.requestResponseCounter = new AtomicLong();
      this.init(new Decoder(config), new Encoder());
      this.parseHttpAfterConnectRequest = parseHttpAfterConnectRequest;
      this.failOnMissingResponse = failOnMissingResponse;
   }

   public void prepareUpgradeFrom(ChannelHandlerContext ctx) {
      ((Encoder)this.outboundHandler()).upgraded = true;
   }

   public void upgradeFrom(ChannelHandlerContext ctx) {
      ChannelPipeline p = ctx.pipeline();
      p.remove(this);
   }

   public void setSingleDecode(boolean singleDecode) {
      ((HttpResponseDecoder)this.inboundHandler()).setSingleDecode(singleDecode);
   }

   public boolean isSingleDecode() {
      return ((HttpResponseDecoder)this.inboundHandler()).isSingleDecode();
   }

   private final class Encoder extends HttpRequestEncoder {
      boolean upgraded;

      private Encoder() {
      }

      protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
         if (this.upgraded) {
            out.add(msg);
         } else {
            if (msg instanceof HttpRequest) {
               HttpClientCodec.this.queue.offer(((HttpRequest)msg).method());
            }

            super.encode(ctx, msg, out);
            if (HttpClientCodec.this.failOnMissingResponse && !HttpClientCodec.this.done && msg instanceof LastHttpContent) {
               HttpClientCodec.this.requestResponseCounter.incrementAndGet();
            }

         }
      }
   }

   private final class Decoder extends HttpResponseDecoder {
      Decoder(HttpDecoderConfig config) {
         super(config);
      }

      protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List out) throws Exception {
         if (HttpClientCodec.this.done) {
            int readable = this.actualReadableBytes();
            if (readable == 0) {
               return;
            }

            out.add(buffer.readBytes(readable));
         } else {
            int oldSize = out.size();
            super.decode(ctx, buffer, out);
            if (HttpClientCodec.this.failOnMissingResponse) {
               int size = out.size();

               for(int i = oldSize; i < size; ++i) {
                  this.decrement(out.get(i));
               }
            }
         }

      }

      private void decrement(Object msg) {
         if (msg != null) {
            if (msg instanceof LastHttpContent) {
               HttpClientCodec.this.requestResponseCounter.decrementAndGet();
            }

         }
      }

      protected boolean isContentAlwaysEmpty(HttpMessage msg) {
         HttpMethod method = (HttpMethod)HttpClientCodec.this.queue.poll();
         HttpResponseStatus status = ((HttpResponse)msg).status();
         HttpStatusClass statusClass = status.codeClass();
         int statusCode = status.code();
         if (statusClass == HttpStatusClass.INFORMATIONAL) {
            return super.isContentAlwaysEmpty(msg);
         } else {
            if (method != null) {
               char firstChar = method.name().charAt(0);
               switch (firstChar) {
                  case 'C':
                     if (statusCode == 200 && HttpMethod.CONNECT.equals(method)) {
                        if (!HttpClientCodec.this.parseHttpAfterConnectRequest) {
                           HttpClientCodec.this.done = true;
                           HttpClientCodec.this.queue.clear();
                        }

                        return true;
                     }
                     break;
                  case 'H':
                     if (HttpMethod.HEAD.equals(method)) {
                        return true;
                     }
               }
            }

            return super.isContentAlwaysEmpty(msg);
         }
      }

      public void channelInactive(ChannelHandlerContext ctx) throws Exception {
         super.channelInactive(ctx);
         if (HttpClientCodec.this.failOnMissingResponse) {
            long missingResponses = HttpClientCodec.this.requestResponseCounter.get();
            if (missingResponses > 0L) {
               ctx.fireExceptionCaught(new PrematureChannelClosureException("channel gone inactive with " + missingResponses + " missing response(s)"));
            }
         }

      }
   }
}
