package org.apache.commons.lang3.function;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.lang3.exception.UncheckedIllegalAccessException;

public final class MethodInvokers {
   public static BiConsumer asBiConsumer(Method method) {
      return (BiConsumer)asInterfaceInstance(BiConsumer.class, method);
   }

   public static BiFunction asBiFunction(Method method) {
      return (BiFunction)asInterfaceInstance(BiFunction.class, method);
   }

   public static FailableBiConsumer asFailableBiConsumer(Method method) {
      return (FailableBiConsumer)asInterfaceInstance(FailableBiConsumer.class, method);
   }

   public static FailableBiFunction asFailableBiFunction(Method method) {
      return (FailableBiFunction)asInterfaceInstance(FailableBiFunction.class, method);
   }

   public static FailableFunction asFailableFunction(Method method) {
      return (FailableFunction)asInterfaceInstance(FailableFunction.class, method);
   }

   public static FailableSupplier asFailableSupplier(Method method) {
      return (FailableSupplier)asInterfaceInstance(FailableSupplier.class, method);
   }

   public static Function asFunction(Method method) {
      return (Function)asInterfaceInstance(Function.class, method);
   }

   public static Object asInterfaceInstance(Class interfaceClass, Method method) {
      return MethodHandleProxies.asInterfaceInstance((Class)Objects.requireNonNull(interfaceClass, "interfaceClass"), unreflectUnchecked(method));
   }

   public static Supplier asSupplier(Method method) {
      return (Supplier)asInterfaceInstance(Supplier.class, method);
   }

   private static Method requireMethod(Method method) {
      return (Method)Objects.requireNonNull(method, "method");
   }

   private static MethodHandle unreflect(Method method) throws IllegalAccessException {
      return MethodHandles.lookup().unreflect(requireMethod(method));
   }

   private static MethodHandle unreflectUnchecked(Method method) {
      try {
         return unreflect(method);
      } catch (IllegalAccessException e) {
         throw new UncheckedIllegalAccessException(e);
      }
   }

   private MethodInvokers() {
   }
}
