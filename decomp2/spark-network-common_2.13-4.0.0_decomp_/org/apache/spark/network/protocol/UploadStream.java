package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NettyManagedBuffer;

public final class UploadStream extends AbstractMessage implements RequestMessage {
   public final long requestId;
   public final ManagedBuffer meta;
   public final long bodyByteCount;

   public UploadStream(long requestId, ManagedBuffer meta, ManagedBuffer body) {
      super(body, false);
      this.requestId = requestId;
      this.meta = meta;
      this.bodyByteCount = body.size();
   }

   private UploadStream(long requestId, ManagedBuffer meta, long bodyByteCount) {
      super((ManagedBuffer)null, false);
      this.requestId = requestId;
      this.meta = meta;
      this.bodyByteCount = bodyByteCount;
   }

   public Message.Type type() {
      return Message.Type.UploadStream;
   }

   public int encodedLength() {
      return 12 + (int)this.meta.size() + 8;
   }

   public void encode(ByteBuf buf) {
      buf.writeLong(this.requestId);

      try {
         ByteBuffer metaBuf = this.meta.nioByteBuffer();
         buf.writeInt(metaBuf.remaining());
         buf.writeBytes(metaBuf);
      } catch (IOException io) {
         throw new RuntimeException(io);
      }

      buf.writeLong(this.bodyByteCount);
   }

   public static UploadStream decode(ByteBuf buf) {
      long requestId = buf.readLong();
      int metaSize = buf.readInt();
      ManagedBuffer meta = new NettyManagedBuffer(buf.readRetainedSlice(metaSize));
      long bodyByteCount = buf.readLong();
      return new UploadStream(requestId, meta, bodyByteCount);
   }

   public int hashCode() {
      return Long.hashCode(this.requestId);
   }

   public boolean equals(Object other) {
      if (!(other instanceof UploadStream o)) {
         return false;
      } else {
         return this.requestId == o.requestId && super.equals(o);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("requestId", this.requestId).append("body", this.body()).toString();
   }
}
