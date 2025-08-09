package org.glassfish.jersey.server.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.glassfish.jersey.internal.util.ReflectionHelper;

public final class MethodList implements Iterable {
   private AnnotatedMethod[] methods;

   public MethodList(Class c) {
      this(c, false);
   }

   public MethodList(Class c, boolean declaredMethods) {
      this((Collection)(declaredMethods ? getAllDeclaredMethods(c) : getMethods(c)));
   }

   private static List getAllDeclaredMethods(Class c) {
      List<Method> l;
      for(l = new ArrayList(); c != null && c != Object.class; c = c.getSuperclass()) {
         l.addAll((Collection)AccessController.doPrivileged(ReflectionHelper.getDeclaredMethodsPA(c)));
      }

      return l;
   }

   private static List getMethods(Class c) {
      return Arrays.asList(c.getMethods());
   }

   public MethodList(Collection methods) {
      List<AnnotatedMethod> l = new ArrayList(methods.size());

      for(Method m : methods) {
         if (!m.isSynthetic() && m.getDeclaringClass() != Object.class) {
            l.add(new AnnotatedMethod(m));
         }
      }

      this.methods = new AnnotatedMethod[l.size()];
      this.methods = (AnnotatedMethod[])l.toArray(this.methods);
   }

   public MethodList(Method... methods) {
      this((Collection)Arrays.asList(methods));
   }

   public MethodList(AnnotatedMethod... methods) {
      this.methods = methods;
   }

   public Iterator iterator() {
      return Arrays.asList(this.methods).iterator();
   }

   public MethodList isNotPublic() {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            return !Modifier.isPublic(m.getMethod().getModifiers());
         }
      });
   }

   public MethodList hasNumParams(final int paramCount) {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            return m.getParameterTypes().length == paramCount;
         }
      });
   }

   public MethodList hasReturnType(final Class returnType) {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            return m.getMethod().getReturnType() == returnType;
         }
      });
   }

   public MethodList nameStartsWith(final String prefix) {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            return m.getMethod().getName().startsWith(prefix);
         }
      });
   }

   public MethodList withAnnotation(final Class annotation) {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            return m.getAnnotation(annotation) != null;
         }
      });
   }

   public MethodList withMetaAnnotation(final Class annotation) {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            for(Annotation a : m.getAnnotations()) {
               if (a.annotationType().getAnnotation(annotation) != null) {
                  return true;
               }
            }

            return false;
         }
      });
   }

   public MethodList withoutAnnotation(final Class annotation) {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            return m.getAnnotation(annotation) == null;
         }
      });
   }

   public MethodList withoutMetaAnnotation(final Class annotation) {
      return this.filter(new Filter() {
         public boolean keep(AnnotatedMethod m) {
            for(Annotation a : m.getAnnotations()) {
               if (a.annotationType().getAnnotation(annotation) != null) {
                  return false;
               }
            }

            return true;
         }
      });
   }

   public MethodList filter(Filter filter) {
      List<AnnotatedMethod> result = new ArrayList();

      for(AnnotatedMethod m : this.methods) {
         if (filter.keep(m)) {
            result.add(m);
         }
      }

      return new MethodList((AnnotatedMethod[])result.toArray(new AnnotatedMethod[result.size()]));
   }

   public interface Filter {
      boolean keep(AnnotatedMethod var1);
   }
}
