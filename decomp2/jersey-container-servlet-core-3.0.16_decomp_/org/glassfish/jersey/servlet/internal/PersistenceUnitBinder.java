package org.glassfish.jersey.servlet.internal;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.ServletConfig;
import jakarta.ws.rs.core.GenericType;
import java.lang.reflect.Proxy;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.Injectee;
import org.glassfish.jersey.internal.inject.InjectionResolver;
import org.glassfish.jersey.server.ContainerException;

public class PersistenceUnitBinder extends AbstractBinder {
   private final ServletConfig servletConfig;
   public static final String PERSISTENCE_UNIT_PREFIX = "unit:";

   public PersistenceUnitBinder(ServletConfig servletConfig) {
      this.servletConfig = servletConfig;
   }

   protected void configure() {
      this.bind(new PersistenceUnitInjectionResolver(this.servletConfig)).to(new GenericType() {
      });
   }

   @Singleton
   private static class PersistenceUnitInjectionResolver implements InjectionResolver {
      private final Map persistenceUnits;

      private PersistenceUnitInjectionResolver(ServletConfig servletConfig) {
         this.persistenceUnits = new HashMap();
         Enumeration parameterNames = servletConfig.getInitParameterNames();

         while(parameterNames.hasMoreElements()) {
            String key = (String)parameterNames.nextElement();
            if (key.startsWith("unit:")) {
               this.persistenceUnits.put(key.substring("unit:".length()), "java:comp/env/" + servletConfig.getInitParameter(key));
            }
         }

      }

      public Object resolve(Injectee injectee) {
         if (!injectee.getRequiredType().equals(EntityManagerFactory.class)) {
            return null;
         } else {
            PersistenceUnit annotation = (PersistenceUnit)injectee.getParent().getAnnotation(PersistenceUnit.class);
            String unitName = annotation.unitName();
            if (!this.persistenceUnits.containsKey(unitName)) {
               throw new ContainerException(LocalizationMessages.PERSISTENCE_UNIT_NOT_CONFIGURED(unitName));
            } else {
               return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{EntityManagerFactory.class}, new ThreadLocalNamedInvoker((String)this.persistenceUnits.get(unitName)));
            }
         }
      }

      public boolean isConstructorParameterIndicator() {
         return false;
      }

      public boolean isMethodParameterIndicator() {
         return false;
      }

      public Class getAnnotation() {
         return PersistenceUnit.class;
      }
   }
}
