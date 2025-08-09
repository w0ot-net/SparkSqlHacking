package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NettyManagedBuffer;
import org.sparkproject.guava.base.Objects;

public class MergedBlockMetaSuccess extends AbstractResponseMessage {
   public final long requestId;
   public final int numChunks;

   public MergedBlockMetaSuccess(long requestId, int numChunks, ManagedBuffer chunkBitmapsBuffer) {
      super(chunkBitmapsBuffer, true);
      this.requestId = requestId;
      this.numChunks = numChunks;
   }

   public Message.Type type() {
      return Message.Type.MergedBlockMetaSuccess;
   }

   public int hashCode() {
      return Objects.hashCode(this.requestId, this.numChunks);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("requestId", this.requestId).append("numChunks", this.numChunks).toString();
   }

   public int encodedLength() {
      return 12;
   }

   public void encode(ByteBuf buf) {
      buf.writeLong(this.requestId);
      buf.writeInt(this.numChunks);
   }

   public int getNumChunks() {
      return this.numChunks;
   }

   public static MergedBlockMetaSuccess decode(ByteBuf buf) {
      long requestId = buf.readLong();
      int numChunks = buf.readInt();
      buf.retain();
      NettyManagedBuffer managedBuf = new NettyManagedBuffer(buf.duplicate());
      return new MergedBlockMetaSuccess(requestId, numChunks, managedBuf);
   }

   public ResponseMessage createFailureResponse(String error) {
      return new RpcFailure(this.requestId, error);
   }
}
