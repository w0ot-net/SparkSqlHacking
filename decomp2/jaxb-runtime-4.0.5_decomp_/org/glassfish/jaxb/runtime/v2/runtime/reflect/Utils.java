package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;

final class Utils {
   private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
   static final Navigator REFLECTION_NAVIGATOR;

   private Utils() {
   }

   static {
      try {
         final Class<?> refNav = Class.forName("org.glassfish.jaxb.core.v2.model.nav.ReflectionNavigator");
         Method getInstance = (Method)AccessController.doPrivileged(new PrivilegedAction() {
            public Method run() {
               try {
                  Method getInstance = refNav.getDeclaredMethod("getInstance");
                  getInstance.setAccessible(true);
                  return getInstance;
               } catch (NoSuchMethodException var2) {
                  throw new IllegalStateException("ReflectionNavigator.getInstance can't be found");
               }
            }
         });
         REFLECTION_NAVIGATOR = (Navigator)getInstance.invoke((Object)null);
      } catch (ClassNotFoundException var2) {
         throw new IllegalStateException("Can't find ReflectionNavigator class");
      } catch (InvocationTargetException var3) {
         throw new IllegalStateException("ReflectionNavigator.getInstance throws the exception");
      } catch (IllegalAccessException var4) {
         throw new IllegalStateException("ReflectionNavigator.getInstance method is inaccessible");
      } catch (SecurityException e) {
         LOGGER.log(Level.FINE, "Unable to access ReflectionNavigator.getInstance", e);
         throw e;
      }
   }
}
