package org.joda.time.tz;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTimeZone;

public class ZoneInfoProvider implements Provider {
   private final File iFileDir;
   private final String iResourcePath;
   private final ClassLoader iLoader;
   private final Map iZoneInfoMap;
   private final Set iZoneInfoKeys;

   public ZoneInfoProvider() throws IOException {
      this("org/joda/time/tz/data");
   }

   public ZoneInfoProvider(File var1) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("No file directory provided");
      } else if (!var1.exists()) {
         throw new IOException("File directory doesn't exist: " + var1);
      } else if (!var1.isDirectory()) {
         throw new IOException("File doesn't refer to a directory: " + var1);
      } else {
         this.iFileDir = var1;
         this.iResourcePath = null;
         this.iLoader = null;
         this.iZoneInfoMap = loadZoneInfoMap(this.openResource("ZoneInfoMap"));
         this.iZoneInfoKeys = Collections.unmodifiableSortedSet(new TreeSet(this.iZoneInfoMap.keySet()));
      }
   }

   public ZoneInfoProvider(String var1) throws IOException {
      this(var1, (ClassLoader)null, false);
   }

   public ZoneInfoProvider(String var1, ClassLoader var2) throws IOException {
      this(var1, var2, true);
   }

   private ZoneInfoProvider(String var1, ClassLoader var2, boolean var3) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("No resource path provided");
      } else {
         if (!var1.endsWith("/")) {
            var1 = var1 + '/';
         }

         this.iFileDir = null;
         this.iResourcePath = var1;
         if (var2 == null && !var3) {
            var2 = this.getClass().getClassLoader();
         }

         this.iLoader = var2;
         this.iZoneInfoMap = loadZoneInfoMap(this.openResource("ZoneInfoMap"));
         this.iZoneInfoKeys = Collections.unmodifiableSortedSet(new TreeSet(this.iZoneInfoMap.keySet()));
      }
   }

   public DateTimeZone getZone(String var1) {
      if (var1 == null) {
         return null;
      } else {
         Object var2 = this.iZoneInfoMap.get(var1);
         if (var2 == null) {
            return null;
         } else if (var2 instanceof SoftReference) {
            SoftReference var3 = (SoftReference)var2;
            DateTimeZone var4 = (DateTimeZone)var3.get();
            return var4 != null ? var4 : this.loadZoneData(var1);
         } else {
            return var1.equals(var2) ? this.loadZoneData(var1) : this.getZone((String)var2);
         }
      }
   }

   public Set getAvailableIDs() {
      return this.iZoneInfoKeys;
   }

   protected void uncaughtException(Exception var1) {
      var1.printStackTrace();
   }

   private InputStream openResource(String var1) throws IOException {
      Object var2;
      if (this.iFileDir != null) {
         var2 = new FileInputStream(new File(this.iFileDir, var1));
      } else {
         final String var3 = this.iResourcePath.concat(var1);
         var2 = (InputStream)AccessController.doPrivileged(new PrivilegedAction() {
            public InputStream run() {
               return ZoneInfoProvider.this.iLoader != null ? ZoneInfoProvider.this.iLoader.getResourceAsStream(var3) : ClassLoader.getSystemResourceAsStream(var3);
            }
         });
         if (var2 == null) {
            StringBuilder var4 = (new StringBuilder(40)).append("Resource not found: \"").append(var3).append("\" ClassLoader: ").append(this.iLoader != null ? this.iLoader.toString() : "system");
            throw new IOException(var4.toString());
         }
      }

      return (InputStream)var2;
   }

   private DateTimeZone loadZoneData(String var1) {
      InputStream var2 = null;

      DateTimeZone var4;
      try {
         var2 = this.openResource(var1);
         DateTimeZone var3 = DateTimeZoneBuilder.readFrom(var2, var1);
         this.iZoneInfoMap.put(var1, new SoftReference(var3));
         var4 = var3;
         return var4;
      } catch (IOException var14) {
         this.uncaughtException(var14);
         this.iZoneInfoMap.remove(var1);
         var4 = null;
      } finally {
         try {
            if (var2 != null) {
               var2.close();
            }
         } catch (IOException var13) {
         }

      }

      return var4;
   }

   private static Map loadZoneInfoMap(InputStream var0) throws IOException {
      ConcurrentHashMap var1 = new ConcurrentHashMap();
      DataInputStream var2 = new DataInputStream(var0);

      try {
         readZoneInfoMap(var2, var1);
      } finally {
         try {
            var2.close();
         } catch (IOException var9) {
         }

      }

      var1.put("UTC", new SoftReference(DateTimeZone.UTC));
      return var1;
   }

   private static void readZoneInfoMap(DataInputStream var0, Map var1) throws IOException {
      int var2 = var0.readUnsignedShort();
      String[] var3 = new String[var2];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4] = var0.readUTF().intern();
      }

      var2 = var0.readUnsignedShort();

      for(int var8 = 0; var8 < var2; ++var8) {
         try {
            var1.put(var3[var0.readUnsignedShort()], var3[var0.readUnsignedShort()]);
         } catch (ArrayIndexOutOfBoundsException var6) {
            throw new IOException("Corrupt zone info map");
         }
      }

   }
}
