package org.glassfish.jaxb.runtime.v2.model.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

public final class RuntimeInlineAnnotationReader extends AbstractInlineAnnotationReaderImpl implements RuntimeAnnotationReader {
   private final Map packageCache = new HashMap();

   public Annotation getFieldAnnotation(Class annotation, Field field, Locatable srcPos) {
      return LocatableAnnotation.create(field.getAnnotation(annotation), srcPos);
   }

   public boolean hasFieldAnnotation(Class annotationType, Field field) {
      return field.isAnnotationPresent(annotationType);
   }

   public boolean hasClassAnnotation(Class clazz, Class annotationType) {
      return clazz.isAnnotationPresent(annotationType);
   }

   public Annotation[] getAllFieldAnnotations(Field field, Locatable srcPos) {
      Annotation[] r = field.getAnnotations();

      for(int i = 0; i < r.length; ++i) {
         r[i] = LocatableAnnotation.create(r[i], srcPos);
      }

      return r;
   }

   public Annotation getMethodAnnotation(Class annotation, Method method, Locatable srcPos) {
      return LocatableAnnotation.create(method.getAnnotation(annotation), srcPos);
   }

   public boolean hasMethodAnnotation(Class annotation, Method method) {
      return method.isAnnotationPresent(annotation);
   }

   public Annotation[] getAllMethodAnnotations(Method method, Locatable srcPos) {
      Annotation[] r = method.getAnnotations();

      for(int i = 0; i < r.length; ++i) {
         r[i] = LocatableAnnotation.create(r[i], srcPos);
      }

      return r;
   }

   public Annotation getMethodParameterAnnotation(Class annotation, Method method, int paramIndex, Locatable srcPos) {
      Annotation[] pa = method.getParameterAnnotations()[paramIndex];

      for(Annotation a : pa) {
         if (a.annotationType() == annotation) {
            return LocatableAnnotation.create(a, srcPos);
         }
      }

      return null;
   }

   public Annotation getClassAnnotation(Class a, Class clazz, Locatable srcPos) {
      return LocatableAnnotation.create(clazz.getAnnotation(a), srcPos);
   }

   public Annotation getPackageAnnotation(Class a, Class clazz, Locatable srcPos) {
      Package p = clazz.getPackage();
      if (p == null) {
         return null;
      } else {
         Map<Package, Annotation> cache = (Map)this.packageCache.computeIfAbsent(a, (k) -> new HashMap());
         if (cache.containsKey(p)) {
            return (Annotation)cache.get(p);
         } else {
            A ann = (A)LocatableAnnotation.create(p.getAnnotation(a), srcPos);
            cache.put(p, ann);
            return ann;
         }
      }
   }

   public Class getClassValue(Annotation a, String name) {
      try {
         return (Class)a.annotationType().getMethod(name).invoke(a);
      } catch (IllegalAccessException e) {
         throw new IllegalAccessError(e.getMessage());
      } catch (InvocationTargetException e) {
         throw new InternalError(Messages.CLASS_NOT_FOUND.format(a.annotationType(), e.getMessage()));
      } catch (NoSuchMethodException e) {
         throw new NoSuchMethodError(e.getMessage());
      }
   }

   public Class[] getClassArrayValue(Annotation a, String name) {
      try {
         return (Class[])a.annotationType().getMethod(name).invoke(a);
      } catch (IllegalAccessException e) {
         throw new IllegalAccessError(e.getMessage());
      } catch (InvocationTargetException e) {
         throw new InternalError(e.getMessage());
      } catch (NoSuchMethodException e) {
         throw new NoSuchMethodError(e.getMessage());
      }
   }

   protected String fullName(Method m) {
      String var10000 = m.getDeclaringClass().getName();
      return var10000 + "#" + m.getName();
   }
}
