package io.netty.handler.codec.socksx.v5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.socksx.SocksVersion;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

public class Socks5CommandRequestDecoder extends ReplayingDecoder {
   private final Socks5AddressDecoder addressDecoder;

   public Socks5CommandRequestDecoder() {
      this(Socks5AddressDecoder.DEFAULT);
   }

   public Socks5CommandRequestDecoder(Socks5AddressDecoder addressDecoder) {
      super(Socks5CommandRequestDecoder.State.INIT);
      this.addressDecoder = (Socks5AddressDecoder)ObjectUtil.checkNotNull(addressDecoder, "addressDecoder");
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      try {
         switch ((State)this.state()) {
            case INIT:
               byte version = in.readByte();
               if (version != SocksVersion.SOCKS5.byteValue()) {
                  throw new DecoderException("unsupported version: " + version + " (expected: " + SocksVersion.SOCKS5.byteValue() + ')');
               }

               Socks5CommandType type = Socks5CommandType.valueOf(in.readByte());
               in.skipBytes(1);
               Socks5AddressType dstAddrType = Socks5AddressType.valueOf(in.readByte());
               String dstAddr = this.addressDecoder.decodeAddress(dstAddrType, in);
               int dstPort = ByteBufUtil.readUnsignedShortBE(in);
               out.add(new DefaultSocks5CommandRequest(type, dstAddrType, dstAddr, dstPort));
               this.checkpoint(Socks5CommandRequestDecoder.State.SUCCESS);
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

      this.checkpoint(Socks5CommandRequestDecoder.State.FAILURE);
      Socks5Message m = new DefaultSocks5CommandRequest(Socks5CommandType.CONNECT, Socks5AddressType.IPv4, "0.0.0.0", 1);
      m.setDecoderResult(DecoderResult.failure(cause));
      out.add(m);
   }

   public static enum State {
      INIT,
      SUCCESS,
      FAILURE;
   }
}
