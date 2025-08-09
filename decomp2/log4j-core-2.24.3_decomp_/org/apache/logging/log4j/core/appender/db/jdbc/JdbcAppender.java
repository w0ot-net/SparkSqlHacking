package org.apache.logging.log4j.core.appender.db.jdbc;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.db.AbstractDatabaseAppender;
import org.apache.logging.log4j.core.appender.db.ColumnMapping;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.util.Assert;
import org.apache.logging.log4j.core.util.Booleans;

@Plugin(
   name = "JDBC",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class JdbcAppender extends AbstractDatabaseAppender {
   private final String description;

   /** @deprecated */
   @Deprecated
   public static JdbcAppender createAppender(final String name, final String ignore, final Filter filter, final ConnectionSource connectionSource, final String bufferSize, final String tableName, final ColumnConfig[] columnConfigs) {
      Assert.requireNonEmpty(name, "Name cannot be empty");
      Objects.requireNonNull(connectionSource, "ConnectionSource cannot be null");
      Assert.requireNonEmpty(tableName, "Table name cannot be empty");
      Assert.requireNonEmpty(columnConfigs, "ColumnConfigs cannot be empty");
      int bufferSizeInt = AbstractAppender.parseInt(bufferSize, 0);
      boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
      return ((Builder)((Builder)((Builder)newBuilder().setBufferSize(bufferSizeInt).setColumnConfigs(columnConfigs).setConnectionSource(connectionSource).setTableName(tableName).setName(name)).setIgnoreExceptions(ignoreExceptions)).setFilter(filter)).build();
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private JdbcAppender(final String name, final Filter filter, final Layout layout, final boolean ignoreExceptions, final Property[] properties, final JdbcDatabaseManager manager) {
      super(name, filter, layout, ignoreExceptions, properties, manager);
      this.description = this.getName() + "{ manager=" + this.getManager() + " }";
   }

   public String toString() {
      return this.description;
   }

   public static class Builder extends AbstractDatabaseAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginElement("ConnectionSource")
      @Required(
         message = "No ConnectionSource provided"
      )
      private ConnectionSource connectionSource;
      @PluginBuilderAttribute
      private boolean immediateFail;
      @PluginBuilderAttribute
      private int bufferSize;
      @PluginBuilderAttribute
      @Required(
         message = "No table name provided"
      )
      private String tableName;
      @PluginElement("ColumnConfigs")
      private ColumnConfig[] columnConfigs;
      @PluginElement("ColumnMappings")
      private ColumnMapping[] columnMappings;
      @PluginBuilderAttribute
      private boolean truncateStrings = true;
      @PluginBuilderAttribute
      private long reconnectIntervalMillis = 5000L;

      public JdbcAppender build() {
         if (Assert.isEmpty(this.columnConfigs) && Assert.isEmpty(this.columnMappings)) {
            JdbcAppender.LOGGER.error("Cannot create JdbcAppender without any columns.");
            return null;
         } else {
            String managerName = "JdbcManager{name=" + this.getName() + ", bufferSize=" + this.bufferSize + ", tableName=" + this.tableName + ", columnConfigs=" + Arrays.toString(this.columnConfigs) + ", columnMappings=" + Arrays.toString(this.columnMappings) + '}';
            JdbcDatabaseManager manager = JdbcDatabaseManager.getManager(managerName, this.bufferSize, this.getLayout(), this.connectionSource, this.tableName, this.columnConfigs, this.columnMappings, this.immediateFail, this.reconnectIntervalMillis, this.truncateStrings);
            return manager == null ? null : new JdbcAppender(this.getName(), this.getFilter(), this.getLayout(), this.isIgnoreExceptions(), this.getPropertyArray(), manager);
         }
      }

      public long getReconnectIntervalMillis() {
         return this.reconnectIntervalMillis;
      }

      public boolean isImmediateFail() {
         return this.immediateFail;
      }

      public Builder setBufferSize(final int bufferSize) {
         this.bufferSize = bufferSize;
         return (Builder)this.asBuilder();
      }

      public Builder setColumnConfigs(final ColumnConfig... columnConfigs) {
         this.columnConfigs = columnConfigs;
         return (Builder)this.asBuilder();
      }

      public Builder setColumnMappings(final ColumnMapping... columnMappings) {
         this.columnMappings = columnMappings;
         return (Builder)this.asBuilder();
      }

      public Builder setConnectionSource(final ConnectionSource connectionSource) {
         this.connectionSource = connectionSource;
         return (Builder)this.asBuilder();
      }

      public void setImmediateFail(final boolean immediateFail) {
         this.immediateFail = immediateFail;
      }

      public void setReconnectIntervalMillis(final long reconnectIntervalMillis) {
         this.reconnectIntervalMillis = reconnectIntervalMillis;
      }

      public Builder setTableName(final String tableName) {
         this.tableName = tableName;
         return (Builder)this.asBuilder();
      }

      public Builder setTruncateStrings(final boolean truncateStrings) {
         this.truncateStrings = truncateStrings;
         return (Builder)this.asBuilder();
      }
   }
}
