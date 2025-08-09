package io.netty.handler.codec.socksx.v5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.socksx.SocksVersion;
import java.util.List;

public class Socks5InitialRequestDecoder extends ReplayingDecoder {
   public Socks5InitialRequestDecoder() {
      super(Socks5InitialRequestDecoder.State.INIT);
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      try {
         switch ((State)this.state()) {
            case INIT:
               byte version = in.readByte();
               if (version != SocksVersion.SOCKS5.byteValue()) {
                  throw new DecoderException("unsupported version: " + version + " (expected: " + SocksVersion.SOCKS5.byteValue() + ')');
               }

               int authMethodCnt = in.readUnsignedByte();
               Socks5AuthMethod[] authMethods = new Socks5AuthMethod[authMethodCnt];

               for(int i = 0; i < authMethodCnt; ++i) {
                  authMethods[i] = Socks5AuthMethod.valueOf(in.readByte());
               }

               out.add(new DefaultSocks5InitialRequest(authMethods));
               this.checkpoint(Socks5InitialRequestDecoder.State.SUCCESS);
            case SUCCESS:
               int readableBytes = this.actualReadableBytes();
               if (readableBytes > 0) {
                  out.add(in.readRetainedSlice(readableBytes));
               }
               break;
            case FAILURE:
               in.skipBytes(this.actualReadableBytes());
         }
      } catch (Exception e) {
         this.fail(out, e);
      }

   }

   private void fail(List out, Exception cause) {
      if (!(cause instanceof DecoderException)) {
         cause = new DecoderException(cause);
      }

      this.checkpoint(Socks5InitialRequestDecoder.State.FAILURE);
      Socks5Message m = new DefaultSocks5InitialRequest(new Socks5AuthMethod[]{Socks5AuthMethod.NO_AUTH});
      m.setDecoderResult(DecoderResult.failure(cause));
      out.add(m);
   }

   public static enum State {
      INIT,
      SUCCESS,
      FAILURE;
   }
}
