package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.Strings;

public class RegisterExecutor extends BlockTransferMessage {
   public final String appId;
   public final String execId;
   public final ExecutorShuffleInfo executorInfo;

   public RegisterExecutor(String appId, String execId, ExecutorShuffleInfo executorInfo) {
      this.appId = appId;
      this.execId = execId;
      this.executorInfo = executorInfo;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.REGISTER_EXECUTOR;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.appId, this.execId, this.executorInfo});
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("execId", this.execId).append("executorInfo", this.executorInfo).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof RegisterExecutor o)) {
         return false;
      } else {
         return Objects.equals(this.appId, o.appId) && Objects.equals(this.execId, o.execId) && Objects.equals(this.executorInfo, o.executorInfo);
      }
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + Strings.encodedLength(this.execId) + this.executorInfo.encodedLength();
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      Strings.encode(buf, this.execId);
      this.executorInfo.encode(buf);
   }

   public static RegisterExecutor decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      String execId = Strings.decode(buf);
      ExecutorShuffleInfo executorShuffleInfo = ExecutorShuffleInfo.decode(buf);
      return new RegisterExecutor(appId, execId, executorShuffleInfo);
   }
}
