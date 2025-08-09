package io.vertx.core.net.impl;

import java.io.Serializable;
import java.util.Objects;

public class ServerID implements Serializable {
   public int port;
   public String host;

   public ServerID(int port, String host) {
      this.port = port;
      this.host = host;
   }

   public ServerID() {
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof ServerID)) {
         return false;
      } else {
         ServerID that = (ServerID)o;
         return this.port == that.port && Objects.equals(this.host, that.host);
      }
   }

   public int hashCode() {
      int result = this.port;
      result = 31 * result + this.host.hashCode();
      return result;
   }

   public String toString() {
      return this.host + ":" + this.port;
   }
}
