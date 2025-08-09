package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

@FunctionalInterface
public interface TemplateResolver {
   default boolean isFlattening() {
      return false;
   }

   default boolean isResolvable() {
      return true;
   }

   default boolean isResolvable(final Object value) {
      return true;
   }

   void resolve(Object value, JsonWriter jsonWriter);

   default void resolve(final Object value, final JsonWriter jsonWriter, final boolean succeedingEntry) {
      this.resolve(value, jsonWriter);
   }
}
