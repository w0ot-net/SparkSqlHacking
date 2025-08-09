package org.apache.ivy.core.pack;

import java.util.HashMap;
import java.util.Map;

public class PackingRegistry {
   private Map packings = new HashMap();

   public PackingRegistry() {
      this.register(new ZipPacking());
      this.register(new Pack200Packing());
      this.register(new OsgiBundlePacking());
   }

   public void register(ArchivePacking packing) {
      for(String name : packing.getNames()) {
         this.packings.put(name, packing);
      }

   }

   public ArchivePacking get(String type) {
      return (ArchivePacking)this.packings.get(type);
   }
}
