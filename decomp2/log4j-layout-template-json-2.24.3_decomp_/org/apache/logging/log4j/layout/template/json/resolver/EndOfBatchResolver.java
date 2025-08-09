package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public final class EndOfBatchResolver implements EventResolver {
   private static final EndOfBatchResolver INSTANCE = new EndOfBatchResolver();

   private EndOfBatchResolver() {
   }

   static EndOfBatchResolver getInstance() {
      return INSTANCE;
   }

   static String getName() {
      return "endOfBatch";
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      boolean endOfBatch = logEvent.isEndOfBatch();
      jsonWriter.writeBoolean(endOfBatch);
   }
}
