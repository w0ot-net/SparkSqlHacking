package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.Strings;
import org.sparkproject.guava.base.Objects;

public class PushBlockStream extends BlockTransferMessage {
   public final String appId;
   public final int appAttemptId;
   public final int shuffleId;
   public final int shuffleMergeId;
   public final int mapIndex;
   public final int reduceId;
   public final int index;

   public PushBlockStream(String appId, int appAttemptId, int shuffleId, int shuffleMergeId, int mapIndex, int reduceId, int index) {
      this.appId = appId;
      this.appAttemptId = appAttemptId;
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.mapIndex = mapIndex;
      this.reduceId = reduceId;
      this.index = index;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.PUSH_BLOCK_STREAM;
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{this.appId, this.appAttemptId, this.shuffleId, this.shuffleMergeId, this.mapIndex, this.reduceId, this.index});
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("attemptId", this.appAttemptId).append("shuffleId", this.shuffleId).append("shuffleMergeId", this.shuffleMergeId).append("mapIndex", this.mapIndex).append("reduceId", this.reduceId).append("index", this.index).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof PushBlockStream o)) {
         return false;
      } else {
         return Objects.equal(this.appId, o.appId) && this.appAttemptId == o.appAttemptId && this.shuffleId == o.shuffleId && this.shuffleMergeId == o.shuffleMergeId && this.mapIndex == o.mapIndex && this.reduceId == o.reduceId && this.index == o.index;
      }
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + 4 + 4 + 4 + 4 + 4 + 4;
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      buf.writeInt(this.appAttemptId);
      buf.writeInt(this.shuffleId);
      buf.writeInt(this.shuffleMergeId);
      buf.writeInt(this.mapIndex);
      buf.writeInt(this.reduceId);
      buf.writeInt(this.index);
   }

   public static PushBlockStream decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      int attemptId = buf.readInt();
      int shuffleId = buf.readInt();
      int shuffleMergeId = buf.readInt();
      int mapIdx = buf.readInt();
      int reduceId = buf.readInt();
      int index = buf.readInt();
      return new PushBlockStream(appId, attemptId, shuffleId, shuffleMergeId, mapIdx, reduceId, index);
   }
}
