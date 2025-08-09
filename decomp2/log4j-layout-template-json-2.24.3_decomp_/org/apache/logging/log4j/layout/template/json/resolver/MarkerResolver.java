package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class MarkerResolver implements EventResolver {
   private static final TemplateResolver NAME_RESOLVER = (logEvent, jsonWriter) -> {
      Marker marker = logEvent.getMarker();
      if (marker == null) {
         jsonWriter.writeNull();
      } else {
         jsonWriter.writeString((CharSequence)marker.getName());
      }

   };
   private static final TemplateResolver PARENTS_RESOLVER = (logEvent, jsonWriter) -> {
      Marker marker = logEvent.getMarker();
      if (marker != null && marker.hasParents()) {
         Marker[] parents = marker.getParents();
         jsonWriter.writeArrayStart();

         for(int parentIndex = 0; parentIndex < parents.length; ++parentIndex) {
            if (parentIndex > 0) {
               jsonWriter.writeSeparator();
            }

            Marker parentMarker = parents[parentIndex];
            jsonWriter.writeString((CharSequence)parentMarker.getName());
         }

         jsonWriter.writeArrayEnd();
      } else {
         jsonWriter.writeNull();
      }
   };
   private final TemplateResolver internalResolver;

   MarkerResolver(final TemplateResolverConfig config) {
      this.internalResolver = this.createInternalResolver(config);
   }

   private TemplateResolver createInternalResolver(final TemplateResolverConfig config) {
      String fieldName = config.getString("field");
      if ("name".equals(fieldName)) {
         return NAME_RESOLVER;
      } else if ("parents".equals(fieldName)) {
         return PARENTS_RESOLVER;
      } else {
         throw new IllegalArgumentException("unknown field: " + config);
      }
   }

   static String getName() {
      return "marker";
   }

   public boolean isResolvable(final LogEvent logEvent) {
      return logEvent.getMarker() != null;
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }
}
