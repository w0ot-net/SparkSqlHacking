package org.apache.logging.log4j.core.appender.db;

import java.util.Date;
import java.util.Objects;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.spi.ThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextStack;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "ColumnMapping",
   category = "Core",
   printObject = true
)
public class ColumnMapping {
   public static final ColumnMapping[] EMPTY_ARRAY = new ColumnMapping[0];
   private static final Logger LOGGER = StatusLogger.getLogger();
   private final StringLayout layout;
   private final String literalValue;
   private final String name;
   private final String nameKey;
   private final String parameter;
   private final String source;
   private final Class type;

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   public static String toKey(final String name) {
      return Strings.toRootUpperCase(name);
   }

   private ColumnMapping(final String name, final String source, final StringLayout layout, final String literalValue, final String parameter, final Class type) {
      this.name = (String)Objects.requireNonNull(name);
      this.nameKey = toKey(name);
      this.source = source;
      this.layout = layout;
      this.literalValue = literalValue;
      this.parameter = parameter;
      this.type = type;
   }

   public StringLayout getLayout() {
      return this.layout;
   }

   public String getLiteralValue() {
      return this.literalValue;
   }

   public String getName() {
      return this.name;
   }

   public String getNameKey() {
      return this.nameKey;
   }

   public String getParameter() {
      return this.parameter;
   }

   public String getSource() {
      return this.source;
   }

   public Class getType() {
      return this.type;
   }

   public String toString() {
      return "ColumnMapping [name=" + this.name + ", source=" + this.source + ", literalValue=" + this.literalValue + ", parameter=" + this.parameter + ", type=" + this.type + ", layout=" + this.layout + "]";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ColumnMapping that = (ColumnMapping)o;
         return Objects.equals(this.layout, that.layout) && Objects.equals(this.literalValue, that.literalValue) && this.name.equals(that.name) && Objects.equals(this.parameter, that.parameter) && Objects.equals(this.source, that.source) && Objects.equals(this.type, that.type);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.layout, this.literalValue, this.name, this.parameter, this.source, this.type});
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginConfiguration
      private Configuration configuration;
      @PluginElement("Layout")
      private StringLayout layout;
      @PluginBuilderAttribute
      private String literal;
      @PluginBuilderAttribute
      @Required(
         message = "No column name provided"
      )
      private String name;
      @PluginBuilderAttribute
      private String parameter;
      @PluginBuilderAttribute
      private String pattern;
      @PluginBuilderAttribute
      private String source;
      /** @deprecated */
      @PluginBuilderAttribute
      @Deprecated
      private Class type;
      @PluginBuilderAttribute
      @Required(
         message = "No conversion type provided"
      )
      private Class columnType = String.class;

      public ColumnMapping build() {
         if (this.pattern != null) {
            this.layout = PatternLayout.newBuilder().withPattern(this.pattern).withConfiguration(this.configuration).withAlwaysWriteExceptions(false).build();
         }

         Class<?> columnType = this.type != null ? this.type : this.columnType;
         if (this.layout != null && this.literal != null && !Date.class.isAssignableFrom(columnType) && !ReadOnlyStringMap.class.isAssignableFrom(columnType) && !ThreadContextMap.class.isAssignableFrom(columnType) && !ThreadContextStack.class.isAssignableFrom(columnType)) {
            ColumnMapping.LOGGER.error("No 'layout' or 'literal' value specified and type ({}) is not compatible with ThreadContextMap, ThreadContextStack, or java.util.Date for the mapping", columnType, this);
            return null;
         } else if (this.literal != null && this.parameter != null) {
            ColumnMapping.LOGGER.error("Only one of 'literal' or 'parameter' can be set on the column mapping {}", this);
            return null;
         } else {
            return new ColumnMapping(this.name, this.source, this.layout, this.literal, this.parameter, columnType);
         }
      }

      public Builder setConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public Builder setLayout(final StringLayout layout) {
         this.layout = layout;
         return this;
      }

      public Builder setLiteral(final String literal) {
         this.literal = literal;
         return this;
      }

      public Builder setName(final String name) {
         this.name = name;
         return this;
      }

      public Builder setParameter(final String parameter) {
         this.parameter = parameter;
         return this;
      }

      public Builder setPattern(final String pattern) {
         this.pattern = pattern;
         return this;
      }

      public Builder setSource(final String source) {
         this.source = source;
         return this;
      }

      public Builder setColumnType(final Class columnType) {
         this.columnType = columnType;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setType(final Class type) {
         this.type = type;
         return this;
      }

      public String toString() {
         return "Builder [name=" + this.name + ", source=" + this.source + ", literal=" + this.literal + ", parameter=" + this.parameter + ", pattern=" + this.pattern + ", columnType=" + this.columnType + ", layout=" + this.layout + "]";
      }
   }
}
