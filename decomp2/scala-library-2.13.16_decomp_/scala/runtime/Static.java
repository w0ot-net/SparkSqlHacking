package scala.runtime;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public final class Static {
   private Static() {
   }

   public static CallSite bootstrap(MethodHandles.Lookup lookup, String invokedName, MethodType invokedType, MethodHandle handle, Object... args) throws Throwable {
      Object value = handle.invokeWithArguments(args);
      return new ConstantCallSite(MethodHandles.constant(invokedType.returnType(), value));
   }
}
