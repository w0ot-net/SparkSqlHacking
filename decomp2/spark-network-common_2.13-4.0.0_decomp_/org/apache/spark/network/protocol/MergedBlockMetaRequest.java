package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.sparkproject.guava.base.Objects;

public class MergedBlockMetaRequest extends AbstractMessage implements RequestMessage {
   public final long requestId;
   public final String appId;
   public final int shuffleId;
   public final int shuffleMergeId;
   public final int reduceId;

   public MergedBlockMetaRequest(long requestId, String appId, int shuffleId, int shuffleMergeId, int reduceId) {
      super((ManagedBuffer)null, false);
      this.requestId = requestId;
      this.appId = appId;
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.reduceId = reduceId;
   }

   public Message.Type type() {
      return Message.Type.MergedBlockMetaRequest;
   }

   public int encodedLength() {
      return 8 + Encoders.Strings.encodedLength(this.appId) + 4 + 4 + 4;
   }

   public void encode(ByteBuf buf) {
      buf.writeLong(this.requestId);
      Encoders.Strings.encode(buf, this.appId);
      buf.writeInt(this.shuffleId);
      buf.writeInt(this.shuffleMergeId);
      buf.writeInt(this.reduceId);
   }

   public static MergedBlockMetaRequest decode(ByteBuf buf) {
      long requestId = buf.readLong();
      String appId = Encoders.Strings.decode(buf);
      int shuffleId = buf.readInt();
      int shuffleMergeId = buf.readInt();
      int reduceId = buf.readInt();
      return new MergedBlockMetaRequest(requestId, appId, shuffleId, shuffleMergeId, reduceId);
   }

   public int hashCode() {
      return Objects.hashCode(this.requestId, this.appId, this.shuffleId, this.shuffleMergeId, this.reduceId);
   }

   public boolean equals(Object other) {
      if (!(other instanceof MergedBlockMetaRequest o)) {
         return false;
      } else {
         return this.requestId == o.requestId && this.shuffleId == o.shuffleId && this.shuffleMergeId == o.shuffleMergeId && this.reduceId == o.reduceId && Objects.equal(this.appId, o.appId);
      }
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("requestId", this.requestId).append("appId", this.appId).append("shuffleId", this.shuffleId).append("shuffleMergeId", this.shuffleMergeId).append("reduceId", this.reduceId).toString();
   }
}
