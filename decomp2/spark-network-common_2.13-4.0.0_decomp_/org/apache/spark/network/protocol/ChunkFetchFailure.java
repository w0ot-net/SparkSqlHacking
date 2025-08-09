package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class ChunkFetchFailure extends AbstractMessage implements ResponseMessage {
   public final StreamChunkId streamChunkId;
   public final String errorString;

   public ChunkFetchFailure(StreamChunkId streamChunkId, String errorString) {
      this.streamChunkId = streamChunkId;
      this.errorString = errorString;
   }

   public Message.Type type() {
      return Message.Type.ChunkFetchFailure;
   }

   public int encodedLength() {
      return this.streamChunkId.encodedLength() + Encoders.Strings.encodedLength(this.errorString);
   }

   public void encode(ByteBuf buf) {
      this.streamChunkId.encode(buf);
      Encoders.Strings.encode(buf, this.errorString);
   }

   public static ChunkFetchFailure decode(ByteBuf buf) {
      StreamChunkId streamChunkId = StreamChunkId.decode(buf);
      String errorString = Encoders.Strings.decode(buf);
      return new ChunkFetchFailure(streamChunkId, errorString);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.streamChunkId, this.errorString});
   }

   public boolean equals(Object other) {
      if (!(other instanceof ChunkFetchFailure o)) {
         return false;
      } else {
         return this.streamChunkId.equals(o.streamChunkId) && this.errorString.equals(o.errorString);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamChunkId", this.streamChunkId).append("errorString", this.errorString).toString();
   }
}
