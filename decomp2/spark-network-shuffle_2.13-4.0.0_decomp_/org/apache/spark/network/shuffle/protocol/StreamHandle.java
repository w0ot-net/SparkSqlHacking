package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StreamHandle extends BlockTransferMessage {
   public final long streamId;
   public final int numChunks;

   public StreamHandle(long streamId, int numChunks) {
      this.streamId = streamId;
      this.numChunks = numChunks;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.STREAM_HANDLE;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.streamId, this.numChunks});
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamId", this.streamId).append("numChunks", this.numChunks).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof StreamHandle o)) {
         return false;
      } else {
         return Objects.equals(this.streamId, o.streamId) && Objects.equals(this.numChunks, o.numChunks);
      }
   }

   public int encodedLength() {
      return 12;
   }

   public void encode(ByteBuf buf) {
      buf.writeLong(this.streamId);
      buf.writeInt(this.numChunks);
   }

   public static StreamHandle decode(ByteBuf buf) {
      long streamId = buf.readLong();
      int numChunks = buf.readInt();
      return new StreamHandle(streamId, numChunks);
   }
}
