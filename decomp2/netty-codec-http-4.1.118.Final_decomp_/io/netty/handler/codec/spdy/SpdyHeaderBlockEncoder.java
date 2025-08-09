package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.internal.PlatformDependent;

public abstract class SpdyHeaderBlockEncoder {
   static SpdyHeaderBlockEncoder newInstance(SpdyVersion version, int compressionLevel, int windowBits, int memLevel) {
      return (SpdyHeaderBlockEncoder)(PlatformDependent.javaVersion() >= 7 ? new SpdyHeaderBlockZlibEncoder(version, compressionLevel) : new SpdyHeaderBlockJZlibEncoder(version, compressionLevel, windowBits, memLevel));
   }

   abstract ByteBuf encode(ByteBufAllocator var1, SpdyHeadersFrame var2) throws Exception;

   abstract void end();
}
