package io.vertx.core.spi.cluster;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.ClusterSerializable;
import java.util.Objects;

public class RegistrationInfo implements ClusterSerializable {
   private String nodeId;
   private long seq;
   private boolean localOnly;

   public RegistrationInfo() {
   }

   public RegistrationInfo(String nodeId, long seq, boolean localOnly) {
      Objects.requireNonNull(nodeId, "nodeId is null");
      this.nodeId = nodeId;
      this.seq = seq;
      this.localOnly = localOnly;
   }

   public String nodeId() {
      return this.nodeId;
   }

   public long seq() {
      return this.seq;
   }

   public boolean localOnly() {
      return this.localOnly;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         RegistrationInfo that = (RegistrationInfo)o;
         if (this.seq != that.seq) {
            return false;
         } else {
            return this.localOnly != that.localOnly ? false : this.nodeId.equals(that.nodeId);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.nodeId.hashCode();
      result = 31 * result + (int)(this.seq ^ this.seq >>> 32);
      result = 31 * result + (this.localOnly ? 1 : 0);
      return result;
   }

   public String toString() {
      return "RegistrationInfo{nodeId=" + this.nodeId + ", seq=" + this.seq + ", localOnly=" + this.localOnly + '}';
   }

   public void writeToBuffer(Buffer buffer) {
      buffer.appendInt(this.nodeId.length()).appendString(this.nodeId);
      buffer.appendLong(this.seq);
      buffer.appendByte((byte)(this.localOnly ? 1 : 0));
   }

   public int readFromBuffer(int start, Buffer buffer) {
      int len = buffer.getInt(start);
      int pos = start + 4;
      this.nodeId = buffer.getString(pos, pos + len);
      pos += len;
      this.seq = buffer.getLong(pos);
      pos += 8;
      this.localOnly = buffer.getByte(pos) > 0;
      ++pos;
      return pos;
   }
}
