package org.apache.parquet.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.Exceptions;
import org.apache.parquet.Preconditions;

public class DynConstructors {
   private static String formatProblems(Map problems) {
      StringBuilder sb = new StringBuilder();
      boolean first = true;

      for(Map.Entry problem : problems.entrySet()) {
         if (first) {
            first = false;
         } else {
            sb.append("\n");
         }

         sb.append("\tMissing ").append((String)problem.getKey()).append(" [").append(((Throwable)problem.getValue()).getClass().getName()).append(": ").append(((Throwable)problem.getValue()).getMessage()).append("]");
      }

      return sb.toString();
   }

   private static String methodName(Class targetClass, Class... types) {
      StringBuilder sb = new StringBuilder();
      sb.append(targetClass.getName()).append("(");
      boolean first = true;

      for(Class type : types) {
         if (first) {
            first = false;
         } else {
            sb.append(",");
         }

         sb.append(type.getName());
      }

      sb.append(")");
      return sb.toString();
   }

   public static class Ctor extends DynMethods.UnboundMethod {
      private final Constructor ctor;
      private final Class constructed;

      private Ctor(Constructor constructor, Class constructed) {
         super((Method)null, "newInstance");
         this.ctor = constructor;
         this.constructed = constructed;
      }

      public Class getConstructedClass() {
         return this.constructed;
      }

      public Object newInstanceChecked(Object... args) throws Exception {
         try {
            return this.ctor.newInstance(args);
         } catch (IllegalAccessException | InstantiationException e) {
            throw e;
         } catch (InvocationTargetException e) {
            Exceptions.throwIfInstance(e.getCause(), Exception.class);
            Exceptions.throwIfInstance(e.getCause(), RuntimeException.class);
            throw new RuntimeException(e.getCause());
         }
      }

      public Object newInstance(Object... args) {
         try {
            return this.newInstanceChecked(args);
         } catch (Exception e) {
            Exceptions.throwIfInstance(e, RuntimeException.class);
            throw new RuntimeException(e);
         }
      }

      public Object invoke(Object target, Object... args) {
         Preconditions.checkArgument(target == null, "Invalid call to constructor: target must be null");
         return this.newInstance(args);
      }

      public Object invokeChecked(Object target, Object... args) throws Exception {
         Preconditions.checkArgument(target == null, "Invalid call to constructor: target must be null");
         return this.newInstanceChecked(args);
      }

      public DynMethods.BoundMethod bind(Object receiver) {
         throw new IllegalStateException("Cannot bind constructors");
      }

      public boolean isStatic() {
         return true;
      }

      public String toString() {
         return this.getClass().getSimpleName() + "(constructor=" + this.ctor + ", class=" + this.constructed + ")";
      }
   }

   public static class Builder {
      private final Class baseClass;
      private ClassLoader loader = Thread.currentThread().getContextClassLoader();
      private Ctor ctor = null;
      private Map problems = new HashMap();

      public Builder(Class baseClass) {
         this.baseClass = baseClass;
      }

      public Builder() {
         this.baseClass = null;
      }

      public Builder loader(ClassLoader loader) {
         this.loader = loader;
         return this;
      }

      public Builder impl(String className, Class... types) {
         if (this.ctor != null) {
            return this;
         } else {
            try {
               Class<?> targetClass = Class.forName(className, true, this.loader);
               this.impl(targetClass, types);
            } catch (ClassNotFoundException | NoClassDefFoundError e) {
               this.problems.put(className, e);
            }

            return this;
         }
      }

      public Builder impl(Class targetClass, Class... types) {
         if (this.ctor != null) {
            return this;
         } else {
            try {
               this.ctor = new Ctor(targetClass.getConstructor(types), targetClass);
            } catch (NoSuchMethodException e) {
               this.problems.put(DynConstructors.methodName(targetClass, types), e);
            }

            return this;
         }
      }

      public Builder hiddenImpl(Class... types) {
         this.hiddenImpl(this.baseClass, types);
         return this;
      }

      public Builder hiddenImpl(String className, Class... types) {
         if (this.ctor != null) {
            return this;
         } else {
            try {
               Class targetClass = Class.forName(className, true, this.loader);
               this.hiddenImpl(targetClass, types);
            } catch (ClassNotFoundException | NoClassDefFoundError e) {
               this.problems.put(className, e);
            }

            return this;
         }
      }

      public Builder hiddenImpl(Class targetClass, Class... types) {
         if (this.ctor != null) {
            return this;
         } else {
            try {
               Constructor<T> hidden = targetClass.getDeclaredConstructor(types);
               AccessController.doPrivileged(new MakeAccessible(hidden));
               this.ctor = new Ctor(hidden, targetClass);
            } catch (SecurityException | NoSuchMethodException e) {
               this.problems.put(DynConstructors.methodName(targetClass, types), e);
            }

            return this;
         }
      }

      public Ctor buildChecked() throws NoSuchMethodException {
         if (this.ctor != null) {
            return this.ctor;
         } else {
            throw new NoSuchMethodException("Cannot find constructor for " + this.baseClass + "\n" + DynConstructors.formatProblems(this.problems));
         }
      }

      public Ctor build() {
         if (this.ctor != null) {
            return this.ctor;
         } else {
            throw new RuntimeException("Cannot find constructor for " + this.baseClass + "\n" + DynConstructors.formatProblems(this.problems));
         }
      }
   }

   private static class MakeAccessible implements PrivilegedAction {
      private Constructor hidden;

      public MakeAccessible(Constructor hidden) {
         this.hidden = hidden;
      }

      public Void run() {
         this.hidden.setAccessible(true);
         return null;
      }
   }
}
