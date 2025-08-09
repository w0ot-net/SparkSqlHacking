package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NettyManagedBuffer;

public final class ChunkFetchSuccess extends AbstractResponseMessage {
   public final StreamChunkId streamChunkId;

   public ChunkFetchSuccess(StreamChunkId streamChunkId, ManagedBuffer buffer) {
      super(buffer, true);
      this.streamChunkId = streamChunkId;
   }

   public Message.Type type() {
      return Message.Type.ChunkFetchSuccess;
   }

   public int encodedLength() {
      return this.streamChunkId.encodedLength();
   }

   public void encode(ByteBuf buf) {
      this.streamChunkId.encode(buf);
   }

   public ResponseMessage createFailureResponse(String error) {
      return new ChunkFetchFailure(this.streamChunkId, error);
   }

   public static ChunkFetchSuccess decode(ByteBuf buf) {
      StreamChunkId streamChunkId = StreamChunkId.decode(buf);
      buf.retain();
      NettyManagedBuffer managedBuf = new NettyManagedBuffer(buf.duplicate());
      return new ChunkFetchSuccess(streamChunkId, managedBuf);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.streamChunkId, this.body()});
   }

   public boolean equals(Object other) {
      if (!(other instanceof ChunkFetchSuccess o)) {
         return false;
      } else {
         return this.streamChunkId.equals(o.streamChunkId) && super.equals(o);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamChunkId", this.streamChunkId).append("buffer", this.body()).toString();
   }
}
