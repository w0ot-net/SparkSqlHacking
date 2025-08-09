package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.ByteArrays;
import org.apache.spark.network.protocol.Encoders.Strings;

public class UploadBlockStream extends BlockTransferMessage {
   public final String blockId;
   public final byte[] metadata;

   public UploadBlockStream(String blockId, byte[] metadata) {
      this.blockId = blockId;
      this.metadata = metadata;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.UPLOAD_BLOCK_STREAM;
   }

   public int hashCode() {
      int objectsHashCode = Objects.hashCode(this.blockId);
      return objectsHashCode * 41 + Arrays.hashCode(this.metadata);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("blockId", this.blockId).append("metadata size", this.metadata.length).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof UploadBlockStream o)) {
         return false;
      } else {
         return Objects.equals(this.blockId, o.blockId) && Arrays.equals(this.metadata, o.metadata);
      }
   }

   public int encodedLength() {
      return Strings.encodedLength(this.blockId) + ByteArrays.encodedLength(this.metadata);
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.blockId);
      ByteArrays.encode(buf, this.metadata);
   }

   public static UploadBlockStream decode(ByteBuf buf) {
      String blockId = Strings.decode(buf);
      byte[] metadata = ByteArrays.decode(buf);
      return new UploadBlockStream(blockId, metadata);
   }
}
