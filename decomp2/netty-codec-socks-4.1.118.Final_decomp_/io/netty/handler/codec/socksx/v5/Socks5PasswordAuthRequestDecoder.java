package io.netty.handler.codec.socksx.v5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;

public class Socks5PasswordAuthRequestDecoder extends ReplayingDecoder {
   public Socks5PasswordAuthRequestDecoder() {
      super(Socks5PasswordAuthRequestDecoder.State.INIT);
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      try {
         switch ((State)this.state()) {
            case INIT:
               int startOffset = in.readerIndex();
               byte version = in.getByte(startOffset);
               if (version != 1) {
                  throw new DecoderException("unsupported subnegotiation version: " + version + " (expected: 1)");
               }

               int usernameLength = in.getUnsignedByte(startOffset + 1);
               int passwordLength = in.getUnsignedByte(startOffset + 2 + usernameLength);
               int totalLength = usernameLength + passwordLength + 3;
               in.skipBytes(totalLength);
               out.add(new DefaultSocks5PasswordAuthRequest(in.toString(startOffset + 2, usernameLength, CharsetUtil.US_ASCII), in.toString(startOffset + 3 + usernameLength, passwordLength, CharsetUtil.US_ASCII)));
               this.checkpoint(Socks5PasswordAuthRequestDecoder.State.SUCCESS);
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

      this.checkpoint(Socks5PasswordAuthRequestDecoder.State.FAILURE);
      Socks5Message m = new DefaultSocks5PasswordAuthRequest("", "");
      m.setDecoderResult(DecoderResult.failure(cause));
      out.add(m);
   }

   public static enum State {
      INIT,
      SUCCESS,
      FAILURE;
   }
}
