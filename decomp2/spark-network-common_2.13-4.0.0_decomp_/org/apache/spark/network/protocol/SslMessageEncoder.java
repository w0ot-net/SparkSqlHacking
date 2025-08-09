package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.stream.ChunkedStream;
import java.io.InputStream;
import java.util.List;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.MESSAGE.;

@Sharable
public final class SslMessageEncoder extends MessageToMessageEncoder {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(SslMessageEncoder.class);
   public static final SslMessageEncoder INSTANCE = new SslMessageEncoder();

   private SslMessageEncoder() {
   }

   public void encode(ChannelHandlerContext ctx, Message in, List out) throws Exception {
      Object body = null;
      long bodyLength = 0L;
      boolean isBodyInFrame = false;
      if (in.body() != null) {
         try {
            bodyLength = in.body().size();
            body = in.body().convertToNettyForSsl();
            isBodyInFrame = in.isBodyInFrame();
         } catch (Exception var14) {
            in.body().release();
            if (in instanceof AbstractResponseMessage) {
               AbstractResponseMessage resp = (AbstractResponseMessage)in;
               String error = var14.getMessage() != null ? var14.getMessage() : "null";
               logger.error("Error processing {} for client {}", var14, new MDC[]{MDC.of(.MODULE$, in), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, ctx.channel().remoteAddress())});
               this.encode(ctx, (Message)resp.createFailureResponse(error), out);
               return;
            }

            throw var14;
         }
      }

      Message.Type msgType = in.type();
      int headerLength = 8 + msgType.encodedLength() + in.encodedLength();
      long frameLength = (long)headerLength + (isBodyInFrame ? bodyLength : 0L);
      ByteBuf header = ctx.alloc().buffer(headerLength);
      header.writeLong(frameLength);
      msgType.encode(header);
      in.encode(header);

      assert header.writableBytes() == 0;

      if (body != null && bodyLength > 0L) {
         if (body instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf)body;
            out.add(Unpooled.wrappedBuffer(new ByteBuf[]{header, byteBuf}));
         } else {
            if (!(body instanceof InputStream) && !(body instanceof ChunkedStream)) {
               throw new IllegalArgumentException("Body must be a ByteBuf, ChunkedStream or an InputStream");
            }

            out.add(new EncryptedMessageWithHeader(in.body(), header, body, bodyLength));
         }
      } else {
         out.add(header);
      }

   }
}
