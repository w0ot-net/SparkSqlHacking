package org.apache.zookeeper.server.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.ServerCnxn;

public class IPAuthenticationProvider implements AuthenticationProvider {
   public static final String X_FORWARDED_FOR_HEADER_NAME = "X-Forwarded-For";
   public static final String USE_X_FORWARDED_FOR_KEY = "zookeeper.IPAuthenticationProvider.usexforwardedfor";

   public String getScheme() {
      return "ip";
   }

   public KeeperException.Code handleAuthentication(ServerCnxn cnxn, byte[] authData) {
      String id = cnxn.getRemoteSocketAddress().getAddress().getHostAddress();
      cnxn.addAuthInfo(new Id(this.getScheme(), id));
      return KeeperException.Code.OK;
   }

   public List handleAuthentication(HttpServletRequest request, byte[] authData) {
      List<Id> ids = new ArrayList();
      String ip = getClientIPAddress(request);
      ids.add(new Id(this.getScheme(), ip));
      return Collections.unmodifiableList(ids);
   }

   private byte[] addr2Bytes(String addr) {
      byte[] b = this.v4addr2Bytes(addr);
      return b;
   }

   private byte[] v4addr2Bytes(String addr) {
      String[] parts = addr.split("\\.", -1);
      if (parts.length != 4) {
         return null;
      } else {
         byte[] b = new byte[4];

         for(int i = 0; i < 4; ++i) {
            try {
               int v = Integer.parseInt(parts[i]);
               if (v < 0 || v > 255) {
                  return null;
               }

               b[i] = (byte)v;
            } catch (NumberFormatException var6) {
               return null;
            }
         }

         return b;
      }
   }

   private void mask(byte[] b, int bits) {
      int start = bits / 8;
      int startMask = (1 << 8 - bits % 8) - 1;

      for(int var5 = ~startMask; start < b.length; ++start) {
         b[start] = (byte)(b[start] & var5);
         var5 = 0;
      }

   }

   public boolean matches(String id, String aclExpr) {
      String[] parts = aclExpr.split("/", 2);
      byte[] aclAddr = this.addr2Bytes(parts[0]);
      if (aclAddr == null) {
         return false;
      } else {
         int bits = aclAddr.length * 8;
         if (parts.length == 2) {
            try {
               bits = Integer.parseInt(parts[1]);
               if (bits < 0 || bits > aclAddr.length * 8) {
                  return false;
               }
            } catch (NumberFormatException var8) {
               return false;
            }
         }

         this.mask(aclAddr, bits);
         byte[] remoteAddr = this.addr2Bytes(id);
         if (remoteAddr == null) {
            return false;
         } else {
            this.mask(remoteAddr, bits);

            for(int i = 0; i < remoteAddr.length; ++i) {
               if (remoteAddr[i] != aclAddr[i]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean isAuthenticated() {
      return false;
   }

   public boolean isValid(String id) {
      String[] parts = id.split("/", 2);
      byte[] aclAddr = this.addr2Bytes(parts[0]);
      if (aclAddr == null) {
         return false;
      } else {
         if (parts.length == 2) {
            try {
               int bits = Integer.parseInt(parts[1]);
               if (bits < 0 || bits > aclAddr.length * 8) {
                  return false;
               }
            } catch (NumberFormatException var5) {
               return false;
            }
         }

         return true;
      }
   }

   public static String getClientIPAddress(HttpServletRequest request) {
      if (!Boolean.getBoolean("zookeeper.IPAuthenticationProvider.usexforwardedfor")) {
         return request.getRemoteAddr();
      } else {
         String xForwardedForHeader = request.getHeader("X-Forwarded-For");
         return xForwardedForHeader == null ? request.getRemoteAddr() : (new StringTokenizer(xForwardedForHeader, ",")).nextToken().trim();
      }
   }
}
