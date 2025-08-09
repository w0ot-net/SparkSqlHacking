package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.Strings;
import org.sparkproject.guava.base.Objects;

public class RemoveShuffleMerge extends BlockTransferMessage {
   public final String appId;
   public final int appAttemptId;
   public final int shuffleId;
   public final int shuffleMergeId;

   public RemoveShuffleMerge(String appId, int appAttemptId, int shuffleId, int shuffleMergeId) {
      this.appId = appId;
      this.appAttemptId = appAttemptId;
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.REMOVE_SHUFFLE_MERGE;
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{this.appId, this.appAttemptId, this.shuffleId, this.shuffleMergeId});
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("attemptId", this.appAttemptId).append("shuffleId", this.shuffleId).append("shuffleMergeId", this.shuffleMergeId).toString();
   }

   public boolean equals(Object other) {
      if (other != null && other instanceof RemoveShuffleMerge o) {
         return Objects.equal(this.appId, o.appId) && this.appAttemptId == o.appAttemptId && this.shuffleId == o.shuffleId && this.shuffleMergeId == o.shuffleMergeId;
      } else {
         return false;
      }
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + 4 + 4 + 4;
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      buf.writeInt(this.appAttemptId);
      buf.writeInt(this.shuffleId);
      buf.writeInt(this.shuffleMergeId);
   }

   public static RemoveShuffleMerge decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      int attemptId = buf.readInt();
      int shuffleId = buf.readInt();
      int shuffleMergeId = buf.readInt();
      return new RemoveShuffleMerge(appId, attemptId, shuffleId, shuffleMergeId);
   }
}
