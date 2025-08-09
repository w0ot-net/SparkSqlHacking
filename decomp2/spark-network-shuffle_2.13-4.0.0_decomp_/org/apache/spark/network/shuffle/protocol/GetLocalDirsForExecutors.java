package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.StringArrays;
import org.apache.spark.network.protocol.Encoders.Strings;

public class GetLocalDirsForExecutors extends BlockTransferMessage {
   public final String appId;
   public final String[] execIds;

   public GetLocalDirsForExecutors(String appId, String[] execIds) {
      this.appId = appId;
      this.execIds = execIds;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.GET_LOCAL_DIRS_FOR_EXECUTORS;
   }

   public int hashCode() {
      return Objects.hashCode(this.appId) * 41 + Arrays.hashCode(this.execIds);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("execIds", Arrays.toString(this.execIds)).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof GetLocalDirsForExecutors o)) {
         return false;
      } else {
         return this.appId.equals(o.appId) && Arrays.equals(this.execIds, o.execIds);
      }
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + StringArrays.encodedLength(this.execIds);
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      StringArrays.encode(buf, this.execIds);
   }

   public static GetLocalDirsForExecutors decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      String[] execIds = StringArrays.decode(buf);
      return new GetLocalDirsForExecutors(appId, execIds);
   }
}
