package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Vector;
import java.lang.invoke.SerializedLambda;

public final class GenericOps$ {
   public static final GenericOps$ MODULE$ = new GenericOps$();

   public UFunc.InPlaceImpl2 updateFromPure(final UFunc.UImpl2 op, final UFunc.InPlaceImpl2 set) {
      return (a, b) -> {
         Object result = op.apply(a, b);
         set.apply(a, result);
      };
   }

   public boolean sparseEnoughForActiveIterator(final Vector v) {
      return v.activeSize() < v.size() / 4;
   }

   private GenericOps$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
