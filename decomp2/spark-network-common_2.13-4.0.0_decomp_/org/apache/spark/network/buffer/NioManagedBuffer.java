package org.apache.spark.network.buffer;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NioManagedBuffer extends ManagedBuffer {
   private final ByteBuffer buf;

   public NioManagedBuffer(ByteBuffer buf) {
      this.buf = buf;
   }

   public long size() {
      return (long)this.buf.remaining();
   }

   public ByteBuffer nioByteBuffer() throws IOException {
      return this.buf.duplicate();
   }

   public InputStream createInputStream() throws IOException {
      return new ByteBufInputStream(Unpooled.wrappedBuffer(this.buf));
   }

   public ManagedBuffer retain() {
      return this;
   }

   public ManagedBuffer release() {
      return this;
   }

   public Object convertToNetty() throws IOException {
      return Unpooled.wrappedBuffer(this.buf);
   }

   public Object convertToNettyForSsl() throws IOException {
      return Unpooled.wrappedBuffer(this.buf);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("buf", this.buf).toString();
   }
}
