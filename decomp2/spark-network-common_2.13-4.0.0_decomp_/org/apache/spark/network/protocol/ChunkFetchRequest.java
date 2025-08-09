package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class ChunkFetchRequest extends AbstractMessage implements RequestMessage {
   public final StreamChunkId streamChunkId;

   public ChunkFetchRequest(StreamChunkId streamChunkId) {
      this.streamChunkId = streamChunkId;
   }

   public Message.Type type() {
      return Message.Type.ChunkFetchRequest;
   }

   public int encodedLength() {
      return this.streamChunkId.encodedLength();
   }

   public void encode(ByteBuf buf) {
      this.streamChunkId.encode(buf);
   }

   public static ChunkFetchRequest decode(ByteBuf buf) {
      return new ChunkFetchRequest(StreamChunkId.decode(buf));
   }

   public int hashCode() {
      return this.streamChunkId.hashCode();
   }

   public boolean equals(Object other) {
      if (other instanceof ChunkFetchRequest o) {
         return this.streamChunkId.equals(o.streamChunkId);
      } else {
         return false;
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamChunkId", this.streamChunkId).toString();
   }
}
