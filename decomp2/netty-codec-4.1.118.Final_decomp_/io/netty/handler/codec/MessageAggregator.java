package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

public abstract class MessageAggregator extends MessageToMessageDecoder {
   private static final int DEFAULT_MAX_COMPOSITEBUFFER_COMPONENTS = 1024;
   private final int maxContentLength;
   private ByteBufHolder currentMessage;
   private boolean handlingOversizedMessage;
   private int maxCumulationBufferComponents = 1024;
   private ChannelHandlerContext ctx;
   private ChannelFutureListener continueResponseWriteListener;
   private boolean aggregating;
   private boolean handleIncompleteAggregateDuringClose = true;

   protected MessageAggregator(int maxContentLength) {
      validateMaxContentLength(maxContentLength);
      this.maxContentLength = maxContentLength;
   }

   protected MessageAggregator(int maxContentLength, Class inboundMessageType) {
      super(inboundMessageType);
      validateMaxContentLength(maxContentLength);
      this.maxContentLength = maxContentLength;
   }

   private static void validateMaxContentLength(int maxContentLength) {
      ObjectUtil.checkPositiveOrZero(maxContentLength, "maxContentLength");
   }

   public boolean acceptInboundMessage(Object msg) throws Exception {
      if (!super.acceptInboundMessage(msg)) {
         return false;
      } else if (this.isAggregated(msg)) {
         return false;
      } else if (this.isStartMessage(msg)) {
         return true;
      } else {
         return this.aggregating && this.isContentMessage(msg);
      }
   }

   protected abstract boolean isStartMessage(Object var1) throws Exception;

   protected abstract boolean isContentMessage(Object var1) throws Exception;

   protected abstract boolean isLastContentMessage(ByteBufHolder var1) throws Exception;

   protected abstract boolean isAggregated(Object var1) throws Exception;

   public final int maxContentLength() {
      return this.maxContentLength;
   }

   public final int maxCumulationBufferComponents() {
      return this.maxCumulationBufferComponents;
   }

   public final void setMaxCumulationBufferComponents(int maxCumulationBufferComponents) {
      if (maxCumulationBufferComponents < 2) {
         throw new IllegalArgumentException("maxCumulationBufferComponents: " + maxCumulationBufferComponents + " (expected: >= 2)");
      } else if (this.ctx == null) {
         this.maxCumulationBufferComponents = maxCumulationBufferComponents;
      } else {
         throw new IllegalStateException("decoder properties cannot be changed once the decoder is added to a pipeline.");
      }
   }

   /** @deprecated */
   @Deprecated
   public final boolean isHandlingOversizedMessage() {
      return this.handlingOversizedMessage;
   }

   protected final ChannelHandlerContext ctx() {
      if (this.ctx == null) {
         throw new IllegalStateException("not added to a pipeline yet");
      } else {
         return this.ctx;
      }
   }

   protected void decode(final ChannelHandlerContext ctx, Object msg, List out) throws Exception {
      if (this.isStartMessage(msg)) {
         this.aggregating = true;
         this.handlingOversizedMessage = false;
         if (this.currentMessage != null) {
            this.currentMessage.release();
            this.currentMessage = null;
            throw new MessageAggregationException();
         }

         Object continueResponse = this.newContinueResponse(msg, this.maxContentLength, ctx.pipeline());
         if (continueResponse != null) {
            ChannelFutureListener listener = this.continueResponseWriteListener;
            if (listener == null) {
               this.continueResponseWriteListener = listener = new ChannelFutureListener() {
                  public void operationComplete(ChannelFuture future) throws Exception {
                     if (!future.isSuccess()) {
                        ctx.fireExceptionCaught(future.cause());
                     }

                  }
               };
            }

            boolean closeAfterWrite = this.closeAfterContinueResponse(continueResponse);
            this.handlingOversizedMessage = this.ignoreContentAfterContinueResponse(continueResponse);
            ChannelFuture future = ctx.writeAndFlush(continueResponse).addListener(listener);
            if (closeAfterWrite) {
               this.handleIncompleteAggregateDuringClose = false;
               future.addListener(ChannelFutureListener.CLOSE);
               return;
            }

            if (this.handlingOversizedMessage) {
               return;
            }
         } else if (this.isContentLengthInvalid(msg, this.maxContentLength)) {
            this.invokeHandleOversizedMessage(ctx, msg);
            return;
         }

         if (msg instanceof DecoderResultProvider && !((DecoderResultProvider)msg).decoderResult().isSuccess()) {
            O aggregated;
            if (msg instanceof ByteBufHolder) {
               aggregated = (O)this.beginAggregation(msg, ((ByteBufHolder)msg).content().retain());
            } else {
               aggregated = (O)this.beginAggregation(msg, Unpooled.EMPTY_BUFFER);
            }

            this.finishAggregation0(aggregated);
            out.add(aggregated);
            return;
         }

         CompositeByteBuf content = ctx.alloc().compositeBuffer(this.maxCumulationBufferComponents);
         if (msg instanceof ByteBufHolder) {
            appendPartialContent(content, ((ByteBufHolder)msg).content());
         }

         this.currentMessage = this.beginAggregation(msg, content);
      } else {
         if (!this.isContentMessage(msg)) {
            throw new MessageAggregationException();
         }

         if (this.currentMessage == null) {
            return;
         }

         CompositeByteBuf content = (CompositeByteBuf)this.currentMessage.content();
         C m = (C)((ByteBufHolder)msg);
         if (content.readableBytes() > this.maxContentLength - m.content().readableBytes()) {
            S s = (S)this.currentMessage;
            this.invokeHandleOversizedMessage(ctx, s);
            return;
         }

         appendPartialContent(content, m.content());
         this.aggregate(this.currentMessage, m);
         boolean last;
         if (m instanceof DecoderResultProvider) {
            DecoderResult decoderResult = ((DecoderResultProvider)m).decoderResult();
            if (!decoderResult.isSuccess()) {
               if (this.currentMessage instanceof DecoderResultProvider) {
                  ((DecoderResultProvider)this.currentMessage).setDecoderResult(DecoderResult.failure(decoderResult.cause()));
               }

               last = true;
            } else {
               last = this.isLastContentMessage(m);
            }
         } else {
            last = this.isLastContentMessage(m);
         }

         if (last) {
            this.finishAggregation0(this.currentMessage);
            out.add(this.currentMessage);
            this.currentMessage = null;
         }
      }

   }

   private static void appendPartialContent(CompositeByteBuf content, ByteBuf partialContent) {
      if (partialContent.isReadable()) {
         content.addComponent(true, partialContent.retain());
      }

   }

   protected abstract boolean isContentLengthInvalid(Object var1, int var2) throws Exception;

   protected abstract Object newContinueResponse(Object var1, int var2, ChannelPipeline var3) throws Exception;

   protected abstract boolean closeAfterContinueResponse(Object var1) throws Exception;

   protected abstract boolean ignoreContentAfterContinueResponse(Object var1) throws Exception;

   protected abstract ByteBufHolder beginAggregation(Object var1, ByteBuf var2) throws Exception;

   protected void aggregate(ByteBufHolder aggregated, ByteBufHolder content) throws Exception {
   }

   private void finishAggregation0(ByteBufHolder aggregated) throws Exception {
      this.aggregating = false;
      this.finishAggregation(aggregated);
   }

   protected void finishAggregation(ByteBufHolder aggregated) throws Exception {
   }

   private void invokeHandleOversizedMessage(ChannelHandlerContext ctx, Object oversized) throws Exception {
      this.handlingOversizedMessage = true;
      this.currentMessage = null;
      this.handleIncompleteAggregateDuringClose = false;

      try {
         this.handleOversizedMessage(ctx, oversized);
      } finally {
         ReferenceCountUtil.release(oversized);
      }

   }

   protected void handleOversizedMessage(ChannelHandlerContext ctx, Object oversized) throws Exception {
      ctx.fireExceptionCaught(new TooLongFrameException("content length exceeded " + this.maxContentLength() + " bytes."));
   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      if (this.currentMessage != null && !ctx.channel().config().isAutoRead()) {
         ctx.read();
      }

      ctx.fireChannelReadComplete();
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      if (this.aggregating && this.handleIncompleteAggregateDuringClose) {
         ctx.fireExceptionCaught(new PrematureChannelClosureException("Channel closed while still aggregating message"));
      }

      try {
         super.channelInactive(ctx);
      } finally {
         this.releaseCurrentMessage();
      }

   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      this.ctx = ctx;
   }

   public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
      try {
         super.handlerRemoved(ctx);
      } finally {
         this.releaseCurrentMessage();
      }

   }

   private void releaseCurrentMessage() {
      if (this.currentMessage != null) {
         this.currentMessage.release();
         this.currentMessage = null;
         this.handlingOversizedMessage = false;
         this.aggregating = false;
      }

   }
}
