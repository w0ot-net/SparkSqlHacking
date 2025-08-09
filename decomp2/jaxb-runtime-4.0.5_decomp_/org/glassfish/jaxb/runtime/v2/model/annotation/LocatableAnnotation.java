package org.glassfish.jaxb.runtime.v2.model.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.runtime.Location;

public class LocatableAnnotation implements InvocationHandler, Locatable, Location {
   private final Annotation core;
   private final Locatable upstream;
   private static final Map quicks = new HashMap();

   public static Annotation create(Annotation annotation, Locatable parentSourcePos) {
      if (annotation == null) {
         return null;
      } else {
         Class<? extends Annotation> type = annotation.annotationType();
         if (quicks.containsKey(type)) {
            return ((Quick)quicks.get(type)).newInstance(parentSourcePos, annotation);
         } else {
            ClassLoader cl = SecureLoader.getClassClassLoader(LocatableAnnotation.class);

            try {
               Class loadableT = Class.forName(type.getName(), false, cl);
               return loadableT != type ? annotation : (Annotation)Proxy.newProxyInstance(cl, new Class[]{type, Locatable.class}, new LocatableAnnotation(annotation, parentSourcePos));
            } catch (ClassNotFoundException var5) {
               return annotation;
            } catch (IllegalArgumentException var6) {
               return annotation;
            }
         }
      }
   }

   LocatableAnnotation(Annotation core, Locatable upstream) {
      this.core = core;
      this.upstream = upstream;
   }

   public Locatable getUpstream() {
      return this.upstream;
   }

   public Location getLocation() {
      return this;
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      try {
         if (method.getDeclaringClass() == Locatable.class) {
            return method.invoke(this, args);
         } else if (Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException();
         } else {
            return method.invoke(this.core, args);
         }
      } catch (InvocationTargetException e) {
         if (e.getTargetException() != null) {
            throw e.getTargetException();
         } else {
            throw e;
         }
      }
   }

   public String toString() {
      return this.core.toString();
   }

   static {
      for(Quick q : Init.getAll()) {
         quicks.put(q.annotationType(), q);
      }

   }
}
