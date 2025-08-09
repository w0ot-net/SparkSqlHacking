package io.netty.handler.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.TypeParameterMatcher;
import java.util.List;

public abstract class MessageToMessageDecoder extends ChannelInboundHandlerAdapter {
   private final TypeParameterMatcher matcher;
   private boolean decodeCalled;
   private boolean messageProduced;

   protected MessageToMessageDecoder() {
      this.matcher = TypeParameterMatcher.find(this, MessageToMessageDecoder.class, "I");
   }

   protected MessageToMessageDecoder(Class inboundMessageType) {
      this.matcher = TypeParameterMatcher.get(inboundMessageType);
   }

   public boolean acceptInboundMessage(Object msg) throws Exception {
      return this.matcher.match(msg);
   }

   public void channelRead(ChannelHandlerContext param1, Object param2) throws Exception {
      // $FF: Couldn't be decompiled
   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      if (!this.isSharable()) {
         if (this.decodeCalled && !this.messageProduced && !ctx.channel().config().isAutoRead()) {
            ctx.read();
         }

         this.decodeCalled = false;
         this.messageProduced = false;
      }

      ctx.fireChannelReadComplete();
   }

   protected abstract void decode(ChannelHandlerContext var1, Object var2, List var3) throws Exception;
}
