package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocksInitRequestDecoder extends ReplayingDecoder {
   public SocksInitRequestDecoder() {
      super(SocksInitRequestDecoder.State.CHECK_PROTOCOL_VERSION);
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List out) throws Exception {
      switch ((State)this.state()) {
         case CHECK_PROTOCOL_VERSION:
            if (byteBuf.readByte() != SocksProtocolVersion.SOCKS5.byteValue()) {
               out.add(SocksCommonUtils.UNKNOWN_SOCKS_REQUEST);
               break;
            } else {
               this.checkpoint(SocksInitRequestDecoder.State.READ_AUTH_SCHEMES);
            }
         case READ_AUTH_SCHEMES:
            byte authSchemeNum = byteBuf.readByte();
            List<SocksAuthScheme> authSchemes;
            if (authSchemeNum > 0) {
               authSchemes = new ArrayList(authSchemeNum);

               for(int i = 0; i < authSchemeNum; ++i) {
                  authSchemes.add(SocksAuthScheme.valueOf(byteBuf.readByte()));
               }
            } else {
               authSchemes = Collections.emptyList();
            }

            out.add(new SocksInitRequest(authSchemes));
            break;
         default:
            throw new Error();
      }

      ctx.pipeline().remove(this);
   }

   public static enum State {
      CHECK_PROTOCOL_VERSION,
      READ_AUTH_SCHEMES;
   }
}
