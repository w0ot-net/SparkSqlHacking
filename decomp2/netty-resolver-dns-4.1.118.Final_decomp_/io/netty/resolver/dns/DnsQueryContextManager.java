package io.netty.resolver.dns;

import io.netty.util.NetUtil;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

final class DnsQueryContextManager {
   private final Map map = new HashMap();

   int add(InetSocketAddress nameServerAddr, DnsQueryContext qCtx) {
      assert !nameServerAddr.isUnresolved();

      DnsQueryContextMap contexts = this.getOrCreateContextMap(nameServerAddr);
      return contexts.add(qCtx);
   }

   DnsQueryContext get(InetSocketAddress nameServerAddr, int id) {
      assert !nameServerAddr.isUnresolved();

      DnsQueryContextMap contexts = this.getContextMap(nameServerAddr);
      return contexts == null ? null : contexts.get(id);
   }

   DnsQueryContext remove(InetSocketAddress nameServerAddr, int id) {
      assert !nameServerAddr.isUnresolved();

      DnsQueryContextMap contexts = this.getContextMap(nameServerAddr);
      return contexts == null ? null : contexts.remove(id);
   }

   private DnsQueryContextMap getContextMap(InetSocketAddress nameServerAddr) {
      synchronized(this.map) {
         return (DnsQueryContextMap)this.map.get(nameServerAddr);
      }
   }

   private DnsQueryContextMap getOrCreateContextMap(InetSocketAddress nameServerAddr) {
      synchronized(this.map) {
         DnsQueryContextMap contexts = (DnsQueryContextMap)this.map.get(nameServerAddr);
         if (contexts != null) {
            return contexts;
         } else {
            DnsQueryContextMap newContexts = new DnsQueryContextMap();
            InetAddress a = nameServerAddr.getAddress();
            int port = nameServerAddr.getPort();
            DnsQueryContextMap old = (DnsQueryContextMap)this.map.put(nameServerAddr, newContexts);

            assert old == null : "DnsQueryContextMap already exists for " + nameServerAddr;

            InetSocketAddress extraAddress = null;
            if (a instanceof Inet4Address) {
               Inet4Address a4 = (Inet4Address)a;
               if (a4.isLoopbackAddress()) {
                  extraAddress = new InetSocketAddress(NetUtil.LOCALHOST6, port);
               } else {
                  extraAddress = new InetSocketAddress(toCompactAddress(a4), port);
               }
            } else if (a instanceof Inet6Address) {
               Inet6Address a6 = (Inet6Address)a;
               if (a6.isLoopbackAddress()) {
                  extraAddress = new InetSocketAddress(NetUtil.LOCALHOST4, port);
               } else if (a6.isIPv4CompatibleAddress()) {
                  extraAddress = new InetSocketAddress(toIPv4Address(a6), port);
               }
            }

            if (extraAddress != null) {
               old = (DnsQueryContextMap)this.map.put(extraAddress, newContexts);

               assert old == null : "DnsQueryContextMap already exists for " + extraAddress;
            }

            return newContexts;
         }
      }
   }

   private static Inet6Address toCompactAddress(Inet4Address a4) {
      byte[] b4 = a4.getAddress();
      byte[] b6 = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, b4[0], b4[1], b4[2], b4[3]};

      try {
         return (Inet6Address)InetAddress.getByAddress(b6);
      } catch (UnknownHostException e) {
         throw new Error(e);
      }
   }

   private static Inet4Address toIPv4Address(Inet6Address a6) {
      assert a6.isIPv4CompatibleAddress();

      byte[] b6 = a6.getAddress();
      byte[] b4 = new byte[]{b6[12], b6[13], b6[14], b6[15]};

      try {
         return (Inet4Address)InetAddress.getByAddress(b4);
      } catch (UnknownHostException e) {
         throw new Error(e);
      }
   }

   private static final class DnsQueryContextMap {
      private final DnsQueryIdSpace idSpace;
      private final IntObjectMap map;

      private DnsQueryContextMap() {
         this.idSpace = new DnsQueryIdSpace();
         this.map = new IntObjectHashMap();
      }

      synchronized int add(DnsQueryContext ctx) {
         int id = this.idSpace.nextId();
         if (id == -1) {
            return -1;
         } else {
            DnsQueryContext oldCtx = (DnsQueryContext)this.map.put(id, ctx);

            assert oldCtx == null;

            return id;
         }
      }

      synchronized DnsQueryContext get(int id) {
         return (DnsQueryContext)this.map.get(id);
      }

      synchronized DnsQueryContext remove(int id) {
         DnsQueryContext result = (DnsQueryContext)this.map.remove(id);
         if (result != null) {
            this.idSpace.pushId(id);
         }

         assert result != null : "DnsQueryContext not found, id: " + id;

         return result;
      }
   }
}
