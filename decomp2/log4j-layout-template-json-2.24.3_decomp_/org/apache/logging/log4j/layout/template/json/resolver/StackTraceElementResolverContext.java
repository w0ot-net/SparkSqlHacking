package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

final class StackTraceElementResolverContext implements TemplateResolverContext {
   private final Map resolverFactoryByName;
   private final StackTraceElementResolverStringSubstitutor substitutor;
   private final JsonWriter jsonWriter;

   private StackTraceElementResolverContext(final Builder builder) {
      this.resolverFactoryByName = builder.resolverFactoryByName;
      this.substitutor = builder.substitutor;
      this.jsonWriter = builder.jsonWriter;
   }

   public final Class getContextClass() {
      return StackTraceElementResolverContext.class;
   }

   public Map getResolverFactoryByName() {
      return this.resolverFactoryByName;
   }

   public List getResolverInterceptors() {
      return Collections.emptyList();
   }

   public StackTraceElementResolverStringSubstitutor getSubstitutor() {
      return this.substitutor;
   }

   public JsonWriter getJsonWriter() {
      return this.jsonWriter;
   }

   static Builder newBuilder() {
      return new Builder();
   }

   static final class Builder {
      private Map resolverFactoryByName;
      private StackTraceElementResolverStringSubstitutor substitutor;
      private JsonWriter jsonWriter;

      private Builder() {
      }

      Builder setResolverFactoryByName(final Map resolverFactoryByName) {
         this.resolverFactoryByName = resolverFactoryByName;
         return this;
      }

      Builder setSubstitutor(final StackTraceElementResolverStringSubstitutor substitutor) {
         this.substitutor = substitutor;
         return this;
      }

      Builder setJsonWriter(final JsonWriter jsonWriter) {
         this.jsonWriter = jsonWriter;
         return this;
      }

      StackTraceElementResolverContext build() {
         this.validate();
         return new StackTraceElementResolverContext(this);
      }

      private void validate() {
         Objects.requireNonNull(this.resolverFactoryByName, "resolverFactoryByName");
         if (this.resolverFactoryByName.isEmpty()) {
            throw new IllegalArgumentException("empty resolverFactoryByName");
         } else {
            Objects.requireNonNull(this.substitutor, "substitutor");
            Objects.requireNonNull(this.jsonWriter, "jsonWriter");
         }
      }
   }
}
