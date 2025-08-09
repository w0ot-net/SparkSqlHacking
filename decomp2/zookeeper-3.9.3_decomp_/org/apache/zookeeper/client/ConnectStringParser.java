package org.apache.zookeeper.client;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import org.apache.zookeeper.common.NetUtils;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.common.StringUtils;

public final class ConnectStringParser {
   private static final int DEFAULT_PORT = 2181;
   private final String chrootPath;
   private final ArrayList serverAddresses = new ArrayList();

   public ConnectStringParser(String connectString) {
      int off = connectString.indexOf(47);
      if (off >= 0) {
         String chrootPath = connectString.substring(off);
         if (chrootPath.length() == 1) {
            this.chrootPath = null;
         } else {
            PathUtils.validatePath(chrootPath);
            this.chrootPath = chrootPath;
         }

         connectString = connectString.substring(0, off);
      } else {
         this.chrootPath = null;
      }

      for(String host : StringUtils.split(connectString, ",")) {
         int port = 2181;
         String[] hostAndPort = NetUtils.getIPV6HostAndPort(host);
         if (hostAndPort.length != 0) {
            host = hostAndPort[0];
            if (hostAndPort.length == 2) {
               port = Integer.parseInt(hostAndPort[1]);
            }
         } else {
            int pidx = host.lastIndexOf(58);
            if (pidx >= 0) {
               if (pidx < host.length() - 1) {
                  port = Integer.parseInt(host.substring(pidx + 1));
               }

               host = host.substring(0, pidx);
            }
         }

         this.serverAddresses.add(InetSocketAddress.createUnresolved(host, port));
      }

   }

   public String getChrootPath() {
      return this.chrootPath;
   }

   public ArrayList getServerAddresses() {
      return this.serverAddresses;
   }
}
