package org.apache.spark.network.shuffle.protocol;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encodable;
import org.apache.spark.network.protocol.Encoders.StringArrays;
import org.apache.spark.network.protocol.Encoders.Strings;

public class ExecutorShuffleInfo implements Encodable {
   public final String[] localDirs;
   public final int subDirsPerLocalDir;
   public final String shuffleManager;

   @JsonCreator
   public ExecutorShuffleInfo(@JsonProperty("localDirs") String[] localDirs, @JsonProperty("subDirsPerLocalDir") int subDirsPerLocalDir, @JsonProperty("shuffleManager") String shuffleManager) {
      this.localDirs = localDirs;
      this.subDirsPerLocalDir = subDirsPerLocalDir;
      this.shuffleManager = shuffleManager;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.subDirsPerLocalDir, this.shuffleManager}) * 41 + Arrays.hashCode(this.localDirs);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("localDirs", Arrays.toString(this.localDirs)).append("subDirsPerLocalDir", this.subDirsPerLocalDir).append("shuffleManager", this.shuffleManager).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof ExecutorShuffleInfo o)) {
         return false;
      } else {
         return Arrays.equals(this.localDirs, o.localDirs) && this.subDirsPerLocalDir == o.subDirsPerLocalDir && Objects.equals(this.shuffleManager, o.shuffleManager);
      }
   }

   public int encodedLength() {
      return StringArrays.encodedLength(this.localDirs) + 4 + Strings.encodedLength(this.shuffleManager);
   }

   public void encode(ByteBuf buf) {
      StringArrays.encode(buf, this.localDirs);
      buf.writeInt(this.subDirsPerLocalDir);
      Strings.encode(buf, this.shuffleManager);
   }

   public static ExecutorShuffleInfo decode(ByteBuf buf) {
      String[] localDirs = StringArrays.decode(buf);
      int subDirsPerLocalDir = buf.readInt();
      String shuffleManager = Strings.decode(buf);
      return new ExecutorShuffleInfo(localDirs, subDirsPerLocalDir, shuffleManager);
   }
}
