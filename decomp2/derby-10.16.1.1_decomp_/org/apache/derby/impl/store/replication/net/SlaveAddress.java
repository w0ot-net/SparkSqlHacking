package org.apache.derby.impl.store.replication.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SlaveAddress {
   private final InetAddress hostAddress;
   private final int portNumber;
   public static final int DEFAULT_PORT_NO = 4851;

   public SlaveAddress(String var1, int var2) throws UnknownHostException {
      this.hostAddress = InetAddress.getByName(var1);
      if (var2 > 0) {
         this.portNumber = var2;
      } else {
         this.portNumber = 4851;
      }

   }

   public InetAddress getHostAddress() {
      return this.hostAddress;
   }

   public int getPortNumber() {
      return this.portNumber;
   }
}
