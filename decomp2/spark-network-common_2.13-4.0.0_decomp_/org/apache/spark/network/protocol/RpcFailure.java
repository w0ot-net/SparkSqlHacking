package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class RpcFailure extends AbstractMessage implements ResponseMessage {
   public final long requestId;
   public final String errorString;

   public RpcFailure(long requestId, String errorString) {
      this.requestId = requestId;
      this.errorString = errorString;
   }

   public Message.Type type() {
      return Message.Type.RpcFailure;
   }

   public int encodedLength() {
      return 8 + Encoders.Strings.encodedLength(this.errorString);
   }

   public void encode(ByteBuf buf) {
      buf.writeLong(this.requestId);
      Encoders.Strings.encode(buf, this.errorString);
   }

   public static RpcFailure decode(ByteBuf buf) {
      long requestId = buf.readLong();
      String errorString = Encoders.Strings.decode(buf);
      return new RpcFailure(requestId, errorString);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.requestId, this.errorString});
   }

   public boolean equals(Object other) {
      if (!(other instanceof RpcFailure o)) {
         return false;
      } else {
         return this.requestId == o.requestId && this.errorString.equals(o.errorString);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("requestId", this.requestId).append("errorString", this.errorString).toString();
   }
}
