package org.apache.zookeeper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

public class Environment {
   public static final String JAAS_CONF_KEY = "java.security.auth.login.config";

   private static void put(List l, String k, String v) {
      l.add(new Entry(k, v));
   }

   public static List list() {
      List<Entry> l = new ArrayList();
      put(l, "zookeeper.version", Version.getFullVersion());

      try {
         put(l, "host.name", InetAddress.getLocalHost().getCanonicalHostName());
      } catch (UnknownHostException var3) {
         put(l, "host.name", "<NA>");
      }

      put(l, "java.version", System.getProperty("java.version", "<NA>"));
      put(l, "java.vendor", System.getProperty("java.vendor", "<NA>"));
      put(l, "java.home", System.getProperty("java.home", "<NA>"));
      put(l, "java.class.path", System.getProperty("java.class.path", "<NA>"));
      put(l, "java.library.path", System.getProperty("java.library.path", "<NA>"));
      put(l, "java.io.tmpdir", System.getProperty("java.io.tmpdir", "<NA>"));
      put(l, "java.compiler", System.getProperty("java.compiler", "<NA>"));
      put(l, "os.name", System.getProperty("os.name", "<NA>"));
      put(l, "os.arch", System.getProperty("os.arch", "<NA>"));
      put(l, "os.version", System.getProperty("os.version", "<NA>"));
      put(l, "user.name", System.getProperty("user.name", "<NA>"));
      put(l, "user.home", System.getProperty("user.home", "<NA>"));
      put(l, "user.dir", System.getProperty("user.dir", "<NA>"));
      Runtime runtime = Runtime.getRuntime();
      int mb = 1048576;
      put(l, "os.memory.free", runtime.freeMemory() / (long)mb + "MB");
      put(l, "os.memory.max", runtime.maxMemory() / (long)mb + "MB");
      put(l, "os.memory.total", runtime.totalMemory() / (long)mb + "MB");
      return l;
   }

   public static void logEnv(String msg, Logger log) {
      for(Entry e : list()) {
         log.info(msg + e.toString());
      }

   }

   public static class Entry {
      private String k;
      private String v;

      public Entry(String k, String v) {
         this.k = k;
         this.v = v;
      }

      public String getKey() {
         return this.k;
      }

      public String getValue() {
         return this.v;
      }

      public String toString() {
         return this.k + "=" + this.v;
      }
   }
}
