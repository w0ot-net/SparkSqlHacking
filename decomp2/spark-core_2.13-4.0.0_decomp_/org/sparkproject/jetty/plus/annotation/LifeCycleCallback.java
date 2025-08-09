package org.sparkproject.jetty.plus.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import org.sparkproject.jetty.util.IntrospectionUtil;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.TypeUtil;

public abstract class LifeCycleCallback {
   public static final Object[] __EMPTY_ARGS = new Object[0];
   private Method _target;
   private Class _targetClass;
   private final String _className;
   private final String _methodName;

   public LifeCycleCallback(String className, String methodName) {
      this._className = (String)Objects.requireNonNull(className);
      this._methodName = (String)Objects.requireNonNull(methodName);
   }

   public LifeCycleCallback(Class clazz, String methodName) {
      this._targetClass = (Class)Objects.requireNonNull(clazz);
      this._methodName = (String)Objects.requireNonNull(methodName);

      try {
         Method method = IntrospectionUtil.findMethod(clazz, methodName, (Class[])null, true, true);
         this.validate(clazz, method);
         this._target = method;
         this._className = clazz.getName();
      } catch (NoSuchMethodException var4) {
         throw new IllegalArgumentException("Method " + methodName + " not found on class " + clazz.getName());
      }
   }

   public Class getTargetClass() {
      return this._targetClass;
   }

   public String getTargetClassName() {
      return this._className;
   }

   public String getMethodName() {
      return this._methodName;
   }

   public Method getTarget() {
      return this._target;
   }

   public void callback(Object instance) throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
      if (this._target == null) {
         if (this._targetClass == null) {
            this._targetClass = Loader.loadClass(this._className);
         }

         this._target = this._targetClass.getDeclaredMethod(this._methodName, TypeUtil.NO_ARGS);
      }

      if (this._target != null) {
         boolean accessibility = this.getTarget().isAccessible();
         this.getTarget().setAccessible(true);
         this.getTarget().invoke(instance, __EMPTY_ARGS);
         this.getTarget().setAccessible(accessibility);
      }

   }

   public Method findMethod(Package pack, Class clazz, String methodName, boolean checkInheritance) {
      if (clazz == null) {
         return null;
      } else {
         try {
            Method method = clazz.getDeclaredMethod(methodName);
            if (!checkInheritance) {
               return method;
            } else {
               int modifiers = method.getModifiers();
               return !Modifier.isProtected(modifiers) && !Modifier.isPublic(modifiers) && (Modifier.isPrivate(modifiers) || !pack.equals(clazz.getPackage())) ? this.findMethod(clazz.getPackage(), clazz.getSuperclass(), methodName, true) : method;
            }
         } catch (NoSuchMethodException var7) {
            return this.findMethod(clazz.getPackage(), clazz.getSuperclass(), methodName, true);
         }
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this._className, this._methodName});
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (this == o) {
         return true;
      } else if (!LifeCycleCallback.class.isInstance(o)) {
         return false;
      } else {
         LifeCycleCallback callback = (LifeCycleCallback)o;
         return this.getTargetClassName().equals(callback.getTargetClassName()) && this.getMethodName().equals(callback.getMethodName());
      }
   }

   public abstract void validate(Class var1, Method var2);
}
