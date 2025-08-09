package breeze.util;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class UpdateSerializedObjects$ {
   public static final UpdateSerializedObjects$ MODULE$ = new UpdateSerializedObjects$();

   public void main(final String[] args) {
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])args), (a) -> {
         $anonfun$main$1(a);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$main$1(final String a) {
      package$.MODULE$.writeObject(new File(a), package$.MODULE$.readObject(new File(a), true));
   }

   private UpdateSerializedObjects$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
