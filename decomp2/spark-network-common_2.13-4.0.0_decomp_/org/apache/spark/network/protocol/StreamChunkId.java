package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public record StreamChunkId(long streamId, int chunkIndex) implements Encodable {
   public int encodedLength() {
      return 12;
   }

   public void encode(ByteBuf buffer) {
      buffer.writeLong(this.streamId);
      buffer.writeInt(this.chunkIndex);
   }

   public static StreamChunkId decode(ByteBuf buffer) {
      assert buffer.readableBytes() >= 12;

      long streamId = buffer.readLong();
      int chunkIndex = buffer.readInt();
      return new StreamChunkId(streamId, chunkIndex);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.streamId, this.chunkIndex});
   }

   public boolean equals(Object other) {
      if (!(other instanceof StreamChunkId o)) {
         return false;
      } else {
         return this.streamId == o.streamId && this.chunkIndex == o.chunkIndex;
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamId", this.streamId).append("chunkIndex", this.chunkIndex).toString();
   }
}
