package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.ByteArrays;
import org.apache.spark.network.protocol.Encoders.Strings;

public class UploadBlock extends BlockTransferMessage {
   public final String appId;
   public final String execId;
   public final String blockId;
   public final byte[] metadata;
   public final byte[] blockData;

   public UploadBlock(String appId, String execId, String blockId, byte[] metadata, byte[] blockData) {
      this.appId = appId;
      this.execId = execId;
      this.blockId = blockId;
      this.metadata = metadata;
      this.blockData = blockData;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.UPLOAD_BLOCK;
   }

   public int hashCode() {
      int objectsHashCode = Objects.hash(new Object[]{this.appId, this.execId, this.blockId});
      return (objectsHashCode * 41 + Arrays.hashCode(this.metadata)) * 41 + Arrays.hashCode(this.blockData);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("execId", this.execId).append("blockId", this.blockId).append("metadata size", this.metadata.length).append("block size", this.blockData.length).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof UploadBlock o)) {
         return false;
      } else {
         return Objects.equals(this.appId, o.appId) && Objects.equals(this.execId, o.execId) && Objects.equals(this.blockId, o.blockId) && Arrays.equals(this.metadata, o.metadata) && Arrays.equals(this.blockData, o.blockData);
      }
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + Strings.encodedLength(this.execId) + Strings.encodedLength(this.blockId) + ByteArrays.encodedLength(this.metadata) + ByteArrays.encodedLength(this.blockData);
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      Strings.encode(buf, this.execId);
      Strings.encode(buf, this.blockId);
      ByteArrays.encode(buf, this.metadata);
      ByteArrays.encode(buf, this.blockData);
   }

   public static UploadBlock decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      String execId = Strings.decode(buf);
      String blockId = Strings.decode(buf);
      byte[] metadata = ByteArrays.decode(buf);
      byte[] blockData = ByteArrays.decode(buf);
      return new UploadBlock(appId, execId, blockId, metadata, blockData);
   }
}
