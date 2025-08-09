package scala.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SerializedLambda;

class MethodHandleConstants {
   static final MethodHandle LAMBDA_DESERIALIZE_DESERIALIZE_LAMBDA = lookupDeserialize();

   private static MethodHandle lookupDeserialize() {
      try {
         return MethodHandles.lookup().findVirtual(Class.forName("scala.runtime.LambdaDeserialize"), "deserializeLambda", MethodType.methodType(Object.class, SerializedLambda.class));
      } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
         throw new ExceptionInInitializerError(e);
      }
   }
}
