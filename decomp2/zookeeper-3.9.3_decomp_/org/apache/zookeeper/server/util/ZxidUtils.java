package org.apache.zookeeper.server.util;

public class ZxidUtils {
   public static long getEpochFromZxid(long zxid) {
      return zxid >> 32;
   }

   public static long getCounterFromZxid(long zxid) {
      return zxid & 4294967295L;
   }

   public static long makeZxid(long epoch, long counter) {
      return epoch << 32 | counter & 4294967295L;
   }

   public static String zxidToString(long zxid) {
      return Long.toHexString(zxid);
   }
}
