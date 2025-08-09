package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.Strings;
import org.apache.spark.network.server.BlockPushNonFatalFailure;
import org.sparkproject.guava.base.Preconditions;

public class BlockPushReturnCode extends BlockTransferMessage {
   public final byte returnCode;
   public final String failureBlockId;

   public BlockPushReturnCode(byte returnCode, String failureBlockId) {
      Preconditions.checkNotNull(BlockPushNonFatalFailure.getReturnCode(returnCode));
      this.returnCode = returnCode;
      this.failureBlockId = failureBlockId;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.PUSH_BLOCK_RETURN_CODE;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.returnCode, this.failureBlockId});
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("returnCode", this.returnCode).append("failureBlockId", this.failureBlockId).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof BlockPushReturnCode o)) {
         return false;
      } else {
         return this.returnCode == o.returnCode && Objects.equals(this.failureBlockId, o.failureBlockId);
      }
   }

   public int encodedLength() {
      return 1 + Strings.encodedLength(this.failureBlockId);
   }

   public void encode(ByteBuf buf) {
      buf.writeByte(this.returnCode);
      Strings.encode(buf, this.failureBlockId);
   }

   public static BlockPushReturnCode decode(ByteBuf buf) {
      byte type = buf.readByte();
      String failureBlockId = Strings.decode(buf);
      return new BlockPushReturnCode(type, failureBlockId);
   }
}
