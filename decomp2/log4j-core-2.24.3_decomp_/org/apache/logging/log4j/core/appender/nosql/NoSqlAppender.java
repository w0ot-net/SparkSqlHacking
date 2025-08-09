package org.apache.logging.log4j.core.appender.nosql;

import java.io.Serializable;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.db.AbstractDatabaseAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.KeyValuePair;

@Plugin(
   name = "NoSql",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class NoSqlAppender extends AbstractDatabaseAppender {
   private final String description;

   /** @deprecated */
   @Deprecated
   public static NoSqlAppender createAppender(final String name, final String ignore, final Filter filter, final String bufferSize, final NoSqlProvider provider) {
      if (provider == null) {
         LOGGER.error("NoSQL provider not specified for appender [{}].", name);
         return null;
      } else {
         int bufferSizeInt = AbstractAppender.parseInt(bufferSize, 0);
         boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
         String managerName = "noSqlManager{ description=" + name + ", bufferSize=" + bufferSizeInt + ", provider=" + provider + " }";
         NoSqlDatabaseManager<?> manager = NoSqlDatabaseManager.getNoSqlDatabaseManager(managerName, bufferSizeInt, provider, (KeyValuePair[])null, (Configuration)null);
         return manager == null ? null : new NoSqlAppender(name, filter, (Layout)null, ignoreExceptions, (Property[])null, manager);
      }
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private NoSqlAppender(final String name, final Filter filter, final Layout layout, final boolean ignoreExceptions, final Property[] properties, final NoSqlDatabaseManager manager) {
      super(name, filter, layout, ignoreExceptions, properties, manager);
      this.description = this.getName() + "{ manager=" + this.getManager() + " }";
   }

   public String toString() {
      return this.description;
   }

   public static class Builder extends AbstractAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute("bufferSize")
      private int bufferSize;
      @PluginElement("NoSqlProvider")
      private NoSqlProvider provider;
      @PluginElement("AdditionalField")
      private KeyValuePair[] additionalFields;

      public NoSqlAppender build() {
         String name = this.getName();
         if (this.provider == null) {
            NoSqlAppender.LOGGER.error("NoSQL provider not specified for appender [{}].", name);
            return null;
         } else {
            String managerName = "noSqlManager{ description=" + name + ", bufferSize=" + this.bufferSize + ", provider=" + this.provider + " }";
            NoSqlDatabaseManager<?> manager = NoSqlDatabaseManager.getNoSqlDatabaseManager(managerName, this.bufferSize, this.provider, this.additionalFields, this.getConfiguration());
            return manager == null ? null : new NoSqlAppender(name, this.getFilter(), this.getLayout(), this.isIgnoreExceptions(), this.getPropertyArray(), manager);
         }
      }

      public Builder setBufferSize(final int bufferSize) {
         this.bufferSize = bufferSize;
         return (Builder)this.asBuilder();
      }

      public Builder setProvider(final NoSqlProvider provider) {
         this.provider = provider;
         return (Builder)this.asBuilder();
      }
   }
}
