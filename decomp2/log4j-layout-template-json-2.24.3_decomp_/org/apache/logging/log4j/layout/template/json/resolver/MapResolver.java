package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

public final class MapResolver extends ReadOnlyStringMapResolver {
   MapResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      super(context, config, MapResolver::toMap);
   }

   private static ReadOnlyStringMap toMap(final LogEvent logEvent) {
      Message message = logEvent.getMessage();
      if (!(message instanceof MapMessage)) {
         return null;
      } else {
         MapMessage<?, Object> mapMessage = (MapMessage)message;
         return mapMessage.getIndexedReadOnlyStringMap();
      }
   }

   static String getName() {
      return "map";
   }
}
