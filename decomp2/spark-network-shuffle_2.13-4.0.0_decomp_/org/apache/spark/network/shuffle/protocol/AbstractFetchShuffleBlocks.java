package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.Strings;
import org.sparkproject.guava.base.Objects;

public abstract class AbstractFetchShuffleBlocks extends BlockTransferMessage {
   public final String appId;
   public final String execId;
   public final int shuffleId;

   protected AbstractFetchShuffleBlocks(String appId, String execId, int shuffleId) {
      this.appId = appId;
      this.execId = execId;
      this.shuffleId = shuffleId;
   }

   public ToStringBuilder toStringHelper() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("execId", this.execId).append("shuffleId", this.shuffleId);
   }

   public abstract int getNumBlocks();

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         AbstractFetchShuffleBlocks that = (AbstractFetchShuffleBlocks)o;
         return this.shuffleId == that.shuffleId && Objects.equal(this.appId, that.appId) && Objects.equal(this.execId, that.execId);
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.appId.hashCode();
      result = 31 * result + this.execId.hashCode();
      result = 31 * result + this.shuffleId;
      return result;
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + Strings.encodedLength(this.execId) + 4;
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      Strings.encode(buf, this.execId);
      buf.writeInt(this.shuffleId);
   }
}
