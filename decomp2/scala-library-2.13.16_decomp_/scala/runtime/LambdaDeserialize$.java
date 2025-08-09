package scala.runtime;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Seq;

public final class LambdaDeserialize$ {
   public static final LambdaDeserialize$ MODULE$ = new LambdaDeserialize$();

   public CallSite bootstrap(final MethodHandles.Lookup lookup, final String invokedName, final MethodType invokedType, final MethodHandle... targetMethods) {
      return this.bootstrap(lookup, invokedName, invokedType, (Seq)ScalaRunTime$.MODULE$.wrapRefArray(targetMethods));
   }

   public CallSite bootstrap(final MethodHandles.Lookup lookup, final String invokedName, final MethodType invokedType, final Seq targetMethods) throws Throwable {
      MethodHandle[] targetMethodsArray = (MethodHandle[])((ArraySeq)targetMethods).unsafeArray();
      MethodHandle exact = MethodHandleConstants.LAMBDA_DESERIALIZE_DESERIALIZE_LAMBDA.bindTo(new LambdaDeserialize(lookup, targetMethodsArray)).asType(invokedType);
      return new ConstantCallSite(exact);
   }

   public String nameAndDescriptorKey(final String name, final String descriptor) {
      return (new StringBuilder(0)).append(name).append(descriptor).toString();
   }

   private LambdaDeserialize$() {
   }
}
