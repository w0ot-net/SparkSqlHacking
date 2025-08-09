package scala.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Predef.;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class DebugUtils$ {
   public static final DebugUtils$ MODULE$ = new DebugUtils$();

   public String buildString(final Function1 closure) {
      StringBuilder output = new StringBuilder();
      closure.apply((Function1)(any) -> {
         $anonfun$buildString$1(output, any);
         return BoxedUnit.UNIT;
      });
      return output.result();
   }

   public String arrayString(final Object array, final int from, final int until) {
      return .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.genericArrayOps(array), from, until)), new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            return x1 == null ? "n/a" : String.valueOf(x1);
         }

         public final boolean isDefinedAt(final Object x1) {
            return x1 == null ? true : true;
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(" | ");
   }

   // $FF: synthetic method
   public static final void $anonfun$buildString$1(final StringBuilder output$1, final Object any) {
      output$1.$plus$plus$eq(any.toString());
      output$1.$plus$eq(BoxesRunTime.boxToCharacter('\n'));
   }

   private DebugUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
