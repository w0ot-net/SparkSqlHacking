package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.lookup.MainMapLookup;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class MainMapResolver implements EventResolver {
   private static final MainMapLookup MAIN_MAP_LOOKUP = new MainMapLookup();
   private final String key;

   static String getName() {
      return "main";
   }

   MainMapResolver(final TemplateResolverConfig config) {
      String key = config.getString("key");
      Integer index = config.getInteger("index");
      if (key != null && index != null) {
         throw new IllegalArgumentException("provided both key and index: " + config);
      } else if (key == null && index == null) {
         throw new IllegalArgumentException("either key or index must be provided: " + config);
      } else {
         this.key = index != null ? String.valueOf(index) : key;
      }
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      String value = MAIN_MAP_LOOKUP.lookup(this.key);
      jsonWriter.writeString((CharSequence)value);
   }
}
