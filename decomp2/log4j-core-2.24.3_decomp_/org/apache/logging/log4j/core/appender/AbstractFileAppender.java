package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.net.Advertiser;

public abstract class AbstractFileAppender extends AbstractOutputStreamAppender {
   private final String fileName;
   private final Advertiser advertiser;
   private final Object advertisement;

   private AbstractFileAppender(final String name, final Layout layout, final Filter filter, final OutputStreamManager manager, final String filename, final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser, final Property[] properties) {
      super(name, layout, filter, ignoreExceptions, immediateFlush, properties, manager);
      if (advertiser != null) {
         Map<String, String> configuration = new HashMap(layout.getContentFormat());
         configuration.putAll(manager.getContentFormat());
         configuration.put("contentType", layout.getContentType());
         configuration.put("name", name);
         this.advertisement = advertiser.advertise(configuration);
      } else {
         this.advertisement = null;
      }

      this.fileName = filename;
      this.advertiser = advertiser;
   }

   public String getFileName() {
      return this.fileName;
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

   public abstract static class Builder extends AbstractOutputStreamAppender.Builder {
      @PluginBuilderAttribute
      @Required
      private String fileName;
      @PluginBuilderAttribute
      private boolean append = true;
      @PluginBuilderAttribute
      private boolean locking;
      @PluginBuilderAttribute
      private boolean advertise;
      @PluginBuilderAttribute
      private String advertiseUri;
      @PluginBuilderAttribute
      private boolean createOnDemand;
      @PluginBuilderAttribute
      private String filePermissions;
      @PluginBuilderAttribute
      private String fileOwner;
      @PluginBuilderAttribute
      private String fileGroup;

      public String getAdvertiseUri() {
         return this.advertiseUri;
      }

      public String getFileName() {
         return this.fileName;
      }

      public boolean isAdvertise() {
         return this.advertise;
      }

      public boolean isAppend() {
         return this.append;
      }

      public boolean isCreateOnDemand() {
         return this.createOnDemand;
      }

      public boolean isLocking() {
         return this.locking;
      }

      public String getFilePermissions() {
         return this.filePermissions;
      }

      public String getFileOwner() {
         return this.fileOwner;
      }

      public String getFileGroup() {
         return this.fileGroup;
      }

      public Builder withAdvertise(final boolean advertise) {
         this.advertise = advertise;
         return (Builder)this.asBuilder();
      }

      public Builder withAdvertiseUri(final String advertiseUri) {
         this.advertiseUri = advertiseUri;
         return (Builder)this.asBuilder();
      }

      public Builder withAppend(final boolean append) {
         this.append = append;
         return (Builder)this.asBuilder();
      }

      public Builder withFileName(final String fileName) {
         this.fileName = fileName;
         return (Builder)this.asBuilder();
      }

      public Builder withCreateOnDemand(final boolean createOnDemand) {
         this.createOnDemand = createOnDemand;
         return (Builder)this.asBuilder();
      }

      public Builder withLocking(final boolean locking) {
         this.locking = locking;
         return (Builder)this.asBuilder();
      }

      public Builder withFilePermissions(final String filePermissions) {
         this.filePermissions = filePermissions;
         return (Builder)this.asBuilder();
      }

      public Builder withFileOwner(final String fileOwner) {
         this.fileOwner = fileOwner;
         return (Builder)this.asBuilder();
      }

      public Builder withFileGroup(final String fileGroup) {
         this.fileGroup = fileGroup;
         return (Builder)this.asBuilder();
      }
   }
}
