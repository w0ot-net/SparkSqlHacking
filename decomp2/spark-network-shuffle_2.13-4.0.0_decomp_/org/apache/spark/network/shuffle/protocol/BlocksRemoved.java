package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BlocksRemoved extends BlockTransferMessage {
   public final int numRemovedBlocks;

   public BlocksRemoved(int numRemovedBlocks) {
      this.numRemovedBlocks = numRemovedBlocks;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.BLOCKS_REMOVED;
   }

   public int hashCode() {
      return Objects.hashCode(this.numRemovedBlocks);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("numRemovedBlocks", this.numRemovedBlocks).toString();
   }

   public boolean equals(Object other) {
      if (other instanceof BlocksRemoved o) {
         return this.numRemovedBlocks == o.numRemovedBlocks;
      } else {
         return false;
      }
   }

   public int encodedLength() {
      return 4;
   }

   public void encode(ByteBuf buf) {
      buf.writeInt(this.numRemovedBlocks);
   }

   public static BlocksRemoved decode(ByteBuf buf) {
      int numRemovedBlocks = buf.readInt();
      return new BlocksRemoved(numRemovedBlocks);
   }
}
