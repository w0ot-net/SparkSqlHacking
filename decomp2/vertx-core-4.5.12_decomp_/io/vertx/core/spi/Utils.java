package io.vertx.core.spi;

import io.vertx.core.ServiceHelper;
import io.vertx.core.json.jackson.JacksonFactory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Utils {
   static JsonFactory load() {
      List<JsonFactory> factories = new ArrayList(ServiceHelper.loadFactories(JsonFactory.class));
      factories.sort(Comparator.comparingInt(JsonFactory::order));
      return (JsonFactory)(factories.size() > 0 ? (JsonFactory)factories.iterator().next() : JacksonFactory.INSTANCE);
   }
}
