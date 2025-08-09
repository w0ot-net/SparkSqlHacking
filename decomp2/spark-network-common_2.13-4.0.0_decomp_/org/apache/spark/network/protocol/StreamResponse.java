package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.buffer.ManagedBuffer;

public final class StreamResponse extends AbstractResponseMessage {
   public final String streamId;
   public final long byteCount;

   public StreamResponse(String streamId, long byteCount, ManagedBuffer buffer) {
      super(buffer, false);
      this.streamId = streamId;
      this.byteCount = byteCount;
   }

   public Message.Type type() {
      return Message.Type.StreamResponse;
   }

   public int encodedLength() {
      return 8 + Encoders.Strings.encodedLength(this.streamId);
   }

   public void encode(ByteBuf buf) {
      Encoders.Strings.encode(buf, this.streamId);
      buf.writeLong(this.byteCount);
   }

   public ResponseMessage createFailureResponse(String error) {
      return new StreamFailure(this.streamId, error);
   }

   public static StreamResponse decode(ByteBuf buf) {
      String streamId = Encoders.Strings.decode(buf);
      long byteCount = buf.readLong();
      return new StreamResponse(streamId, byteCount, (ManagedBuffer)null);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.byteCount, this.streamId});
   }

   public boolean equals(Object other) {
      if (!(other instanceof StreamResponse o)) {
         return false;
      } else {
         return this.byteCount == o.byteCount && this.streamId.equals(o.streamId);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamId", this.streamId).append("byteCount", this.byteCount).append("body", this.body()).toString();
   }
}
