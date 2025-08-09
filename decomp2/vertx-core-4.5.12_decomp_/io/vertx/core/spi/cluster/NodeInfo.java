package io.vertx.core.spi.cluster;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.ClusterSerializable;
import java.util.Objects;

public class NodeInfo implements ClusterSerializable {
   private String host;
   private int port;
   private JsonObject metadata;

   public NodeInfo() {
   }

   public NodeInfo(String host, int port, JsonObject metadata) {
      this.host = (String)Objects.requireNonNull(host, "host is null");
      Arguments.requireInRange(port, 1, 65535, "Not an actual port: " + port);
      this.port = port;
      this.metadata = metadata;
   }

   public String host() {
      return this.host;
   }

   public int port() {
      return this.port;
   }

   public JsonObject metadata() {
      return this.metadata;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         NodeInfo nodeInfo = (NodeInfo)o;
         if (this.port != nodeInfo.port) {
            return false;
         } else {
            return !this.host.equals(nodeInfo.host) ? false : Objects.equals(this.metadata, nodeInfo.metadata);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.host.hashCode();
      result = 31 * result + this.port;
      result = 31 * result + (this.metadata != null ? this.metadata.hashCode() : 0);
      return result;
   }

   public String toString() {
      return "NodeInfo{host='" + this.host + '\'' + ", port=" + this.port + ", metadata=" + this.metadata + '}';
   }

   public void writeToBuffer(Buffer buffer) {
      buffer.appendInt(this.host.length()).appendString(this.host);
      buffer.appendInt(this.port);
      if (this.metadata == null) {
         buffer.appendInt(-1);
      } else {
         Buffer buf = this.metadata.toBuffer();
         buffer.appendInt(buf.length()).appendBuffer(buf);
      }

   }

   public int readFromBuffer(int start, Buffer buffer) {
      int len = buffer.getInt(start);
      int pos = start + 4;
      this.host = buffer.getString(pos, pos + len);
      pos += len;
      this.port = buffer.getInt(pos);
      pos += 4;
      len = buffer.getInt(pos);
      pos += 4;
      if (len == 0) {
         this.metadata = new JsonObject();
      } else if (len > 0) {
         this.metadata = new JsonObject(buffer.getBuffer(pos, pos + len));
         pos += len;
      }

      return pos;
   }
}
