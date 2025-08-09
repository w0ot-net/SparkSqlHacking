package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.net.Advertiser;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.Integers;

@Plugin(
   name = "MemoryMappedFile",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class MemoryMappedFileAppender extends AbstractOutputStreamAppender {
   private static final int BIT_POSITION_1GB = 30;
   private static final int MAX_REGION_LENGTH = 1073741824;
   private static final int MIN_REGION_LENGTH = 256;
   private final String fileName;
   private Object advertisement;
   private final Advertiser advertiser;

   private MemoryMappedFileAppender(final String name, final Layout layout, final Filter filter, final MemoryMappedFileManager manager, final String filename, final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser, final Property[] properties) {
      super(name, layout, filter, ignoreExceptions, immediateFlush, properties, manager);
      if (advertiser != null) {
         Map<String, String> configuration = new HashMap(layout.getContentFormat());
         configuration.putAll(manager.getContentFormat());
         configuration.put("contentType", layout.getContentType());
         configuration.put("name", name);
         this.advertisement = advertiser.advertise(configuration);
      }

      this.fileName = filename;
      this.advertiser = advertiser;
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      super.stop(timeout, timeUnit, false);
      if (this.advertiser != null) {
         this.advertiser.unadvertise(this.advertisement);
      }

      this.setStopped();
      return true;
   }

   public String getFileName() {
      return this.fileName;
   }

   public int getRegionLength() {
      return ((MemoryMappedFileManager)this.getManager()).getRegionLength();
   }

   /** @deprecated */
   @Deprecated
   public static MemoryMappedFileAppender createAppender(final String fileName, final String append, final String name, final String immediateFlush, final String regionLengthStr, final String ignore, final Layout layout, final Filter filter, final String advertise, final String advertiseURI, final Configuration config) {
      boolean isAppend = Booleans.parseBoolean(append, true);
      boolean isImmediateFlush = Booleans.parseBoolean(immediateFlush, false);
      boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
      boolean isAdvertise = Boolean.parseBoolean(advertise);
      int regionLength = Integers.parseInt(regionLengthStr, 33554432);
      return ((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)newBuilder().setAdvertise(isAdvertise).setAdvertiseURI(advertiseURI).setAppend(isAppend).setConfiguration(config)).setFileName(fileName).setFilter(filter)).setIgnoreExceptions(ignoreExceptions)).withImmediateFlush(isImmediateFlush)).setLayout(layout)).setName(name)).setRegionLength(regionLength).build();
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private static int determineValidRegionLength(final String name, final int regionLength) {
      if (regionLength > 1073741824) {
         LOGGER.info("MemoryMappedAppender[{}] Reduced region length from {} to max length: {}", name, regionLength, 1073741824);
         return 1073741824;
      } else if (regionLength < 256) {
         LOGGER.info("MemoryMappedAppender[{}] Expanded region length from {} to min length: {}", name, regionLength, 256);
         return 256;
      } else {
         int result = Integers.ceilingNextPowerOfTwo(regionLength);
         if (regionLength != result) {
            LOGGER.info("MemoryMappedAppender[{}] Rounded up region length from {} to next power of two: {}", name, regionLength, result);
         }

         return result;
      }
   }

   public static class Builder extends AbstractOutputStreamAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute("fileName")
      private String fileName;
      @PluginBuilderAttribute("append")
      private boolean append = true;
      @PluginBuilderAttribute("regionLength")
      private int regionLength = 33554432;
      @PluginBuilderAttribute("advertise")
      private boolean advertise;
      @PluginBuilderAttribute("advertiseURI")
      private String advertiseURI;

      public MemoryMappedFileAppender build() {
         String name = this.getName();
         int actualRegionLength = MemoryMappedFileAppender.determineValidRegionLength(name, this.regionLength);
         if (name == null) {
            MemoryMappedFileAppender.LOGGER.error("No name provided for MemoryMappedFileAppender");
            return null;
         } else if (this.fileName == null) {
            MemoryMappedFileAppender.LOGGER.error("No filename provided for MemoryMappedFileAppender with name " + name);
            return null;
         } else {
            Layout<? extends Serializable> layout = this.getOrCreateLayout();
            MemoryMappedFileManager manager = MemoryMappedFileManager.getFileManager(this.fileName, this.append, this.isImmediateFlush(), actualRegionLength, this.advertiseURI, layout);
            return manager == null ? null : new MemoryMappedFileAppender(name, layout, this.getFilter(), manager, this.fileName, this.isIgnoreExceptions(), false, this.advertise ? this.getConfiguration().getAdvertiser() : null, this.getPropertyArray());
         }
      }

      public Builder setFileName(final String fileName) {
         this.fileName = fileName;
         return (Builder)this.asBuilder();
      }

      public Builder setAppend(final boolean append) {
         this.append = append;
         return (Builder)this.asBuilder();
      }

      public Builder setRegionLength(final int regionLength) {
         this.regionLength = regionLength;
         return (Builder)this.asBuilder();
      }

      public Builder setAdvertise(final boolean advertise) {
         this.advertise = advertise;
         return (Builder)this.asBuilder();
      }

      public Builder setAdvertiseURI(final String advertiseURI) {
         this.advertiseURI = advertiseURI;
         return (Builder)this.asBuilder();
      }
   }
}
