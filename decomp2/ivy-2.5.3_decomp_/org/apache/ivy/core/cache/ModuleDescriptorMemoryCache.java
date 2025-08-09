package org.apache.ivy.core.cache;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.util.Message;

class ModuleDescriptorMemoryCache {
   private final int maxSize;
   private final LinkedHashMap valueMap;

   public ModuleDescriptorMemoryCache(int size) {
      this.maxSize = size;
      this.valueMap = new LinkedHashMap(size);
   }

   public ModuleDescriptor get(File ivyFile, ParserSettings ivySettings, boolean validated, ModuleDescriptorProvider mdProvider) throws ParseException, IOException {
      ModuleDescriptor descriptor = this.getFromCache(ivyFile, ivySettings, validated);
      if (descriptor == null) {
         descriptor = this.getStale(ivyFile, ivySettings, validated, mdProvider);
      }

      return descriptor;
   }

   public ModuleDescriptor getStale(File ivyFile, ParserSettings ivySettings, boolean validated, ModuleDescriptorProvider mdProvider) throws ParseException, IOException {
      ParserSettingsMonitor settingsMonitor = new ParserSettingsMonitor(ivySettings);
      ModuleDescriptor descriptor = mdProvider.provideModule(settingsMonitor.getMonitoredSettings(), ivyFile, validated);
      this.putInCache(ivyFile, settingsMonitor, validated, descriptor);
      return descriptor;
   }

   ModuleDescriptor getFromCache(File ivyFile, ParserSettings ivySettings, boolean validated) {
      if (this.maxSize <= 0) {
         return null;
      } else {
         synchronized(this.valueMap) {
            CacheEntry entry = (CacheEntry)this.valueMap.get(ivyFile);
            if (entry != null) {
               if (entry.isStale(ivyFile, validated, ivySettings)) {
                  Message.debug("Entry is found in the ModuleDescriptorCache but entry should be reevaluated : " + ivyFile);
                  this.valueMap.remove(ivyFile);
                  return null;
               } else {
                  this.valueMap.remove(ivyFile);
                  this.valueMap.put(ivyFile, entry);
                  Message.debug("Entry is found in the ModuleDescriptorCache : " + ivyFile);
                  return entry.md;
               }
            } else {
               Message.debug("No entry is found in the ModuleDescriptorCache : " + ivyFile);
               return null;
            }
         }
      }
   }

   void putInCache(File url, ParserSettingsMonitor ivySettingsMonitor, boolean validated, ModuleDescriptor descriptor) {
      if (this.maxSize > 0) {
         synchronized(this.valueMap) {
            if (this.valueMap.size() >= this.maxSize) {
               Message.debug("ModuleDescriptorCache is full, remove one entry");
               Iterator<CacheEntry> it = this.valueMap.values().iterator();
               it.next();
               it.remove();
            }

            this.valueMap.put(url, new CacheEntry(descriptor, validated, ivySettingsMonitor));
         }
      }
   }

   private static class CacheEntry {
      private final ModuleDescriptor md;
      private final boolean validated;
      private final ParserSettingsMonitor parserSettingsMonitor;

      CacheEntry(ModuleDescriptor md, boolean validated, ParserSettingsMonitor parserSettingsMonitor) {
         this.md = md;
         this.validated = validated;
         this.parserSettingsMonitor = parserSettingsMonitor;
      }

      boolean isStale(File ivyFile, boolean validated, ParserSettings newParserSettings) {
         return validated && !this.validated || this.md.getLastModified() != ivyFile.lastModified() || this.parserSettingsMonitor.hasChanged(newParserSettings);
      }
   }
}
