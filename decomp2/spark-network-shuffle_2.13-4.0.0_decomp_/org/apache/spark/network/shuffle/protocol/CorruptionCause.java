package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.shuffle.checksum.Cause;

public class CorruptionCause extends BlockTransferMessage {
   public Cause cause;

   public CorruptionCause(Cause cause) {
      this.cause = cause;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.CORRUPTION_CAUSE;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("cause", this.cause).toString();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CorruptionCause that = (CorruptionCause)o;
         return this.cause == that.cause;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.cause.hashCode();
   }

   public int encodedLength() {
      return 1;
   }

   public void encode(ByteBuf buf) {
      buf.writeByte(this.cause.ordinal());
   }

   public static CorruptionCause decode(ByteBuf buf) {
      int ordinal = buf.readByte();
      return new CorruptionCause(Cause.values()[ordinal]);
   }
}
