package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.Strings;

public class DiagnoseCorruption extends BlockTransferMessage {
   public final String appId;
   public final String execId;
   public final int shuffleId;
   public final long mapId;
   public final int reduceId;
   public final long checksum;
   public final String algorithm;

   public DiagnoseCorruption(String appId, String execId, int shuffleId, long mapId, int reduceId, long checksum, String algorithm) {
      this.appId = appId;
      this.execId = execId;
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.reduceId = reduceId;
      this.checksum = checksum;
      this.algorithm = algorithm;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.DIAGNOSE_CORRUPTION;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("execId", this.execId).append("shuffleId", this.shuffleId).append("mapId", this.mapId).append("reduceId", this.reduceId).append("checksum", this.checksum).append("algorithm", this.algorithm).toString();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         DiagnoseCorruption that = (DiagnoseCorruption)o;
         if (this.checksum != that.checksum) {
            return false;
         } else if (this.shuffleId != that.shuffleId) {
            return false;
         } else if (this.mapId != that.mapId) {
            return false;
         } else if (this.reduceId != that.reduceId) {
            return false;
         } else if (!this.algorithm.equals(that.algorithm)) {
            return false;
         } else if (!this.appId.equals(that.appId)) {
            return false;
         } else {
            return this.execId.equals(that.execId);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.appId.hashCode();
      result = 31 * result + this.execId.hashCode();
      result = 31 * result + Integer.hashCode(this.shuffleId);
      result = 31 * result + Long.hashCode(this.mapId);
      result = 31 * result + Integer.hashCode(this.reduceId);
      result = 31 * result + Long.hashCode(this.checksum);
      result = 31 * result + this.algorithm.hashCode();
      return result;
   }

   public int encodedLength() {
      return Strings.encodedLength(this.appId) + Strings.encodedLength(this.execId) + 4 + 8 + 4 + 8 + Strings.encodedLength(this.algorithm);
   }

   public void encode(ByteBuf buf) {
      Strings.encode(buf, this.appId);
      Strings.encode(buf, this.execId);
      buf.writeInt(this.shuffleId);
      buf.writeLong(this.mapId);
      buf.writeInt(this.reduceId);
      buf.writeLong(this.checksum);
      Strings.encode(buf, this.algorithm);
   }

   public static DiagnoseCorruption decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      String execId = Strings.decode(buf);
      int shuffleId = buf.readInt();
      long mapId = buf.readLong();
      int reduceId = buf.readInt();
      long checksum = buf.readLong();
      String algorithm = Strings.decode(buf);
      return new DiagnoseCorruption(appId, execId, shuffleId, mapId, reduceId, checksum, algorithm);
   }
}
