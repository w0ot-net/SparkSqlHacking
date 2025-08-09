package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;

interface SpdyFrameDecoderExtendedDelegate extends SpdyFrameDecoderDelegate {
   void readUnknownFrame(int var1, byte var2, ByteBuf var3);
}
