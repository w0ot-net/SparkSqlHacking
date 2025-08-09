package org.glassfish.jaxb.core.v2;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jaxb.core.Utils;

public final class ClassFactory {
   private static final Class[] emptyClass = new Class[0];
   private static final Object[] emptyObject = new Object[0];
   private static final Logger logger = Utils.getClassLogger();
   private static final ThreadLocal tls = new ThreadLocal() {
      public Map initialValue() {
         return new WeakHashMap();
      }
   };

   private ClassFactory() {
   }

   public static void cleanCache() {
      if (tls != null) {
         try {
            tls.remove();
         } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to clean Thread Local cache of classes used in Unmarshaller: {0}", e.getLocalizedMessage());
         }
      }

   }

   public static Object create0(final Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
      Map<Class, WeakReference<Constructor>> m = (Map)tls.get();
      Constructor<T> cons = null;
      WeakReference<Constructor> consRef = (WeakReference)m.get(clazz);
      if (consRef != null) {
         cons = (Constructor)consRef.get();
      }

      if (cons == null) {
         if (System.getSecurityManager() == null) {
            cons = tryGetDeclaredConstructor(clazz);
         } else {
            cons = (Constructor)AccessController.doPrivileged(new PrivilegedAction() {
               public Constructor run() {
                  return ClassFactory.tryGetDeclaredConstructor(clazz);
               }
            });
         }

         int classMod = clazz.getModifiers();
         if (!Modifier.isPublic(classMod) || !Modifier.isPublic(cons.getModifiers())) {
            try {
               cons.setAccessible(true);
            } catch (SecurityException e) {
               logger.log(Level.FINE, "Unable to make the constructor of " + clazz + " accessible", e);
               throw e;
            }
         }

         m.put(clazz, new WeakReference(cons));
      }

      return cons.newInstance(emptyObject);
   }

   private static Constructor tryGetDeclaredConstructor(Class clazz) {
      try {
         return clazz.getDeclaredConstructor(emptyClass);
      } catch (NoSuchMethodException var3) {
         logger.log(Level.INFO, "No default constructor found on " + clazz, var3);
         NoSuchMethodError exp;
         if (clazz.getDeclaringClass() != null && !Modifier.isStatic(clazz.getModifiers())) {
            exp = new NoSuchMethodError(Messages.NO_DEFAULT_CONSTRUCTOR_IN_INNER_CLASS.format(clazz.getName()));
         } else {
            exp = new NoSuchMethodError(var3.getMessage());
         }

         exp.initCause(var3);
         throw exp;
      }
   }

   public static Object create(Class clazz) {
      try {
         return create0(clazz);
      } catch (InstantiationException e) {
         logger.log(Level.INFO, "failed to create a new instance of " + clazz, e);
         throw new InstantiationError(e.toString());
      } catch (IllegalAccessException e) {
         logger.log(Level.INFO, "failed to create a new instance of " + clazz, e);
         throw new IllegalAccessError(e.toString());
      } catch (InvocationTargetException e) {
         Throwable target = e.getTargetException();
         if (target instanceof RuntimeException) {
            throw (RuntimeException)target;
         } else if (target instanceof Error) {
            throw (Error)target;
         } else {
            throw new IllegalStateException(target);
         }
      }
   }

   public static Object create(Method method) {
      try {
         return method.invoke((Object)null, emptyObject);
      } catch (InvocationTargetException ive) {
         Throwable target = ive.getTargetException();
         if (target instanceof RuntimeException) {
            throw (RuntimeException)target;
         } else if (target instanceof Error) {
            throw (Error)target;
         } else {
            throw new IllegalStateException(target);
         }
      } catch (IllegalAccessException e) {
         logger.log(Level.INFO, "failed to create a new instance of " + method.getReturnType().getName(), e);
         throw new IllegalAccessError(e.toString());
      } catch (NullPointerException | ExceptionInInitializerError | IllegalArgumentException iae) {
         logger.log(Level.INFO, "failed to create a new instance of " + method.getReturnType().getName(), iae);
         NoSuchMethodError exp = new NoSuchMethodError(((Throwable)iae).getMessage());
         exp.initCause(iae);
         throw exp;
      }
   }

   public static Class inferImplClass(Class fieldType, Class[] knownImplClasses) {
      if (!fieldType.isInterface()) {
         return fieldType;
      } else {
         for(Class impl : knownImplClasses) {
            if (fieldType.isAssignableFrom(impl)) {
               return impl.asSubclass(fieldType);
            }
         }

         return null;
      }
   }
}
