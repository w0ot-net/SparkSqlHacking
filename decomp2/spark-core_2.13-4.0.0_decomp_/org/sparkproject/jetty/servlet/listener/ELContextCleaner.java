package org.sparkproject.jetty.servlet.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Loader;

/** @deprecated */
@Deprecated
public class ELContextCleaner implements ServletContextListener {
   private static final Logger LOG = LoggerFactory.getLogger(ELContextCleaner.class);

   public void contextInitialized(ServletContextEvent sce) {
   }

   public void contextDestroyed(ServletContextEvent sce) {
      try {
         Class<?> beanELResolver = Loader.loadClass("jakarta.el.BeanELResolver");
         Field field = this.getField(beanELResolver);
         field.setAccessible(true);
         this.purgeEntries(field);
         if (LOG.isDebugEnabled()) {
            LOG.debug("javax.el.BeanELResolver purged");
         }
      } catch (ClassNotFoundException var4) {
      } catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
         LOG.warn("Cannot purge classes from javax.el.BeanELResolver", e);
      } catch (NoSuchFieldException var6) {
         LOG.debug("Not cleaning cached beans: no such field javax.el.BeanELResolver.properties");
      }

   }

   protected Field getField(Class beanELResolver) throws SecurityException, NoSuchFieldException {
      return beanELResolver == null ? null : beanELResolver.getDeclaredField("properties");
   }

   protected void purgeEntries(Field properties) throws IllegalArgumentException, IllegalAccessException {
      if (properties != null) {
         Map map = (Map)properties.get((Object)null);
         if (map != null) {
            Iterator<Class<?>> itor = map.keySet().iterator();

            while(itor.hasNext()) {
               Class<?> clazz = (Class)itor.next();
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Clazz: {} loaded by {}", clazz, clazz.getClassLoader());
               }

               if (Thread.currentThread().getContextClassLoader().equals(clazz.getClassLoader())) {
                  itor.remove();
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("removed");
                  }
               } else if (LOG.isDebugEnabled()) {
                  LOG.debug("not removed: contextclassloader={} clazz's classloader={}", Thread.currentThread().getContextClassLoader(), clazz.getClassLoader());
               }
            }

         }
      }
   }
}
