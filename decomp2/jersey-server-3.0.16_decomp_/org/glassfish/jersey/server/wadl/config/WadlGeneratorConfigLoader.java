package org.glassfish.jersey.server.wadl.config;

import jakarta.ws.rs.ProcessingException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.server.internal.LocalizationMessages;

public class WadlGeneratorConfigLoader {
   public static WadlGeneratorConfig loadWadlGeneratorsFromConfig(Map properties) {
      Object wadlGeneratorConfigProperty = properties.get("jersey.config.server.wadl.generatorConfig");
      if (wadlGeneratorConfigProperty == null) {
         return new WadlGeneratorConfig() {
            public List configure() {
               return Collections.emptyList();
            }
         };
      } else {
         try {
            if (wadlGeneratorConfigProperty instanceof WadlGeneratorConfig) {
               return (WadlGeneratorConfig)wadlGeneratorConfigProperty;
            } else {
               Class<? extends WadlGeneratorConfig> configClazz;
               if (wadlGeneratorConfigProperty instanceof Class) {
                  configClazz = ((Class)wadlGeneratorConfigProperty).asSubclass(WadlGeneratorConfig.class);
               } else {
                  if (!(wadlGeneratorConfigProperty instanceof String)) {
                     throw new ProcessingException(LocalizationMessages.ERROR_WADL_GENERATOR_CONFIG_LOADER_PROPERTY("jersey.config.server.wadl.generatorConfig", wadlGeneratorConfigProperty.getClass().getName()));
                  }

                  configClazz = ((Class)AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA((String)wadlGeneratorConfigProperty))).asSubclass(WadlGeneratorConfig.class);
               }

               return (WadlGeneratorConfig)configClazz.newInstance();
            }
         } catch (PrivilegedActionException pae) {
            throw new ProcessingException(LocalizationMessages.ERROR_WADL_GENERATOR_CONFIG_LOADER("jersey.config.server.wadl.generatorConfig"), pae.getCause());
         } catch (Exception e) {
            throw new ProcessingException(LocalizationMessages.ERROR_WADL_GENERATOR_CONFIG_LOADER("jersey.config.server.wadl.generatorConfig"), e);
         }
      }
   }
}
