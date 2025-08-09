package org.datanucleus.metadata.xml;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.plugin.PluginManager;
import org.xml.sax.EntityResolver;

public class EntityResolverFactory {
   private static Map resolvers = new HashMap();

   private EntityResolverFactory() {
   }

   public static EntityResolver getInstance(PluginManager pluginManager, String handlerName) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
      EntityResolver resolver = (EntityResolver)resolvers.get(handlerName);
      if (resolver == null) {
         resolver = (EntityResolver)pluginManager.createExecutableExtension("org.datanucleus.metadata_handler", "name", handlerName, "entity-resolver", new Class[]{PluginManager.class}, new Object[]{pluginManager});
         resolvers.put(handlerName, resolver);
      }

      return resolver;
   }
}
