package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NettyManagedBuffer;

public final class OneWayMessage extends AbstractMessage implements RequestMessage {
   public OneWayMessage(ManagedBuffer body) {
      super(body, true);
   }

   public Message.Type type() {
      return Message.Type.OneWayMessage;
   }

   public int encodedLength() {
      return 4;
   }

   public void encode(ByteBuf buf) {
      buf.writeInt((int)this.body().size());
   }

   public static OneWayMessage decode(ByteBuf buf) {
      buf.readInt();
      return new OneWayMessage(new NettyManagedBuffer(buf.retain()));
   }

   public int hashCode() {
      return Objects.hashCode(this.body());
   }

   public boolean equals(Object other) {
      if (other instanceof OneWayMessage o) {
         return super.equals(o);
      } else {
         return false;
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("body", this.body()).toString();
   }
}
