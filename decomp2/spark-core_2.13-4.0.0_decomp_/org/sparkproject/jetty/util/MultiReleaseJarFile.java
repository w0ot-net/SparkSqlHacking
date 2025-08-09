package org.sparkproject.jetty.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Stream;

public class MultiReleaseJarFile implements Closeable {
   private static final String META_INF_VERSIONS = "META-INF/versions/";
   private final JarFile jarFile;
   private final int platform;
   private final boolean multiRelease;
   private final Map entries;

   public MultiReleaseJarFile(File file) throws IOException {
      this(file, JavaVersion.VERSION.getPlatform(), false);
   }

   public MultiReleaseJarFile(File file, int javaPlatform, boolean includeDirectories) throws IOException {
      if (file != null && file.exists() && file.canRead() && !file.isDirectory()) {
         this.jarFile = new JarFile(file, true, 1);
         this.platform = javaPlatform;
         Manifest manifest = this.jarFile.getManifest();
         if (manifest == null) {
            this.multiRelease = false;
         } else {
            this.multiRelease = Boolean.parseBoolean(String.valueOf(manifest.getMainAttributes().getValue("Multi-Release")));
         }

         Map<String, VersionedJarEntry> map = new TreeMap();
         this.jarFile.stream().map((x$0) -> new VersionedJarEntry(x$0)).filter((ex) -> (includeDirectories || !ex.isDirectory()) && ex.isApplicable()).forEach((ex) -> map.compute(ex.name, (k, v) -> v != null && !v.isReplacedBy(ex) ? v : ex));
         Iterator<Map.Entry<String, VersionedJarEntry>> i = map.entrySet().iterator();

         while(i.hasNext()) {
            Map.Entry<String, VersionedJarEntry> e = (Map.Entry)i.next();
            VersionedJarEntry entry = (VersionedJarEntry)e.getValue();
            if (entry.inner) {
               VersionedJarEntry outer = entry.outer == null ? null : (VersionedJarEntry)map.get(entry.outer);
               if (outer == null || outer.version != entry.version) {
                  i.remove();
               }
            }
         }

         this.entries = Collections.unmodifiableMap(map);
      } else {
         throw new IllegalArgumentException("bad jar file: " + String.valueOf(file));
      }
   }

   public boolean isMultiRelease() {
      return this.multiRelease;
   }

   public int getVersion() {
      return this.platform;
   }

   public Stream stream() {
      return this.entries.values().stream();
   }

   public VersionedJarEntry getEntry(String name) {
      return (VersionedJarEntry)this.entries.get(name);
   }

   public void close() throws IOException {
      if (this.jarFile != null) {
         this.jarFile.close();
      }

   }

   public String toString() {
      return String.format("%s[%b,%d]", this.jarFile.getName(), this.isMultiRelease(), this.getVersion());
   }

   public class VersionedJarEntry {
      final JarEntry entry;
      final String name;
      final int version;
      final boolean inner;
      final String outer;

      VersionedJarEntry(JarEntry entry) {
         int v = 0;
         String name = entry.getName();
         if (name.startsWith("META-INF/versions/")) {
            v = -1;
            int index = name.indexOf(47, "META-INF/versions/".length());
            if (index > "META-INF/versions/".length() && index < name.length()) {
               try {
                  v = TypeUtil.parseInt((String)name, "META-INF/versions/".length(), index - "META-INF/versions/".length(), 10);
                  name = name.substring(index + 1);
               } catch (NumberFormatException x) {
                  throw new RuntimeException("illegal version in " + String.valueOf(MultiReleaseJarFile.this.jarFile), x);
               }
            }
         }

         this.entry = entry;
         this.name = name;
         this.version = v;
         this.inner = name.contains("$") && name.toLowerCase(Locale.ENGLISH).endsWith(".class");
         this.outer = this.inner ? name.substring(0, name.indexOf(36)) + ".class" : null;
      }

      public String getName() {
         return this.name;
      }

      public String getNameInJar() {
         return this.entry.getName();
      }

      public int getVersion() {
         return this.version;
      }

      public boolean isVersioned() {
         return this.version > 0;
      }

      public boolean isDirectory() {
         return this.entry.isDirectory();
      }

      public InputStream getInputStream() throws IOException {
         return MultiReleaseJarFile.this.jarFile.getInputStream(this.entry);
      }

      boolean isApplicable() {
         if (!MultiReleaseJarFile.this.multiRelease) {
            return this.version == 0;
         } else {
            return (this.version == 0 || this.version == MultiReleaseJarFile.this.platform) && this.name.length() > 0;
         }
      }

      boolean isReplacedBy(VersionedJarEntry entry) {
         if (this.isDirectory()) {
            return entry.version == 0;
         } else {
            return this.name.equals(entry.name) && entry.version > this.version;
         }
      }

      public String toString() {
         return String.format("%s->%s[%d]", this.name, this.entry.getName(), this.version);
      }
   }
}
