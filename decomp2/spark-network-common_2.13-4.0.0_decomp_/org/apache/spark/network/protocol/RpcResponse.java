package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NettyManagedBuffer;

public final class RpcResponse extends AbstractResponseMessage {
   public final long requestId;

   public RpcResponse(long requestId, ManagedBuffer message) {
      super(message, true);
      this.requestId = requestId;
   }

   public Message.Type type() {
      return Message.Type.RpcResponse;
   }

   public int encodedLength() {
      return 12;
   }

   public void encode(ByteBuf buf) {
      buf.writeLong(this.requestId);
      buf.writeInt((int)this.body().size());
   }

   public ResponseMessage createFailureResponse(String error) {
      return new RpcFailure(this.requestId, error);
   }

   public static RpcResponse decode(ByteBuf buf) {
      long requestId = buf.readLong();
      buf.readInt();
      return new RpcResponse(requestId, new NettyManagedBuffer(buf.retain()));
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.requestId, this.body()});
   }

   public boolean equals(Object other) {
      if (!(other instanceof RpcResponse o)) {
         return false;
      } else {
         return this.requestId == o.requestId && super.equals(o);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("requestId", this.requestId).append("body", this.body()).toString();
   }
}
