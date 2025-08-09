package org.apache.avro.reflect;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.avro.AvroRuntimeException;

public class ReflectionUtil {
   private static FieldAccess fieldAccess;

   private ReflectionUtil() {
   }

   static void resetFieldAccess() {
      FieldAccess access = null;

      try {
         FieldAccess reflectAccess = new FieldAccessReflect();
         if (validate(reflectAccess)) {
            fieldAccess = reflectAccess;
         }

      } catch (Throwable var2) {
         throw new AvroRuntimeException("Unable to load a functional FieldAccess class!");
      }
   }

   private static Object load(String name, Class type) throws Exception {
      return ReflectionUtil.class.getClassLoader().loadClass(name).asSubclass(type).getDeclaredConstructor().newInstance();
   }

   public static FieldAccess getFieldAccess() {
      return fieldAccess;
   }

   private static boolean validate(FieldAccess access) throws Exception {
      return (new AccessorTestClass()).validate(access);
   }

   protected static Map resolveTypeVariables(Class iface) {
      return resolveTypeVariables(iface, new IdentityHashMap());
   }

   private static Map resolveTypeVariables(Class iface, Map reuse) {
      for(Type type : iface.getGenericInterfaces()) {
         if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type rawType = parameterizedType.getRawType();
            if (rawType instanceof Class) {
               Class<?> classType = (Class)rawType;
               TypeVariable<? extends Class<?>>[] typeParameters = classType.getTypeParameters();
               Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

               for(int i = 0; i < typeParameters.length; ++i) {
                  reuse.putIfAbsent(typeParameters[i], (Type)reuse.getOrDefault(actualTypeArguments[i], actualTypeArguments[i]));
               }

               resolveTypeVariables(classType, reuse);
            }
         }
      }

      return reuse;
   }

   private static Supplier getConstructorAsSupplier(Class clazz) {
      try {
         MethodHandles.Lookup lookup = MethodHandles.lookup();
         MethodHandle constructorHandle = lookup.findConstructor(clazz, MethodType.methodType(Void.TYPE));
         CallSite site = LambdaMetafactory.metafactory(lookup, "get", MethodType.methodType(Supplier.class), constructorHandle.type().generic(), constructorHandle, constructorHandle.type());
         return site.getTarget().invokeExact();
      } catch (Throwable var4) {
         return null;
      }
   }

   private static Supplier getOneArgConstructorAsSupplier(Class clazz, Class argumentClass, Object argument) {
      Function<V, R> supplierFunction = getConstructorAsFunction(argumentClass, clazz);
      return supplierFunction != null ? () -> supplierFunction.apply(argument) : null;
   }

   public static Function getConstructorAsFunction(Class parameterClass, Class clazz) {
      try {
         MethodHandles.Lookup lookup = MethodHandles.lookup();
         MethodHandle constructorHandle = lookup.findConstructor(clazz, MethodType.methodType(Void.TYPE, parameterClass));
         CallSite site = LambdaMetafactory.metafactory(lookup, "apply", MethodType.methodType(Function.class), constructorHandle.type().generic(), constructorHandle, constructorHandle.type());
         return site.getTarget().invokeExact();
      } catch (Throwable var5) {
         return null;
      }
   }

   static {
      resetFieldAccess();
   }

   private static final class AccessorTestClass {
      private boolean b = true;
      protected byte by = 15;
      public char c = 'c';
      short s = 123;
      int i = 999;
      long l = 12345L;
      float f = 2.2F;
      double d = 4.4;
      Object o = "foo";
      Integer i2 = 555;

      private boolean validate(FieldAccess access) throws Exception {
         boolean valid = true;
         valid &= this.validField(access, "b", this.b, false);
         valid &= this.validField(access, "by", this.by, -81);
         valid &= this.validField(access, "c", this.c, 'C');
         valid &= this.validField(access, "s", this.s, (short)321);
         valid &= this.validField(access, "i", this.i, 111);
         valid &= this.validField(access, "l", this.l, 54321L);
         valid &= this.validField(access, "f", this.f, 0.2F);
         valid &= this.validField(access, "d", this.d, 0.4);
         valid &= this.validField(access, "o", this.o, new Object());
         valid &= this.validField(access, "i2", this.i2, -555);
         return valid;
      }

      private boolean validField(FieldAccess access, String name, Object original, Object toSet) throws Exception {
         FieldAccessor a = this.accessor(access, name);
         boolean valid = original.equals(a.get(this));
         a.set(this, toSet);
         valid &= !original.equals(a.get(this));
         return valid;
      }

      private FieldAccessor accessor(FieldAccess access, String name) throws Exception {
         return access.getAccessor(this.getClass().getDeclaredField(name));
      }
   }
}
