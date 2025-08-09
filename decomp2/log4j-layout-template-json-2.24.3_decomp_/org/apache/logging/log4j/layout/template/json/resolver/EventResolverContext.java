package org.apache.logging.log4j.layout.template.json.resolver;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.layout.template.json.JsonTemplateLayout;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.layout.template.json.util.RecyclerFactory;
import org.apache.logging.log4j.util.Strings;

public final class EventResolverContext implements TemplateResolverContext {
   private final Configuration configuration;
   private final Map resolverFactoryByName;
   private final List resolverInterceptors;
   private final EventResolverStringSubstitutor substitutor;
   private final Charset charset;
   private final JsonWriter jsonWriter;
   private final RecyclerFactory recyclerFactory;
   private final int maxStringByteCount;
   private final String truncatedStringSuffix;
   private final boolean locationInfoEnabled;
   private final boolean stackTraceEnabled;
   private final String stackTraceElementTemplate;
   private final String eventTemplateRootObjectKey;
   private final JsonTemplateLayout.EventTemplateAdditionalField[] eventTemplateAdditionalFields;

   private EventResolverContext(final Builder builder) {
      this.configuration = builder.configuration;
      this.resolverFactoryByName = builder.resolverFactoryByName;
      this.resolverInterceptors = builder.resolverInterceptors;
      this.substitutor = builder.substitutor;
      this.charset = builder.charset;
      this.jsonWriter = builder.jsonWriter;
      this.recyclerFactory = builder.recyclerFactory;
      this.maxStringByteCount = builder.maxStringByteCount;
      this.truncatedStringSuffix = builder.truncatedStringSuffix;
      this.locationInfoEnabled = builder.locationInfoEnabled;
      this.stackTraceEnabled = builder.stackTraceEnabled;
      this.stackTraceElementTemplate = builder.stackTraceElementTemplate;
      this.eventTemplateRootObjectKey = builder.eventTemplateRootObjectKey;
      this.eventTemplateAdditionalFields = builder.eventTemplateAdditionalFields;
   }

   public final Class getContextClass() {
      return EventResolverContext.class;
   }

   public Configuration getConfiguration() {
      return this.configuration;
   }

   public Map getResolverFactoryByName() {
      return this.resolverFactoryByName;
   }

   public List getResolverInterceptors() {
      return this.resolverInterceptors;
   }

   public EventResolverStringSubstitutor getSubstitutor() {
      return this.substitutor;
   }

   public Charset getCharset() {
      return this.charset;
   }

   public JsonWriter getJsonWriter() {
      return this.jsonWriter;
   }

   public RecyclerFactory getRecyclerFactory() {
      return this.recyclerFactory;
   }

   public int getMaxStringByteCount() {
      return this.maxStringByteCount;
   }

   public String getTruncatedStringSuffix() {
      return this.truncatedStringSuffix;
   }

   public boolean isLocationInfoEnabled() {
      return this.locationInfoEnabled;
   }

   public boolean isStackTraceEnabled() {
      return this.stackTraceEnabled;
   }

   public String getStackTraceElementTemplate() {
      return this.stackTraceElementTemplate;
   }

   public String getEventTemplateRootObjectKey() {
      return this.eventTemplateRootObjectKey;
   }

   public JsonTemplateLayout.EventTemplateAdditionalField[] getEventTemplateAdditionalFields() {
      return this.eventTemplateAdditionalFields;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public static class Builder {
      private Configuration configuration;
      private Map resolverFactoryByName;
      private List resolverInterceptors;
      private EventResolverStringSubstitutor substitutor;
      private Charset charset;
      private JsonWriter jsonWriter;
      private RecyclerFactory recyclerFactory;
      private int maxStringByteCount;
      private String truncatedStringSuffix;
      private boolean locationInfoEnabled;
      private boolean stackTraceEnabled;
      private String stackTraceElementTemplate;
      private String eventTemplateRootObjectKey;
      private JsonTemplateLayout.EventTemplateAdditionalField[] eventTemplateAdditionalFields;

      private Builder() {
      }

      public Builder setConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public Builder setResolverFactoryByName(final Map resolverFactoryByName) {
         this.resolverFactoryByName = resolverFactoryByName;
         return this;
      }

      public Builder setResolverInterceptors(final List resolverInterceptors) {
         this.resolverInterceptors = resolverInterceptors;
         return this;
      }

      public Builder setSubstitutor(final EventResolverStringSubstitutor substitutor) {
         this.substitutor = substitutor;
         return this;
      }

      public Builder setCharset(final Charset charset) {
         this.charset = charset;
         return this;
      }

      public Builder setJsonWriter(final JsonWriter jsonWriter) {
         this.jsonWriter = jsonWriter;
         return this;
      }

      public Builder setRecyclerFactory(final RecyclerFactory recyclerFactory) {
         this.recyclerFactory = recyclerFactory;
         return this;
      }

      public Builder setMaxStringByteCount(final int maxStringByteCount) {
         this.maxStringByteCount = maxStringByteCount;
         return this;
      }

      public Builder setTruncatedStringSuffix(final String truncatedStringSuffix) {
         this.truncatedStringSuffix = truncatedStringSuffix;
         return this;
      }

      public Builder setLocationInfoEnabled(final boolean locationInfoEnabled) {
         this.locationInfoEnabled = locationInfoEnabled;
         return this;
      }

      public Builder setStackTraceEnabled(final boolean stackTraceEnabled) {
         this.stackTraceEnabled = stackTraceEnabled;
         return this;
      }

      public Builder setStackTraceElementTemplate(final String stackTraceElementTemplate) {
         this.stackTraceElementTemplate = stackTraceElementTemplate;
         return this;
      }

      public Builder setEventTemplateRootObjectKey(final String eventTemplateRootObjectKey) {
         this.eventTemplateRootObjectKey = eventTemplateRootObjectKey;
         return this;
      }

      public Builder setEventTemplateAdditionalFields(final JsonTemplateLayout.EventTemplateAdditionalField[] eventTemplateAdditionalFields) {
         this.eventTemplateAdditionalFields = eventTemplateAdditionalFields;
         return this;
      }

      public EventResolverContext build() {
         this.validate();
         return new EventResolverContext(this);
      }

      private void validate() {
         Objects.requireNonNull(this.configuration, "configuration");
         Objects.requireNonNull(this.resolverFactoryByName, "resolverFactoryByName");
         if (this.resolverFactoryByName.isEmpty()) {
            throw new IllegalArgumentException("empty resolverFactoryByName");
         } else {
            Objects.requireNonNull(this.resolverInterceptors, "resolverInterceptors");
            Objects.requireNonNull(this.substitutor, "substitutor");
            Objects.requireNonNull(this.charset, "charset");
            Objects.requireNonNull(this.jsonWriter, "jsonWriter");
            Objects.requireNonNull(this.recyclerFactory, "recyclerFactory");
            if (this.maxStringByteCount <= 0) {
               throw new IllegalArgumentException("was expecting maxStringByteCount > 0: " + this.maxStringByteCount);
            } else {
               Objects.requireNonNull(this.truncatedStringSuffix, "truncatedStringSuffix");
               if (this.stackTraceEnabled && Strings.isBlank(this.stackTraceElementTemplate)) {
                  throw new IllegalArgumentException("stackTraceElementTemplate cannot be blank when stackTraceEnabled is set to true");
               } else {
                  Objects.requireNonNull(this.stackTraceElementTemplate, "stackTraceElementTemplate");
                  Objects.requireNonNull(this.eventTemplateAdditionalFields, "eventTemplateAdditionalFields");
               }
            }
         }
      }
   }
}
