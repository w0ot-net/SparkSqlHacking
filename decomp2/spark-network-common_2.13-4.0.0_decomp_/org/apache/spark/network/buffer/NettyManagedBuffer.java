package org.apache.spark.network.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NettyManagedBuffer extends ManagedBuffer {
   private final ByteBuf buf;

   public NettyManagedBuffer(ByteBuf buf) {
      this.buf = buf;
   }

   public long size() {
      return (long)this.buf.readableBytes();
   }

   public ByteBuffer nioByteBuffer() throws IOException {
      return this.buf.nioBuffer();
   }

   public InputStream createInputStream() throws IOException {
      return new ByteBufInputStream(this.buf);
   }

   public ManagedBuffer retain() {
      this.buf.retain();
      return this;
   }

   public ManagedBuffer release() {
      this.buf.release();
      return this;
   }

   public Object convertToNetty() throws IOException {
      return this.buf.duplicate().retain();
   }

   public Object convertToNettyForSsl() throws IOException {
      return this.buf.duplicate().retain();
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("buf", this.buf).toString();
   }
}
