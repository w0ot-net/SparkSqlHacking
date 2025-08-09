package io.netty.handler.codec.socksx.v4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.NetUtil;
import java.util.List;

public class Socks4ClientDecoder extends ReplayingDecoder {
   public Socks4ClientDecoder() {
      super(Socks4ClientDecoder.State.START);
      this.setSingleDecode(true);
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      try {
         switch ((State)this.state()) {
            case START:
               int version = in.readUnsignedByte();
               if (version != 0) {
                  throw new DecoderException("unsupported reply version: " + version + " (expected: 0)");
               }

               Socks4CommandStatus status = Socks4CommandStatus.valueOf(in.readByte());
               int dstPort = ByteBufUtil.readUnsignedShortBE(in);
               String dstAddr = NetUtil.intToIpAddress(ByteBufUtil.readIntBE(in));
               out.add(new DefaultSocks4CommandResponse(status, dstAddr, dstPort));
               this.checkpoint(Socks4ClientDecoder.State.SUCCESS);
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

      Socks4CommandResponse m = new DefaultSocks4CommandResponse(Socks4CommandStatus.REJECTED_OR_FAILED);
      m.setDecoderResult(DecoderResult.failure(cause));
      out.add(m);
      this.checkpoint(Socks4ClientDecoder.State.FAILURE);
   }

   public static enum State {
      START,
      SUCCESS,
      FAILURE;
   }
}
