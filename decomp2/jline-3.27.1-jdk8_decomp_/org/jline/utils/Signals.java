package org.jline.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.function.Supplier;

public final class Signals {
   private Signals() {
   }

   public static Object register(String name, Runnable handler) {
      Objects.requireNonNull(handler);
      return register(name, handler, handler.getClass().getClassLoader());
   }

   public static Object register(String name, Runnable handler, ClassLoader loader) {
      try {
         Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
         Object signalHandler = Proxy.newProxyInstance(loader, new Class[]{signalHandlerClass}, (proxy, method, args) -> {
            if (method.getDeclaringClass() == Object.class) {
               if ("toString".equals(method.getName())) {
                  return handler.toString();
               }
            } else if (method.getDeclaringClass() == signalHandlerClass) {
               Log.trace((Supplier)(() -> "Calling handler " + toString(handler) + " for signal " + name));
               handler.run();
            }

            return null;
         });
         return doRegister(name, signalHandler);
      } catch (Exception e) {
         Log.debug("Error registering handler for signal ", name, e);
         return null;
      }
   }

   public static Object registerDefault(String name) {
      try {
         Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
         return doRegister(name, signalHandlerClass.getField("SIG_DFL").get((Object)null));
      } catch (Exception e) {
         Log.debug("Error registering default handler for signal ", name, e);
         return null;
      }
   }

   public static void unregister(String name, Object previous) {
      try {
         if (previous != null) {
            doRegister(name, previous);
         }
      } catch (Exception e) {
         Log.debug("Error unregistering handler for signal ", name, e);
      }

   }

   private static Object doRegister(String name, Object handler) throws Exception {
      Log.trace((Supplier)(() -> "Registering signal " + name + " with handler " + toString(handler)));
      Class<?> signalClass = Class.forName("sun.misc.Signal");
      Constructor<?> constructor = signalClass.getConstructor(String.class);

      Object signal;
      try {
         signal = constructor.newInstance(name);
      } catch (InvocationTargetException e) {
         if (e.getCause() instanceof IllegalArgumentException) {
            Log.trace((Supplier)(() -> "Ignoring unsupported signal " + name));
         } else {
            Log.debug("Error registering handler for signal ", name, e);
         }

         return null;
      }

      Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
      return signalClass.getMethod("handle", signalClass, signalHandlerClass).invoke((Object)null, signal, handler);
   }

   private static String toString(Object handler) {
      try {
         Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
         if (handler == signalHandlerClass.getField("SIG_DFL").get((Object)null)) {
            return "SIG_DFL";
         }

         if (handler == signalHandlerClass.getField("SIG_IGN").get((Object)null)) {
            return "SIG_IGN";
         }
      } catch (Throwable var2) {
      }

      return handler != null ? handler.toString() : "null";
   }
}
