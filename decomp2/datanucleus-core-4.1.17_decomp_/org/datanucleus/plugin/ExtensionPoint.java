package org.datanucleus.plugin;

import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

public class ExtensionPoint {
   private final String id;
   private final String name;
   private final URL schema;
   private final Bundle plugin;
   private Extension[] extensions;

   public ExtensionPoint(String id, String name, URL schema, Bundle plugin) {
      this.id = id;
      this.name = name;
      this.schema = schema;
      this.plugin = plugin;
      this.extensions = new Extension[0];
   }

   public Extension[] getExtensions() {
      return this.extensions;
   }

   public void sortExtensions(Comparator comp) {
      Arrays.sort(this.extensions, comp);
   }

   public void addExtension(Extension extension) {
      Extension[] exs = new Extension[this.extensions.length + 1];
      System.arraycopy(this.extensions, 0, exs, 0, this.extensions.length);
      exs[this.extensions.length] = extension;
      this.extensions = exs;
   }

   public String getId() {
      return this.id;
   }

   public String getUniqueId() {
      return this.plugin.getSymbolicName() + "." + this.id;
   }

   public String getName() {
      return this.name;
   }

   public URL getSchema() {
      return this.schema;
   }

   public Bundle getBundle() {
      return this.plugin;
   }
}
