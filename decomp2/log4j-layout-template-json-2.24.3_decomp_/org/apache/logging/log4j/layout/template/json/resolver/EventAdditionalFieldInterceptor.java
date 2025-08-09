package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Map;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.layout.template.json.JsonTemplateLayout;
import org.apache.logging.log4j.layout.template.json.util.JsonReader;

@Plugin(
   name = "EventAdditionalFieldInterceptor",
   category = "JsonTemplateResolverInterceptor"
)
public class EventAdditionalFieldInterceptor implements EventResolverInterceptor {
   private static final EventAdditionalFieldInterceptor INSTANCE = new EventAdditionalFieldInterceptor();

   private EventAdditionalFieldInterceptor() {
   }

   @PluginFactory
   public static EventAdditionalFieldInterceptor getInstance() {
      return INSTANCE;
   }

   public Object processTemplateBeforeResolverInjection(final EventResolverContext context, final Object node) {
      JsonTemplateLayout.EventTemplateAdditionalField[] additionalFields = context.getEventTemplateAdditionalFields();
      if (additionalFields.length == 0) {
         return node;
      } else {
         Map<String, Object> objectNode;
         try {
            Map<String, Object> map = (Map)node;
            objectNode = map;
         } catch (ClassCastException var15) {
            String message = String.format("was expecting an object to merge additional fields: %s", node.getClass().getName());
            throw new IllegalArgumentException(message);
         }

         for(JsonTemplateLayout.EventTemplateAdditionalField additionalField : additionalFields) {
            String additionalFieldKey = additionalField.getKey();
            JsonTemplateLayout.EventTemplateAdditionalField.Format additionalFieldFormat = additionalField.getFormat();
            Object additionalFieldValue;
            if (JsonTemplateLayout.EventTemplateAdditionalField.Format.STRING.equals(additionalFieldFormat)) {
               additionalFieldValue = additionalField.getValue();
            } else {
               if (!JsonTemplateLayout.EventTemplateAdditionalField.Format.JSON.equals(additionalFieldFormat)) {
                  String message = String.format("unknown format %s for additional field: %s", additionalFieldKey, additionalFieldFormat);
                  throw new IllegalArgumentException(message);
               }

               try {
                  additionalFieldValue = JsonReader.read(additionalField.getValue());
               } catch (Exception error) {
                  String message = String.format("failed reading JSON provided by additional field: %s", additionalFieldKey);
                  throw new IllegalArgumentException(message, error);
               }
            }

            objectNode.put(additionalFieldKey, additionalFieldValue);
         }

         return node;
      }
   }
}
