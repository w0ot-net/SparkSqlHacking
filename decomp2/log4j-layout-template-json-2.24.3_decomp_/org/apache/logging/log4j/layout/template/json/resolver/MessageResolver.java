package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MultiformatMessage;
import org.apache.logging.log4j.message.ObjectMessage;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public final class MessageResolver implements EventResolver {
   private static final String[] FORMATS = new String[]{"JSON"};
   private final EventResolver internalResolver;

   MessageResolver(final TemplateResolverConfig config) {
      this.internalResolver = createInternalResolver(config);
   }

   static String getName() {
      return "message";
   }

   private static EventResolver createInternalResolver(final TemplateResolverConfig config) {
      boolean stringified = config.getBoolean("stringified", false);
      String fallbackKey = config.getString("fallbackKey");
      if (stringified && fallbackKey != null) {
         throw new IllegalArgumentException("fallbackKey is not allowed when stringified is enable: " + config);
      } else {
         return stringified ? createStringResolver() : createObjectResolver(fallbackKey);
      }
   }

   public void resolve(final LogEvent logEvent, final JsonWriter jsonWriter) {
      this.internalResolver.resolve(logEvent, jsonWriter);
   }

   private static EventResolver createStringResolver() {
      return (logEvent, jsonWriter) -> resolveString((String)null, (LogEvent)logEvent, jsonWriter);
   }

   private static void resolveString(final String fallbackKey, final LogEvent logEvent, final JsonWriter jsonWriter) {
      Message message = logEvent.getMessage();
      resolveString(fallbackKey, message, jsonWriter);
   }

   private static void resolveString(final String fallbackKey, final Message message, final JsonWriter jsonWriter) {
      if (fallbackKey != null) {
         jsonWriter.writeObjectStart();
         jsonWriter.writeObjectKey(fallbackKey);
      }

      if (message instanceof CharSequence) {
         jsonWriter.writeString((CharSequence)message);
      } else if (message instanceof StringBuilderFormattable) {
         jsonWriter.writeString((StringBuilderFormattable)message);
      } else {
         String formattedMessage = message.getFormattedMessage();
         jsonWriter.writeString((CharSequence)formattedMessage);
      }

      if (fallbackKey != null) {
         jsonWriter.writeObjectEnd();
      }

   }

   private static EventResolver createObjectResolver(final String fallbackKey) {
      return (logEvent, jsonWriter) -> {
         Message message = logEvent.getMessage();
         boolean simple = message instanceof SimpleMessage;
         if (!simple) {
            if (writeMultiformatMessage(jsonWriter, message)) {
               return;
            }

            if (writeObjectMessage(jsonWriter, message)) {
               return;
            }
         }

         resolveString(fallbackKey, logEvent, jsonWriter);
      };
   }

   private static boolean writeMultiformatMessage(final JsonWriter jsonWriter, final Message message) {
      if (!(message instanceof MultiformatMessage)) {
         return false;
      } else {
         MultiformatMessage multiformatMessage = (MultiformatMessage)message;
         boolean jsonSupported = false;
         String[] formats = multiformatMessage.getFormats();

         for(String format : formats) {
            if (FORMATS[0].equalsIgnoreCase(format)) {
               jsonSupported = true;
               break;
            }
         }

         if (!jsonSupported) {
            return false;
         } else {
            String messageJson = multiformatMessage.getFormattedMessage(FORMATS);
            jsonWriter.writeRawString((CharSequence)messageJson);
            return true;
         }
      }
   }

   private static boolean writeObjectMessage(final JsonWriter jsonWriter, final Message message) {
      if (!(message instanceof ObjectMessage)) {
         return false;
      } else {
         ObjectMessage objectMessage = (ObjectMessage)message;
         Object object = objectMessage.getParameter();
         jsonWriter.writeValue(object);
         return true;
      }
   }
}
