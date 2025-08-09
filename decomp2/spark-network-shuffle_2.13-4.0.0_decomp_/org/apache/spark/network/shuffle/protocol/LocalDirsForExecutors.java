package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.IntArrays;
import org.apache.spark.network.protocol.Encoders.StringArrays;

public class LocalDirsForExecutors extends BlockTransferMessage {
   private final String[] execIds;
   private final int[] numLocalDirsByExec;
   private final String[] allLocalDirs;

   public LocalDirsForExecutors(Map localDirsByExec) {
      this.execIds = new String[localDirsByExec.size()];
      this.numLocalDirsByExec = new int[localDirsByExec.size()];
      ArrayList<String> localDirs = new ArrayList();
      int index = 0;

      for(Map.Entry e : localDirsByExec.entrySet()) {
         this.execIds[index] = (String)e.getKey();
         this.numLocalDirsByExec[index] = ((String[])e.getValue()).length;
         Collections.addAll(localDirs, (String[])e.getValue());
         ++index;
      }

      this.allLocalDirs = (String[])localDirs.toArray(new String[0]);
   }

   private LocalDirsForExecutors(String[] execIds, int[] numLocalDirsByExec, String[] allLocalDirs) {
      this.execIds = execIds;
      this.numLocalDirsByExec = numLocalDirsByExec;
      this.allLocalDirs = allLocalDirs;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.LOCAL_DIRS_FOR_EXECUTORS;
   }

   public int hashCode() {
      return Arrays.hashCode(this.execIds);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("execIds", Arrays.toString(this.execIds)).append("numLocalDirsByExec", Arrays.toString(this.numLocalDirsByExec)).append("allLocalDirs", Arrays.toString(this.allLocalDirs)).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof LocalDirsForExecutors o)) {
         return false;
      } else {
         return Arrays.equals(this.execIds, o.execIds) && Arrays.equals(this.numLocalDirsByExec, o.numLocalDirsByExec) && Arrays.equals(this.allLocalDirs, o.allLocalDirs);
      }
   }

   public int encodedLength() {
      return StringArrays.encodedLength(this.execIds) + IntArrays.encodedLength(this.numLocalDirsByExec) + StringArrays.encodedLength(this.allLocalDirs);
   }

   public void encode(ByteBuf buf) {
      StringArrays.encode(buf, this.execIds);
      IntArrays.encode(buf, this.numLocalDirsByExec);
      StringArrays.encode(buf, this.allLocalDirs);
   }

   public static LocalDirsForExecutors decode(ByteBuf buf) {
      String[] execIds = StringArrays.decode(buf);
      int[] numLocalDirsByExec = IntArrays.decode(buf);
      String[] allLocalDirs = StringArrays.decode(buf);
      return new LocalDirsForExecutors(execIds, numLocalDirsByExec, allLocalDirs);
   }

   public Map getLocalDirsByExec() {
      Map<String, String[]> localDirsByExec = new HashMap();
      int index = 0;
      int localDirsIndex = 0;

      for(int length : this.numLocalDirsByExec) {
         localDirsByExec.put(this.execIds[index], (String[])Arrays.copyOfRange(this.allLocalDirs, localDirsIndex, localDirsIndex + length));
         localDirsIndex += length;
         ++index;
      }

      return localDirsByExec;
   }
}
