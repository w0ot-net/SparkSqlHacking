package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class StreamRequest extends AbstractMessage implements RequestMessage {
   public final String streamId;

   public StreamRequest(String streamId) {
      this.streamId = streamId;
   }

   public Message.Type type() {
      return Message.Type.StreamRequest;
   }

   public int encodedLength() {
      return Encoders.Strings.encodedLength(this.streamId);
   }

   public void encode(ByteBuf buf) {
      Encoders.Strings.encode(buf, this.streamId);
   }

   public static StreamRequest decode(ByteBuf buf) {
      String streamId = Encoders.Strings.decode(buf);
      return new StreamRequest(streamId);
   }

   public int hashCode() {
      return Objects.hashCode(this.streamId);
   }

   public boolean equals(Object other) {
      if (other instanceof StreamRequest o) {
         return this.streamId.equals(o.streamId);
      } else {
         return false;
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamId", this.streamId).toString();
   }
}
