package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.StringArrays;
import org.apache.spark.network.protocol.Encoders.Strings;

public class OpenBlocks extends BlockTransferMessage {
   public final String appId;
   public final String execId;
   public final String[] blockIds;

   public OpenBlocks(String appId, String execId, String[] blockIds) {
      this.appId = appId;
      this.execId = execId;
      this.blockIds = blockIds;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.OPEN_BLOCKS;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.appId, this.execId}) * 41 + Arrays.hashCode(this.blockIds);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("execId", this.execId).append("blockIds", Arrays.toString(this.blockIds)).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof OpenBlocks o)) {
         return false;
      } else {
         return Objects.equals(this.appId, o.appId) && Objects.equals(this.execId, o.execId) && Arrays.equals(this.blockIds, o.blockIds);
      }
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + Strings.encodedLength(this.execId) + StringArrays.encodedLength(this.blockIds);
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      Strings.encode(buf, this.execId);
      StringArrays.encode(buf, this.blockIds);
   }

   public static OpenBlocks decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      String execId = Strings.decode(buf);
      String[] blockIds = StringArrays.decode(buf);
      return new OpenBlocks(appId, execId, blockIds);
   }
}
