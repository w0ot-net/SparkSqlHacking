package org.apache.parquet.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import org.apache.parquet.Exceptions;
import org.apache.parquet.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynMethods {
   private static final Logger LOG = LoggerFactory.getLogger(DynMethods.class);

   public static class UnboundMethod {
      private final Method method;
      private final String name;
      private final int argLength;
      private static UnboundMethod NOOP = new UnboundMethod((Method)null, "NOOP") {
         public Object invokeChecked(Object target, Object... args) throws Exception {
            return null;
         }

         public BoundMethod bind(Object receiver) {
            return new BoundMethod(this, receiver);
         }

         public StaticMethod asStatic() {
            return new StaticMethod(this);
         }

         public boolean isStatic() {
            return true;
         }

         public String toString() {
            return "DynMethods.UnboundMethod(NOOP)";
         }
      };

      UnboundMethod(Method method, String name) {
         this.method = method;
         this.name = name;
         this.argLength = method != null && !method.isVarArgs() ? method.getParameterTypes().length : -1;
      }

      public Object invokeChecked(Object target, Object... args) throws Exception {
         try {
            return this.argLength < 0 ? this.method.invoke(target, args) : this.method.invoke(target, Arrays.copyOfRange(args, 0, this.argLength));
         } catch (InvocationTargetException e) {
            Exceptions.throwIfInstance(e.getCause(), Exception.class);
            Exceptions.throwIfInstance(e.getCause(), RuntimeException.class);
            throw new RuntimeException(e.getCause());
         }
      }

      public Object invoke(Object target, Object... args) {
         try {
            return this.invokeChecked(target, args);
         } catch (Exception e) {
            Exceptions.throwIfInstance(e, RuntimeException.class);
            throw new RuntimeException(e);
         }
      }

      public BoundMethod bind(Object receiver) {
         Preconditions.checkState(!this.isStatic(), "Cannot bind static method %s", (Object)this.method.toGenericString());
         Preconditions.checkArgument(this.method.getDeclaringClass().isAssignableFrom(receiver.getClass()), "Cannot bind %s to instance of %s", this.method.toGenericString(), receiver.getClass());
         return new BoundMethod(this, receiver);
      }

      public boolean isStatic() {
         return Modifier.isStatic(this.method.getModifiers());
      }

      public boolean isNoop() {
         return this == NOOP;
      }

      public StaticMethod asStatic() {
         Preconditions.checkState(this.isStatic(), "Method is not static");
         return new StaticMethod(this);
      }

      public String toString() {
         return "DynMethods.UnboundMethod(name=" + this.name + " method=" + this.method.toGenericString() + ")";
      }
   }

   public static class BoundMethod {
      private final UnboundMethod method;
      private final Object receiver;

      private BoundMethod(UnboundMethod method, Object receiver) {
         this.method = method;
         this.receiver = receiver;
      }

      public Object invokeChecked(Object... args) throws Exception {
         return this.method.invokeChecked(this.receiver, args);
      }

      public Object invoke(Object... args) {
         return this.method.invoke(this.receiver, args);
      }
   }

   public static class StaticMethod {
      private final UnboundMethod method;

      private StaticMethod(UnboundMethod method) {
         this.method = method;
      }

      public Object invokeChecked(Object... args) throws Exception {
         return this.method.invokeChecked((Object)null, args);
      }

      public Object invoke(Object... args) {
         return this.method.invoke((Object)null, args);
      }
   }

   public static class Builder {
      private final String name;
      private ClassLoader loader = Thread.currentThread().getContextClassLoader();
      private UnboundMethod method = null;

      public Builder(String methodName) {
         this.name = methodName;
      }

      public Builder loader(ClassLoader loader) {
         this.loader = loader;
         return this;
      }

      public Builder orNoop() {
         if (this.method == null) {
            this.method = DynMethods.UnboundMethod.NOOP;
         }

         return this;
      }

      public Builder impl(String className, String methodName, Class... argClasses) {
         if (this.method != null) {
            return this;
         } else {
            try {
               Class<?> targetClass = Class.forName(className, true, this.loader);
               this.impl(targetClass, methodName, argClasses);
            } catch (ClassNotFoundException e) {
               DynMethods.LOG.debug("failed to load class {}", className, e);
            }

            return this;
         }
      }

      public Builder impl(String className, Class... argClasses) {
         this.impl(className, this.name, argClasses);
         return this;
      }

      public Builder impl(Class targetClass, String methodName, Class... argClasses) {
         if (this.method != null) {
            return this;
         } else {
            try {
               this.method = new UnboundMethod(targetClass.getMethod(methodName, argClasses), this.name);
            } catch (NoSuchMethodException e) {
               DynMethods.LOG.debug("failed to load method {} from class {}", new Object[]{methodName, targetClass, e});
            }

            return this;
         }
      }

      public Builder impl(Class targetClass, Class... argClasses) {
         this.impl(targetClass, this.name, argClasses);
         return this;
      }

      public Builder ctorImpl(Class targetClass, Class... argClasses) {
         if (this.method != null) {
            return this;
         } else {
            try {
               this.method = (new DynConstructors.Builder()).impl(targetClass, argClasses).buildChecked();
            } catch (NoSuchMethodException e) {
               DynMethods.LOG.debug("failed to load constructor arity {} from class {}", new Object[]{argClasses.length, targetClass, e});
            }

            return this;
         }
      }

      public Builder ctorImpl(String className, Class... argClasses) {
         if (this.method != null) {
            return this;
         } else {
            try {
               this.method = (new DynConstructors.Builder()).impl(className, argClasses).buildChecked();
            } catch (NoSuchMethodException e) {
               DynMethods.LOG.debug("failed to load constructor arity {} from class {}", new Object[]{argClasses.length, className, e});
            }

            return this;
         }
      }

      public Builder hiddenImpl(String className, String methodName, Class... argClasses) {
         if (this.method != null) {
            return this;
         } else {
            try {
               Class<?> targetClass = Class.forName(className, true, this.loader);
               this.hiddenImpl(targetClass, methodName, argClasses);
            } catch (ClassNotFoundException e) {
               DynMethods.LOG.debug("failed to load class {}", className, e);
            }

            return this;
         }
      }

      public Builder hiddenImpl(String className, Class... argClasses) {
         this.hiddenImpl(className, this.name, argClasses);
         return this;
      }

      public Builder hiddenImpl(Class targetClass, String methodName, Class... argClasses) {
         if (this.method != null) {
            return this;
         } else {
            try {
               Method hidden = targetClass.getDeclaredMethod(methodName, argClasses);
               AccessController.doPrivileged(new MakeAccessible(hidden));
               this.method = new UnboundMethod(hidden, this.name);
            } catch (NoSuchMethodException | SecurityException e) {
               DynMethods.LOG.debug("failed to load method {} from class {}", new Object[]{methodName, targetClass, e});
            }

            return this;
         }
      }

      public Builder hiddenImpl(Class targetClass, Class... argClasses) {
         this.hiddenImpl(targetClass, this.name, argClasses);
         return this;
      }

      public UnboundMethod buildChecked() throws NoSuchMethodException {
         if (this.method != null) {
            return this.method;
         } else {
            throw new NoSuchMethodException("Cannot find method: " + this.name);
         }
      }

      public UnboundMethod build() {
         if (this.method != null) {
            return this.method;
         } else {
            throw new RuntimeException("Cannot find method: " + this.name);
         }
      }

      public BoundMethod buildChecked(Object receiver) throws NoSuchMethodException {
         return this.buildChecked().bind(receiver);
      }

      public BoundMethod build(Object receiver) {
         return this.build().bind(receiver);
      }

      public StaticMethod buildStaticChecked() throws NoSuchMethodException {
         return this.buildChecked().asStatic();
      }

      public StaticMethod buildStatic() {
         return this.build().asStatic();
      }
   }

   private static class MakeAccessible implements PrivilegedAction {
      private Method hidden;

      public MakeAccessible(Method hidden) {
         this.hidden = hidden;
      }

      public Void run() {
         this.hidden.setAccessible(true);
         return null;
      }
   }
}
