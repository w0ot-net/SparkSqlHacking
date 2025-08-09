package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class StreamFailure extends AbstractMessage implements ResponseMessage {
   public final String streamId;
   public final String error;

   public StreamFailure(String streamId, String error) {
      this.streamId = streamId;
      this.error = error;
   }

   public Message.Type type() {
      return Message.Type.StreamFailure;
   }

   public int encodedLength() {
      return Encoders.Strings.encodedLength(this.streamId) + Encoders.Strings.encodedLength(this.error);
   }

   public void encode(ByteBuf buf) {
      Encoders.Strings.encode(buf, this.streamId);
      Encoders.Strings.encode(buf, this.error);
   }

   public static StreamFailure decode(ByteBuf buf) {
      String streamId = Encoders.Strings.decode(buf);
      String error = Encoders.Strings.decode(buf);
      return new StreamFailure(streamId, error);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.streamId, this.error});
   }

   public boolean equals(Object other) {
      if (!(other instanceof StreamFailure o)) {
         return false;
      } else {
         return this.streamId.equals(o.streamId) && this.error.equals(o.error);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("streamId", this.streamId).append("error", this.error).toString();
   }
}
