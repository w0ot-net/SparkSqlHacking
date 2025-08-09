package org.glassfish.hk2.utilities.reflection.internal;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.glassfish.hk2.utilities.reflection.MethodWrapper;
import org.glassfish.hk2.utilities.reflection.Pretty;

public class ClassReflectionHelperUtilities {
   static final String CONVENTION_POST_CONSTRUCT = "postConstruct";
   static final String CONVENTION_PRE_DESTROY = "preDestroy";
   private static final Set OBJECT_METHODS = getObjectMethods();
   private static final Set OBJECT_FIELDS = getObjectFields();

   private static Set getObjectMethods() {
      return (Set)AccessController.doPrivileged(new PrivilegedAction() {
         public Set run() {
            Set<MethodWrapper> retVal = new LinkedHashSet();

            for(Method method : Object.class.getDeclaredMethods()) {
               retVal.add(new MethodWrapperImpl(method));
            }

            return retVal;
         }
      });
   }

   private static Set getObjectFields() {
      return (Set)AccessController.doPrivileged(new PrivilegedAction() {
         public Set run() {
            Set<Field> retVal = new LinkedHashSet();

            for(Field field : Object.class.getDeclaredFields()) {
               retVal.add(field);
            }

            return retVal;
         }
      });
   }

   private static Method[] secureGetDeclaredMethods(final Class clazz) {
      return (Method[])AccessController.doPrivileged(new PrivilegedAction() {
         public Method[] run() {
            return clazz.getDeclaredMethods();
         }
      });
   }

   private static Field[] secureGetDeclaredFields(final Class clazz) {
      return (Field[])AccessController.doPrivileged(new PrivilegedAction() {
         public Field[] run() {
            return clazz.getDeclaredFields();
         }
      });
   }

   private static Set getDeclaredMethodWrappers(Class clazz) {
      Method[] declaredMethods = secureGetDeclaredMethods(clazz);
      Set<MethodWrapper> retVal = new LinkedHashSet();

      for(Method method : declaredMethods) {
         retVal.add(new MethodWrapperImpl(method));
      }

      return retVal;
   }

   private static Set getDeclaredFieldWrappers(Class clazz) {
      Field[] declaredFields = secureGetDeclaredFields(clazz);
      Set<Field> retVal = new LinkedHashSet();

      for(Field field : declaredFields) {
         retVal.add(field);
      }

      return retVal;
   }

   static Set getAllFieldWrappers(Class clazz) {
      if (clazz == null) {
         return Collections.emptySet();
      } else if (Object.class.equals(clazz)) {
         return OBJECT_FIELDS;
      } else if (clazz.isInterface()) {
         return Collections.emptySet();
      } else {
         Set<Field> retVal = new LinkedHashSet();
         retVal.addAll(getDeclaredFieldWrappers(clazz));
         retVal.addAll(getAllFieldWrappers(clazz.getSuperclass()));
         return retVal;
      }
   }

   static Set getAllMethodWrappers(Class clazz) {
      if (clazz == null) {
         return Collections.emptySet();
      } else if (Object.class.equals(clazz)) {
         return OBJECT_METHODS;
      } else {
         Set<MethodWrapper> retVal = new LinkedHashSet();
         if (clazz.isInterface()) {
            for(Method m : clazz.getDeclaredMethods()) {
               MethodWrapper wrapper = new MethodWrapperImpl(m);
               retVal.add(wrapper);
            }

            for(Class extendee : clazz.getInterfaces()) {
               retVal.addAll(getAllMethodWrappers(extendee));
            }
         } else {
            retVal.addAll(getDeclaredMethodWrappers(clazz));
            retVal.addAll(getAllMethodWrappers(clazz.getSuperclass()));
         }

         return retVal;
      }
   }

   static boolean isPostConstruct(Method m) {
      if (m.isAnnotationPresent(PostConstruct.class)) {
         if (m.getParameterTypes().length != 0) {
            throw new IllegalArgumentException("The method " + Pretty.method(m) + " annotated with @PostConstruct must not have any arguments");
         } else {
            return true;
         }
      } else {
         return m.getParameterTypes().length != 0 ? false : "postConstruct".equals(m.getName());
      }
   }

   static boolean isPreDestroy(Method m) {
      if (m.isAnnotationPresent(PreDestroy.class)) {
         if (m.getParameterTypes().length != 0) {
            throw new IllegalArgumentException("The method " + Pretty.method(m) + " annotated with @PreDestroy must not have any arguments");
         } else {
            return true;
         }
      } else {
         return m.getParameterTypes().length != 0 ? false : "preDestroy".equals(m.getName());
      }
   }
}
