package org.apache.logging.log4j.core.config.plugins.processor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.util.Strings;

public class PluginCache {
   private final Map categories = new TreeMap();

   public Map getAllCategories() {
      return this.categories;
   }

   public Map getCategory(final String category) {
      String key = Strings.toRootLowerCase(category);
      return (Map)this.categories.computeIfAbsent(key, (ignored) -> new TreeMap());
   }

   public void writeCache(final OutputStream os) throws IOException {
      DataOutputStream out = new DataOutputStream(new BufferedOutputStream(os));

      try {
         out.writeInt(this.categories.size());

         for(Map.Entry category : this.categories.entrySet()) {
            out.writeUTF((String)category.getKey());
            Map<String, PluginEntry> m = (Map)category.getValue();
            out.writeInt(m.size());

            for(Map.Entry entry : m.entrySet()) {
               PluginEntry plugin = (PluginEntry)entry.getValue();
               out.writeUTF(plugin.getKey());
               out.writeUTF(plugin.getClassName());
               out.writeUTF(plugin.getName());
               out.writeBoolean(plugin.isPrintable());
               out.writeBoolean(plugin.isDefer());
            }
         }
      } catch (Throwable var10) {
         try {
            out.close();
         } catch (Throwable var9) {
            var10.addSuppressed(var9);
         }

         throw var10;
      }

      out.close();
   }

   public void loadCacheFiles(final Enumeration resources) throws IOException {
      this.categories.clear();

      DataInputStream in;
      for(; resources.hasMoreElements(); in.close()) {
         URL url = (URL)resources.nextElement();
         in = new DataInputStream(new BufferedInputStream(url.openStream()));

         try {
            int count = in.readInt();

            for(int i = 0; i < count; ++i) {
               String category = in.readUTF();
               Map<String, PluginEntry> m = this.getCategory(category);
               int entries = in.readInt();

               for(int j = 0; j < entries; ++j) {
                  String key = in.readUTF();
                  String className = in.readUTF();
                  String name = in.readUTF();
                  boolean printable = in.readBoolean();
                  boolean defer = in.readBoolean();
                  m.computeIfAbsent(key, (k) -> {
                     PluginEntry entry = new PluginEntry();
                     entry.setKey(k);
                     entry.setClassName(className);
                     entry.setName(name);
                     entry.setPrintable(printable);
                     entry.setDefer(defer);
                     entry.setCategory(category);
                     return entry;
                  });
               }
            }
         } catch (Throwable var16) {
            try {
               in.close();
            } catch (Throwable var15) {
               var16.addSuppressed(var15);
            }

            throw var16;
         }
      }

   }

   public int size() {
      return this.categories.size();
   }
}
